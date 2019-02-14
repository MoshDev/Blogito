package space.ersan.blogito.model

import space.ersan.blogito.common.Result
import space.ersan.blogito.model.entity.Comment
import space.ersan.blogito.model.entity.Post
import space.ersan.blogito.model.remote.BlogitoRemoteClient
import javax.inject.Inject

interface BlogitoRepository {
  suspend fun getPosts(): Result<List<Post>>
  suspend fun getPostComments(id: Int): Result<List<Comment>>
}

class DefaultBlogitoRepository(private val blogitoRemoteClient: BlogitoRemoteClient) :
  BlogitoRepository {

  override suspend fun getPosts(): Result<List<Post>> = try {
    Result.Some(blogitoRemoteClient.getPosts().sortedBy { it.publishDate })
  } catch (e: Exception) {
    Result.Error(e)
  }

  override suspend fun getPostComments(id: Int): Result<List<Comment>> = try {
    Result.Some(blogitoRemoteClient.getPostComments(id))
  } catch (e: Exception) {
    Result.Error(e)
  }
}