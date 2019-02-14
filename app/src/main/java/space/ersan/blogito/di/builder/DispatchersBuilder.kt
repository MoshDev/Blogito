package space.ersan.blogito.di.builder

import dagger.Module
import dagger.Provides
import space.ersan.blogito.common.AppCoroutineDispatchers

@Module
class DispatchersModule {

  @AppScope
  @Provides
  fun provideDispatchers() = AppCoroutineDispatchers()
}