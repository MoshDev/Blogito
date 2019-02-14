package space.ersan.blogito.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import space.ersan.blogito.common.AppCoroutineDispatchers
import space.ersan.blogito.common.Result
import space.ersan.blogito.domain.feed.FeedInteractor
import space.ersan.blogito.model.entity.Post
import space.ersan.blogito.utils.LiveNetworkStatus
import space.ersan.blogito.utils.NetworkStatus
import javax.inject.Inject

abstract class FeedViewModel : ViewModel() {
  abstract fun getPostsList(): LiveData<List<Post>>
  abstract fun getNetworkStatus(): LiveData<NetworkStatus>
  abstract fun refreshPostsList()
  abstract fun onPostClicked(feedNavigator: FeedNavigator, post: Post)
}

class DefaultFeedViewModel @Inject constructor(
  dis: AppCoroutineDispatchers,
  private val interactor: FeedInteractor
) : FeedViewModel() {

  private val parentJob = SupervisorJob()
  private val scope = CoroutineScope(dis.UI + parentJob)

  private val liveNetworkStatus = LiveNetworkStatus()
  private val postsLiveData = MutableLiveData<List<Post>>()

  init {
    refreshPostsList()
  }

  override fun getPostsList(): LiveData<List<Post>> = postsLiveData

  override fun getNetworkStatus(): LiveNetworkStatus = liveNetworkStatus

  override fun refreshPostsList() {
    scope.launch {
      liveNetworkStatus.postLoading()
      when (val result = interactor.getPosts()) {
        is Result.Some -> {
          postsLiveData.postValue(result.value)
          liveNetworkStatus.postLoaded()
        }
        is Result.Error -> liveNetworkStatus.postError(error = result.error)
      }
    }
  }

  override fun onPostClicked(feedNavigator: FeedNavigator, post: Post) {
    feedNavigator.showPostScreen(post)
  }

  override fun onCleared() {
    parentJob.cancel()
    super.onCleared()
  }
}
