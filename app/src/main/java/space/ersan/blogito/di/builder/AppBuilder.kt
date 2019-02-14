package space.ersan.blogito.di.builder

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import dagger.Component
import dagger.Module
import dagger.Provides
import space.ersan.blogito.BlogitoApp
import javax.inject.Scope

@Scope
annotation class AppScope

@AppScope
@Component(
  modules = [
    AppModule::class, AppConfigModule::class, ViewModelModule::class, DispatchersModule::class,
    InteractorModule::class, UseCasesModule::class, NetworkModule::class, GsonModule::class, RepositoryModule::class
  ]
)
interface AppComponent {

  fun viewModelFactory(): ViewModelProvider.Factory
}

@Module
class AppModule(private val app: BlogitoApp) {

  @AppScope
  @Provides
  fun blogitoApp() = app

  @AppScope
  @Provides
  fun application(): Application = app
}