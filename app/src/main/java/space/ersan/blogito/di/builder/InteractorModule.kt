package space.ersan.blogito.di.builder

import dagger.Binds
import dagger.Module
import space.ersan.blogito.domain.feed.DefaultFeedInteractor
import space.ersan.blogito.domain.feed.FeedInteractor
import space.ersan.blogito.domain.viewer.DefaultPostViewerInteractor
import space.ersan.blogito.domain.viewer.PostViewerInteractor

@Module
abstract class InteractorModule {

  @Binds
  abstract fun provideFeedInteractor(instance: DefaultFeedInteractor): FeedInteractor

  @Binds
  abstract fun provideViewerInteractor(instance: DefaultPostViewerInteractor): PostViewerInteractor

}