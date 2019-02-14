package space.ersan.blogito.ui.postviewer

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.post_viewer_fragment.view.*
import space.ersan.blogito.R
import space.ersan.blogito.common.NativeView
import space.ersan.blogito.model.entity.Comment
import space.ersan.blogito.model.entity.EnrichedComment
import space.ersan.blogito.model.entity.Post
import space.ersan.blogito.ui.postviewer.view.PostViewerAdapter
import space.ersan.blogito.utils.NetworkStatus

interface PostViewerView {
  fun setComments(comments: List<EnrichedComment>)
  fun setPost(post: Post)
  fun setNetworkStatus(status: NetworkStatus)
}

class DefaultPostViewerView(context: Context) : FrameLayout(context), PostViewerView, NativeView {

  private val adapter = PostViewerAdapter()

  init {
    View.inflate(context, R.layout.post_viewer_fragment, this)
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = adapter
  }

  override fun setPost(post: Post) {
    adapter.addItem(post)
  }

  override fun setComments(comments: List<EnrichedComment>) {
    adapter.addItems(comments)
  }

  override fun setNetworkStatus(status: NetworkStatus) {
  }

  override fun getView(): View = this
}