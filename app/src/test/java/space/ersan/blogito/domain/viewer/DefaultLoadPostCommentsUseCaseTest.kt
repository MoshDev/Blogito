package space.ersan.blogito.domain.viewer

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import space.ersan.blogito.common.Result
import space.ersan.blogito.mock
import space.ersan.blogito.model.BlogitoRepository
import space.ersan.blogito.onMethod
import space.ersan.blogito.parseResourcesJson

class DefaultLoadPostCommentsUseCaseTest {

  lateinit var repository: BlogitoRepository

  lateinit var defaultLoadPostCommentsUseCaseUnderTest: DefaultLoadPostCommentsUseCase

  @Before
  fun setUp() {
    repository = mock()
    defaultLoadPostCommentsUseCaseUnderTest = DefaultLoadPostCommentsUseCase(repository)
  }

  @Test
  fun testLoadPostComments() = runBlocking {
    // Setup
    val postId = 1
    onMethod(repository.getPostComments(1)).thenReturn(
      Result.Some(parseResourcesJson("comments_1.json"))
    )

    // Run the test
    val result = defaultLoadPostCommentsUseCaseUnderTest.loadPostComments(postId)

    // Verify the results
    assertFalse(result.isError)
    assertTrue(result is Result.Some)
    assertNotNull((result as Result.Some).value)
    assertEquals(5, result.value.size)
    assertEquals(1, result.value[0].id)
    assertEquals(2, result.value[1].id)
  }

  @Test
  fun testLoadPostCommentsFailure() = runBlocking {
    // Setup
    val postId = 1
    onMethod(repository.getPostComments(1)).thenReturn(
      Result.Error(IllegalArgumentException("testing"))
    )

    // Run the test
    val result = defaultLoadPostCommentsUseCaseUnderTest.loadPostComments(postId)

    // Verify the results
    assertTrue(result.isError)
    assertTrue(result is Result.Error)
    assertNotNull((result as Result.Error).error)
    assertEquals(IllegalArgumentException::class, result.error::class)
  }
}
