package space.ersan.blogito.model.entity

import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.util.Date

@PaperParcel
data class Comment(
  @SerializedName("content")
  val content: String,
  @SerializedName("date")
  val date: Date,
  @SerializedName("id")
  val id: Int,
  @SerializedName("parent_id")
  val parentId: Int?,
  @SerializedName("postId")
  val postId: Int,
  @SerializedName("user")
  val user: String
) : PaperParcelable {
  companion object {
    @JvmField
    val CREATOR = PaperParcelComment.CREATOR
  }
}