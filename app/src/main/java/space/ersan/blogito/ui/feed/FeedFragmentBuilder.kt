package space.ersan.blogito.ui.feed

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.blogito.common.NativeView
import space.ersan.blogito.di.builder.AppComponent
import javax.inject.Scope

@Scope
annotation class FeedScope

@FeedScope
@Component(modules = [FeedModule::class], dependencies = [AppComponent::class])
interface FeedComponent {
  fun inject(feedFragment: FeedFragment)
}

@Module
class FeedModule(private val fragment: FeedFragment) {

  @FeedScope
  @Provides
  fun feedViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProviders.of(fragment, factory).get(FeedViewModel::class.java)

  @FeedScope
  @Provides
  fun view(): FeedView = DefaultFeedView(fragment.requireContext())

  @FeedScope
  @Provides
  fun nativeView(view: FeedView): NativeView = view as NativeView

  @FeedScope
  @Provides
  fun feedNavigator(): FeedNavigator = DefaultFeedNavigator(fragment)
}

