package space.ersan.blogito.model.remote

import kotlinx.coroutines.withContext
import space.ersan.blogito.common.AppCoroutineDispatchers

class BlogitoRemoteClient(
  dispatchers: AppCoroutineDispatchers,
  private val api: BlogitoApi
) {

  private val dispatcher = dispatchers.NETWORK

  suspend fun getPosts() = withContext(dispatcher) { api.getPosts().await() }

  suspend fun getPost(id: Int) = withContext(dispatcher) { api.getPost(id).await() }

  suspend fun getPostComments(id: Int) = withContext(dispatcher) { api.getPostComments(id).await() }
}