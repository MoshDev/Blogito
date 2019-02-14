package space.ersan.blogito.ui.feed

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.feed_fragment.view.*
import space.ersan.blogito.R
import space.ersan.blogito.common.NativeView
import space.ersan.blogito.model.entity.Post
import space.ersan.blogito.ui.feed.view.PostsAdapter
import space.ersan.blogito.utils.NetworkStatus
import space.ersan.blogito.utils.SingleLiveEvent

interface FeedView {
  fun setPosts(posts: List<Post>)
  fun setNetworkStatus(it: NetworkStatus)
  fun observeItemsClick(): LiveData<Post>
}

class DefaultFeedView(context: Context) : FrameLayout(context), FeedView, NativeView {

  private val postsAdapter = PostsAdapter()

  init {
    View.inflate(context, R.layout.feed_fragment, this)
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = postsAdapter
  }

  override fun setPosts(posts: List<Post>) {
    postsAdapter.setItems(posts)
  }

  override fun setNetworkStatus(it: NetworkStatus) {
    progressBar.isVisible = it is NetworkStatus.Loading
    if (it is NetworkStatus.Error) {
      showErrorRetryView(it)
    }
  }

  override fun observeItemsClick(): LiveData<Post> = SingleLiveEvent<Post>().also { event ->
    postsAdapter.callback = {
      event.value = it
    }
  }

  private fun showErrorRetryView(error: NetworkStatus.Error) {
    Snackbar.make(this, R.string.failed_to_load, Snackbar.LENGTH_INDEFINITE)
      .setAction(R.string.retry) {
        error.retry?.invoke()
      }.show()
  }

  override fun getView(): View = this
}