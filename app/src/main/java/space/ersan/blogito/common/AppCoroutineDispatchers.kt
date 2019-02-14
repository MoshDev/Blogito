package space.ersan.blogito.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

open class AppCoroutineDispatchers(
  val IO: CoroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher(),
  val NETWORK: CoroutineDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher(),
  val COMPUTING: CoroutineDispatcher = Dispatchers.Default,
  val UI: CoroutineDispatcher = Dispatchers.Main
)