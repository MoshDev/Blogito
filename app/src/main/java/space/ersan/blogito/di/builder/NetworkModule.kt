package space.ersan.blogito.di.builder

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import space.ersan.blogito.BuildConfig
import space.ersan.blogito.common.AppConfig
import space.ersan.blogito.common.AppCoroutineDispatchers
import space.ersan.blogito.model.remote.BlogitoApi
import space.ersan.blogito.model.remote.BlogitoRemoteClient
import space.ersan.blogito.utils.then

@Module
class NetworkModule {

  @AppScope
  @Provides
  fun provideBlogitoRemoteClient(
    dispatchers: AppCoroutineDispatchers,
    api: BlogitoApi
  ): BlogitoRemoteClient {
    return BlogitoRemoteClient(dispatchers, api)
  }

  @AppScope
  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .apply {
        BuildConfig.DEBUG.then {
          addInterceptor(
            HttpLoggingInterceptor().setLevel(
              HttpLoggingInterceptor.Level.BODY
            )
          )
        }
      }
      .build()
  }

  @AppScope
  @Provides
  fun provideRetrofit(appConfig: AppConfig, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder().client(okHttpClient).baseUrl(appConfig.apiBaseUrl)
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
  }

  @AppScope
  @Provides
  fun provideBlogitoApi(retrofit: Retrofit): BlogitoApi {
    return retrofit.create(BlogitoApi::class.java)
  }
}


