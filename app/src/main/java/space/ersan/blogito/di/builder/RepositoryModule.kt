package space.ersan.blogito.di.builder

import dagger.Module
import dagger.Provides
import space.ersan.blogito.model.BlogitoRepository
import space.ersan.blogito.model.DefaultBlogitoRepository
import space.ersan.blogito.model.remote.BlogitoRemoteClient

@Module
class RepositoryModule {

  @AppScope
  @Provides
  fun provideRepository(client: BlogitoRemoteClient): BlogitoRepository {
    return DefaultBlogitoRepository(client)
  }
}