package space.ersan.blogito

import android.app.Application
import space.ersan.blogito.di.Blogito
import space.ersan.blogito.di.DefaultInjector

class BlogitoApp : Application() {

  override fun onCreate() {
    super.onCreate()
    Blogito.injector = DefaultInjector(this)
  }
}