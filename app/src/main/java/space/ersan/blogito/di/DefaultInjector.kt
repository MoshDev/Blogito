package space.ersan.blogito.di

import space.ersan.blogito.BlogitoApp
import space.ersan.blogito.di.builder.AppComponent
import space.ersan.blogito.di.builder.AppModule
import space.ersan.blogito.di.builder.DaggerAppComponent
import space.ersan.blogito.ui.feed.DaggerFeedComponent
import space.ersan.blogito.ui.feed.FeedFragment
import space.ersan.blogito.ui.feed.FeedModule
import space.ersan.blogito.ui.postviewer.DaggerPostViewerComponent
import space.ersan.blogito.ui.postviewer.PostViewerFragment
import space.ersan.blogito.ui.postviewer.PostViewerModule

class DefaultInjector(app: BlogitoApp) : Injector {

  private val appComponent: AppComponent =
    DaggerAppComponent.builder().appModule(AppModule(app)).build()

  override fun inject(fragment: FeedFragment) {
    DaggerFeedComponent.builder().appComponent(appComponent).feedModule(FeedModule(fragment))
      .build().inject(fragment)
  }

  override fun inject(fragment: PostViewerFragment) {
    DaggerPostViewerComponent.builder().appComponent(appComponent)
      .postViewerModule(PostViewerModule(fragment))
      .build().inject(fragment)
  }
}