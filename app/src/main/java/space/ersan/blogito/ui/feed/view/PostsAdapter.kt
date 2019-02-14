package space.ersan.blogito.ui.feed.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import space.ersan.blogito.R
import space.ersan.blogito.model.entity.Post
import space.ersan.blogito.utils.toPostFormattedDate

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

  var callback: ((Post) -> Unit)? = null
  private val internalCallback: (Post) -> Unit = {
    callback?.invoke(it)
  }
  private val items = mutableListOf<Post>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    PostViewHolder(
      LayoutInflater.from(parent.context).inflate(
        R.layout.view_posts_list_item,
        parent,
        false
      ), internalCallback
    )

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
    holder.onBind(items[position])

  fun setItems(list: List<Post>) = items.apply {
    clear()
    addAll(list)
  }.also { notifyDataSetChanged() }
}

class PostViewHolder(
  view: View,
  private val callback: (Post) -> Unit
) : RecyclerView.ViewHolder(view) {

  private val titleView = view.findViewById<TextView>(R.id.titleView)
  private val authorView = view.findViewById<TextView>(R.id.authorView)
  private val descriptionView = view.findViewById<TextView>(R.id.descriptionView)
  private val dateView = view.findViewById<TextView>(R.id.dateView)

  fun onBind(post: Post) {
    titleView.text = post.title
    authorView.text = post.author
    descriptionView.text = post.description
    dateView.text = post.publishDate.toPostFormattedDate()
    itemView.setOnClickListener { callback(post) }
  }
}
