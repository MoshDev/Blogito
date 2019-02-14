package space.ersan.blogito.domain.viewer

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks
import space.ersan.blogito.common.Result
import space.ersan.blogito.model.entity.Comment
import space.ersan.blogito.model.entity.EnrichedComment
import space.ersan.blogito.onMethod
import java.util.Date
import kotlin.IllegalArgumentException

class DefaultPostViewerInteractorTest {

  @Mock
  lateinit var mockEnrichCommentsUseCase: EnrichCommentsUseCase

  @Mock
  lateinit var mockLoadPostCommentsUseCase: LoadPostCommentsUseCase

  lateinit var defaultPostViewerInteractorUnderTest: DefaultPostViewerInteractor

  @Before
  fun setUp() {
    initMocks(this)
    defaultPostViewerInteractorUnderTest =
      DefaultPostViewerInteractor(
        mockEnrichCommentsUseCase, mockLoadPostCommentsUseCase
      )
  }

  @Test
  fun testGetComments() = runBlocking {
    // Setup
    val date = Date()
    val comments = listOf(
      Comment("Hi", date, 1, 2, 1, "user"),
      Comment("Hi", date, 5, 3, 1, "user2")
    )
    val postId = 1
    onMethod(mockLoadPostCommentsUseCase.loadPostComments(postId)).thenReturn(
      Result.Some(comments)
    )

    onMethod(mockEnrichCommentsUseCase.enrichComments(Mockito.anyList())).thenReturn(comments.map {
      EnrichedComment(
        it,
        1
      )
    })

    // Run the test
    val result = defaultPostViewerInteractorUnderTest.getComments(postId)

    // Verify the results
    assertFalse(result.isError)
    Assert.assertTrue(result is Result.Some)
    Assert.assertNotNull((result as Result.Some).value)
    assertEquals(2, result.value.size)
    assertEquals(1, result.value[0].comment.id)
    assertEquals(1, result.value[0].depth)
    assertEquals(5, result.value[1].comment.id)
    assertEquals(1, result.value[1].depth)
  }

  @Test
  fun testGetCommentsFailureLoading() = runBlocking {
    // Setup
    val postId = 1
    onMethod(mockLoadPostCommentsUseCase.loadPostComments(postId)).thenReturn(
      Result.Error(IllegalArgumentException("testing"))
    )

    onMethod(mockEnrichCommentsUseCase.enrichComments(Mockito.anyList())).thenReturn(emptyList())

    // Run the test
    val result = defaultPostViewerInteractorUnderTest.getComments(postId)

    // Verify the results
    Assert.assertTrue(result.isError)
    Assert.assertTrue(result is Result.Error)
    Assert.assertNotNull((result as Result.Error).error)
    assertEquals(IllegalArgumentException::class, result.error::class)
  }

  @Test
  fun testGetCommentsFailureEnriching() = runBlocking {
    // Setup
    val date = Date()
    val comments = listOf(
      Comment("Hi", date, 1, 2, 1, "user"),
      Comment("Hi", date, 5, 3, 1, "user2")
    )
    val postId = 1
    onMethod(mockLoadPostCommentsUseCase.loadPostComments(postId)).thenReturn(
      Result.Some(comments)
    )

    onMethod(mockEnrichCommentsUseCase.enrichComments(Mockito.anyList())).thenThrow(IllegalArgumentException("testing"))

    // Run the test
    val result = defaultPostViewerInteractorUnderTest.getComments(postId)

    // Verify the results
    Assert.assertTrue(result.isError)
    Assert.assertTrue(result is Result.Error)
    Assert.assertNotNull((result as Result.Error).error)
    assertEquals(IllegalArgumentException::class, result.error::class)
  }
}
