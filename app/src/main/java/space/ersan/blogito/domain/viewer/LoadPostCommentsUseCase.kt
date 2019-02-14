package space.ersan.blogito.domain.viewer

import space.ersan.blogito.common.Result
import space.ersan.blogito.domain.UseCase
import space.ersan.blogito.model.BlogitoRepository
import space.ersan.blogito.model.entity.Comment
import javax.inject.Inject

interface LoadPostCommentsUseCase : UseCase {
  suspend fun loadPostComments(postId:Int): Result<List<Comment>>
}

class DefaultLoadPostCommentsUseCase @Inject constructor(private val repository: BlogitoRepository) :
  LoadPostCommentsUseCase {

  override suspend fun loadPostComments(postId:Int): Result<List<Comment>> =
    repository.getPostComments(postId)
}