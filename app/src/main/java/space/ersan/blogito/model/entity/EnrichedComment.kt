package space.ersan.blogito.model.entity

data class EnrichedComment(val comment: Comment, val depth: Int = 0) {
  fun prettyToString() = "${"\t".repeat(depth)}${toString()}"
}