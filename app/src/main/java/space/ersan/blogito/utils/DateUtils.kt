package space.ersan.blogito.utils

import space.ersan.blogito.model.entity.Post
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object JsonDateParser {

  private val DATE_FORMAT by lazy {
    SimpleDateFormat("yyyy-MM-dd", Locale.US)
  }

  fun parse(date: String?): Date? {
    if (!date.isNullOrBlank()) {
      return DATE_FORMAT.parse(date)
    }
    return null
  }
}

private val POST_YEAR_DATE_FORMAT by lazy {
  SimpleDateFormat("dd MMM yyyy", Locale.US)
}

fun Date.toPostFormattedDate(): String = POST_YEAR_DATE_FORMAT.format(this)
