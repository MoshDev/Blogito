package space.ersan.blogito.ui.postviewer.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import space.ersan.blogito.R
import space.ersan.blogito.model.entity.EnrichedComment
import space.ersan.blogito.model.entity.Post
import space.ersan.blogito.utils.toPostFormattedDate

class PostViewerAdapter : RecyclerView.Adapter<ViewerHolder<Any>>() {

  private val items = mutableListOf<Any>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewerHolder<Any> =
    when (viewType) {
      1 -> PostViewerHolder(
        LayoutInflater.from(parent.context).inflate(
          R.layout.view_post_viewer_item, parent, false
        )
      )
      2 -> CommentViewHolder(
        LayoutInflater.from(parent.context).inflate(
          R.layout.view_post_viewer_comment_item,
          parent,
          false
        )
      )
      else -> throw IllegalArgumentException()
    } as ViewerHolder<Any>

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: ViewerHolder<Any>, position: Int) =
    holder.onBind(items[position])

  override fun getItemViewType(position: Int) = when (val item = items[position]) {
    is Post -> 1
    is EnrichedComment -> 2
    else -> throw IllegalArgumentException("not type defined for item $item ")
  }

  fun addItem(item: Any) {
    items.add(item)
    notifyDataSetChanged()
  }

  fun addItems(its: Collection<Any>) {
    items.addAll(its)
    notifyDataSetChanged()
  }
}

abstract class ViewerHolder<Item : Any>(view: View) : RecyclerView.ViewHolder(view) {
  abstract fun onBind(item: Item)
}

class PostViewerHolder(view: View) : ViewerHolder<Post>(view) {

  private val titleView = view.findViewById<TextView>(R.id.titleView)
  private val authorView = view.findViewById<TextView>(R.id.authorView)
  private val contentView = view.findViewById<TextView>(R.id.contentView)
  private val dateView = view.findViewById<TextView>(R.id.dateView)

  override fun onBind(item: Post) {
    titleView.text = item.title
    authorView.text = item.author
    contentView.text = HtmlCompat.fromHtml(item.content, HtmlCompat.FROM_HTML_MODE_COMPACT)
    dateView.text = item.publishDate.toPostFormattedDate()
  }
}

class CommentViewHolder(view: View) : ViewerHolder<EnrichedComment>(view) {

  private val headerView = view.findViewById<TextView>(R.id.commentHeaderView)
  private val contentView = view.findViewById<TextView>(R.id.commentContentView)
  private val depthView = view.findViewById<View>(R.id.depthView)
  private val commentDepthDimen =
    view.resources.getDimensionPixelSize(R.dimen.comment_single_depth_offset)

  override fun onBind(item: EnrichedComment) {
    val comment = item.comment
    headerView.text = itemView.resources.getString(
      R.string.user_comment,
      comment.user,
      comment.date.toPostFormattedDate()
    )
    contentView.text = comment.content

    depthView.updateLayoutParams<LinearLayout.LayoutParams> {
      width = (Math.min(item.depth, 5) * commentDepthDimen)
    }
  }
}