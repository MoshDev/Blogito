package space.ersan.blogito.di.builder

import android.app.Application
import dagger.Module
import dagger.Provides
import space.ersan.blogito.R
import space.ersan.blogito.common.AppConfig

@Module
class AppConfigModule {

  @AppScope
  @Provides
  fun provideAppConfig(app: Application) = AppConfig(
    apiBaseUrl = app.getString(R.string.api_base_url)
  )
}