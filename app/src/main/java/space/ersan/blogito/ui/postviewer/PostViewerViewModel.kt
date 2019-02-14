package space.ersan.blogito.ui.postviewer

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import space.ersan.blogito.common.AppCoroutineDispatchers
import space.ersan.blogito.common.Result
import space.ersan.blogito.domain.viewer.PostViewerInteractor
import space.ersan.blogito.model.entity.Comment
import space.ersan.blogito.model.entity.EnrichedComment
import space.ersan.blogito.model.entity.Post
import space.ersan.blogito.utils.LiveNetworkStatus
import space.ersan.blogito.utils.NetworkStatus
import javax.inject.Inject

abstract class PostViewerViewModel : ViewModel() {
  abstract fun setPost(post: Post)
  abstract fun getComments(): LiveData<List<EnrichedComment>>
  abstract fun loadComments()
  abstract fun getPost(): LiveData<Post>
  abstract fun getNetworkStatus(): LiveData<NetworkStatus>
}

class DefaultPostViewerViewModel @Inject constructor(
  cor: AppCoroutineDispatchers,
  private val interactor: PostViewerInteractor
) :
  PostViewerViewModel() {

  private val parentJob = SupervisorJob()
  private val scope = CoroutineScope(cor.UI + parentJob)

  private val liveNetworkStatus = LiveNetworkStatus()
  private val postData = MutableLiveData<Post>()
  private val commentsData = MutableLiveData<List<EnrichedComment>>()

  @MainThread
  override fun setPost(post: Post) {
    if (postData.value != post) {
      postData.value = post
      loadComments()
    }
  }

  override fun getPost(): LiveData<Post> = postData

  override fun getComments(): LiveData<List<EnrichedComment>> = commentsData

  override fun loadComments() {
    scope.launch {
      liveNetworkStatus.postLoading()
      postData.value?.let {
        val result = interactor.getComments(it.id)
        when (result) {
          is Result.Some -> {
            liveNetworkStatus.postLoaded()
            commentsData.postValue(result.value)
          }
          is Result.Error -> liveNetworkStatus.postError(result.error){
            loadComments()
          }
        }
      }
    }
  }

  override fun getNetworkStatus(): LiveData<NetworkStatus> = liveNetworkStatus
}
