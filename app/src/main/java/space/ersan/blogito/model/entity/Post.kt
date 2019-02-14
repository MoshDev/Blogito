package space.ersan.blogito.model.entity

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.util.Date

@PaperParcel
data class Post(
  @SerializedName("author")
  val author: String,
  @SerializedName("content")
  val content: String,
  @SerializedName("description")
  val description: String,
  @SerializedName("id")
  val id: Int,
  @SerializedName("publish_date")
  val publishDate: Date,
  @SerializedName("slug")
  val slug: String,
  @SerializedName("title")
  val title: String
) : PaperParcelable {
  companion object {
    @JvmField
    val CREATOR = PaperParcelPost.CREATOR
  }
}