package space.ersan.blogito.domain.feed

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks
import space.ersan.blogito.common.Result
import space.ersan.blogito.onMethod

class DefaultFeedInteractorTest {

  @Mock
  lateinit var mockFeedPostsUseCase: FeedPostsUseCase

  lateinit var defaultFeedInteractorUnderTest: DefaultFeedInteractor

  @Before
  fun setUp() {
    initMocks(this)
    defaultFeedInteractorUnderTest = DefaultFeedInteractor(mockFeedPostsUseCase!!)
  }

  @Test
  fun testGetPosts() = runBlocking {
    // Setup
    onMethod(mockFeedPostsUseCase.getPosts()).thenReturn(Result.Some(emptyList()))
    // Run the test
    val result = defaultFeedInteractorUnderTest.getPosts()

    // Verify the results
    assertFalse(result.isError)
    Mockito.verify(mockFeedPostsUseCase).getPosts()
    assertTrue(result is Result.Some)
  }
}
