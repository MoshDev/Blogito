package space.ersan.blogito.di.builder

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import dagger.Module
import dagger.Provides
import space.ersan.blogito.utils.JsonDateParser
import java.util.Date

@Module
class GsonModule {

  @Provides
  @AppScope
  fun provideGson(): Gson {
    val builder = GsonBuilder()
    builder.setLenient()

    builder.registerTypeAdapter(Date::class.java, JsonDeserializer<Date> { json, _, _ ->
      when (json.asJsonPrimitive.isString) {
        true -> JsonDateParser.parse(json.asJsonPrimitive.asString)
        else -> null
      }
    })
    return builder.create()
  }
}