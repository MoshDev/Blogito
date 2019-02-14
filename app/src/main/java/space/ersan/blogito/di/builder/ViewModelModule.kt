package space.ersan.blogito.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import space.ersan.blogito.ui.feed.DefaultFeedViewModel
import space.ersan.blogito.ui.feed.FeedViewModel
import space.ersan.blogito.ui.postviewer.DefaultPostViewerViewModel
import space.ersan.blogito.ui.postviewer.PostViewerViewModel
import space.ersan.blogito.viewmodel.BlogitoViewModelFactory
import space.ersan.blogito.viewmodel.ViewModelKey

@Module
abstract class ViewModelModule {

  @Binds
  abstract fun viewModelFactory(instance: BlogitoViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelKey(FeedViewModel::class)
  abstract fun provideFeedViewModel(instance: DefaultFeedViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(PostViewerViewModel::class)
  abstract fun provideFPostViewerViewModel(instance: DefaultPostViewerViewModel): ViewModel

}