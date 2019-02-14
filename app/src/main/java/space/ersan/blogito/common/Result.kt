package space.ersan.blogito.common

sealed class Result<T>(val isError: Boolean) {
  data class Some<T>(val value: T) : Result<T>(false)
  data class Error<T>(val error: Exception) : Result<T>(true)
}