package space.ersan.blogito.di.builder

import dagger.Binds
import dagger.Module
import space.ersan.blogito.domain.feed.DefaultFeedPostsUseCase
import space.ersan.blogito.domain.feed.FeedPostsUseCase
import space.ersan.blogito.domain.viewer.DefaultEnrichCommentsUseCase
import space.ersan.blogito.domain.viewer.DefaultLoadPostCommentsUseCase
import space.ersan.blogito.domain.viewer.EnrichCommentsUseCase
import space.ersan.blogito.domain.viewer.LoadPostCommentsUseCase

@Module
abstract class UseCasesModule {

  @Binds
  abstract fun provideUserPostsUseCase(instance: DefaultFeedPostsUseCase): FeedPostsUseCase


  @Binds
  abstract fun provideGroupCommentsUseCase(instance: DefaultEnrichCommentsUseCase): EnrichCommentsUseCase


  @Binds
  abstract fun provideLoadPostCommentsUseCase(instance: DefaultLoadPostCommentsUseCase): LoadPostCommentsUseCase
}