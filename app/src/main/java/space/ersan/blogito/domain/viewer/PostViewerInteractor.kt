package space.ersan.blogito.domain.viewer

import space.ersan.blogito.common.Result
import space.ersan.blogito.model.entity.EnrichedComment
import javax.inject.Inject

interface PostViewerInteractor {
  suspend fun getComments(postId: Int): Result<List<EnrichedComment>>
}

class DefaultPostViewerInteractor @Inject constructor(
  private val enrichCommentsUseCase: EnrichCommentsUseCase,
  private val loadPostCommentsUseCase: LoadPostCommentsUseCase
) : PostViewerInteractor {

  override suspend fun getComments(postId: Int): Result<List<EnrichedComment>> {
    val cmts = loadPostCommentsUseCase.loadPostComments(postId)
    return if (cmts is Result.Some) {
      try {
        Result.Some(enrichCommentsUseCase.enrichComments(cmts.value))
      } catch (e: Exception) {
        Result.Error<List<EnrichedComment>>(e)
      }
    } else {
      Result.Error((cmts as Result.Error).error)
    }
  }
}