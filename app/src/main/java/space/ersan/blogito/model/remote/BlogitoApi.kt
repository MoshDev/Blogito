package space.ersan.blogito.model.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import space.ersan.blogito.model.entity.Comment
import space.ersan.blogito.model.entity.Post

interface BlogitoApi {

  @GET("posts")
  fun getPosts(): Deferred<List<Post>>

  @GET("posts/{post_id}")
  fun getPost(@Path("post_id") id: Int): Deferred<Post>

  @GET("posts/{post_id}/comments")
  fun getPostComments(@Path("post_id") id: Int): Deferred<List<Comment>>
}