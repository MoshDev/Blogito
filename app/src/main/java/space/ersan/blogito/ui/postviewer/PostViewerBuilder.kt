package space.ersan.blogito.ui.postviewer

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.blogito.common.NativeView
import space.ersan.blogito.di.builder.AppComponent
import javax.inject.Scope

@Scope
annotation class PostViewerScope

@PostViewerScope
@Component(modules = [PostViewerModule::class], dependencies = [AppComponent::class])
interface PostViewerComponent {
  fun inject(postViewerFragment: PostViewerFragment)
}

@Module
class PostViewerModule(private val fragment: PostViewerFragment){

  @PostViewerScope
  @Provides
  fun postViewerViewModel(factory: ViewModelProvider.Factory) =
    ViewModelProviders.of(fragment, factory).get(PostViewerViewModel::class.java)

  @PostViewerScope
  @Provides
  fun view(): PostViewerView = DefaultPostViewerView(fragment.requireContext())

  @PostViewerScope
  @Provides
  fun nativeView(view: PostViewerView): NativeView = view as NativeView

  @PostViewerScope
  @Provides
  fun postViewerNavigator(): PostViewerNavigator = DefaultPostViewerNavigator(fragment)

}