package space.ersan.blogito.domain.viewer

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import space.ersan.blogito.model.entity.Comment
import space.ersan.blogito.parseResourcesJson

class DefaultEnrichCommentsUseCaseTest {

  private lateinit var defaultEnrichCommentsUseCase2UnderTest: DefaultEnrichCommentsUseCase

  @Before
  fun setUp() {
    defaultEnrichCommentsUseCase2UnderTest = DefaultEnrichCommentsUseCase()
  }

  @Test
  fun testEnrichComments() {
    // Setup
    val comments: List<Comment> = parseResourcesJson("comments_1.json")

    // Run the test
    val result = defaultEnrichCommentsUseCase2UnderTest.enrichComments(
      comments
    )

    // Verify the results
    assertNotNull(result)
    assertEquals(result.size, comments.size)
    assertEquals(1, result[0].comment.id)

    assertEquals(2, result[1].comment.id)
    assertEquals(1, result[1].depth)

    assertEquals(3, result[2].comment.id)
    assertEquals(2, result[2].depth)

    assertEquals(4, result[3].comment.id)
    assertEquals(1, result[3].depth)

    assertEquals(5, result[4].comment.id)
  }
}
