package space.ersan.blogito.di

import space.ersan.blogito.ui.feed.FeedFragment
import space.ersan.blogito.ui.postviewer.PostViewerFragment

object Blogito {
  lateinit var injector: Injector
}

interface Injector {
  fun inject(fragment: FeedFragment)
  fun inject(fragment: PostViewerFragment)
}