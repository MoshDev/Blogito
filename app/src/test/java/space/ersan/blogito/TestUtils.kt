package space.ersan.blogito

import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing
import space.ersan.blogito.common.AppCoroutineDispatchers
import space.ersan.blogito.di.builder.GsonModule

inline fun <reified T> mock() = Mockito.mock(T::class.java)!!

fun <T> onMethod(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)

@Suppress("EXPERIMENTAL_API_USAGE")
private val nowExecutor = Dispatchers.Unconfined

object TestCoroutineDispatchers : AppCoroutineDispatchers(
  nowExecutor, nowExecutor, nowExecutor,
  nowExecutor
)

inline fun <reified T> parseResourcesJson(fileName: String): T {
  val gson = GsonModule().provideGson()
  val reader = gson.javaClass.getResourceAsStream("/$fileName").reader()
  return gson.fromJson(reader, object : TypeToken<T>() {
  }.type)
}