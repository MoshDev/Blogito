package space.ersan.blogito.domain.viewer

import space.ersan.blogito.domain.UseCase
import space.ersan.blogito.model.entity.Comment
import space.ersan.blogito.model.entity.EnrichedComment
import javax.inject.Inject

interface EnrichCommentsUseCase : UseCase {
  fun enrichComments(comments: List<Comment>): List<EnrichedComment>
}

class DefaultEnrichCommentsUseCase @Inject constructor() :
  EnrichCommentsUseCase {

  override fun enrichComments(comments: List<Comment>): List<EnrichedComment> {
    return comments.sortedBy { it.date }
      .fold(mutableListOf()) { items: MutableList<CommentGroup>, comment: Comment ->
        CommentGroup(
          comment = comment,
          parent = comment.parentId?.let { id ->
            items.first { it.comment.id == id }
          }
        ).also { theComment ->
          theComment.parent?.let { parent ->
            parent.increaseChildCount()
            items.add(items.indexOf(parent) + parent.childCount, theComment)
          } ?: items.add(theComment)
        }
        items
      }.map { EnrichedComment(it.comment, it.depth) }
  }
}

private data class CommentGroup(
  val comment: Comment,
  val parent: CommentGroup?
) {

  val depth: Int = if (parent != null) 1 + parent.depth else 0

  var childCount: Int = 0
    private set

  fun increaseChildCount() {
    childCount++
    parent?.increaseChildCount()
  }
}
