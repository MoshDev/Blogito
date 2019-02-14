package space.ersan.blogito.domain.feed

import space.ersan.blogito.common.Result
import space.ersan.blogito.domain.UseCase
import space.ersan.blogito.model.BlogitoRepository
import space.ersan.blogito.model.entity.Post
import javax.inject.Inject

interface FeedPostsUseCase : UseCase {
  suspend fun getPosts(): Result<List<Post>>
}

class DefaultFeedPostsUseCase @Inject constructor(private val repository: BlogitoRepository) : FeedPostsUseCase {
  override suspend fun getPosts(): Result<List<Post>> = repository.getPosts()
}