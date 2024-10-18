package com.minjin.musinsa.data.di

import com.minjin.musinsa.data.BuildConfig
import com.minjin.musinsa.data.model.component.UiType
import com.minjin.musinsa.data.model.component.UnknownTypeContent
import com.minjin.musinsa.data.model.component.content.ContentType
import com.minjin.musinsa.data.model.component.footer.FooterType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object ServiceModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpCallFactory: OkHttpClient,
        json: Json,
    ): Retrofit {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .callFactory(okHttpCallFactory)
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideJson(): Json {
        val module = SerializersModule {
            polymorphic(UiType::class) {
                // content
                subclass(ContentType.GridTypeContent::class, ContentType.GridTypeContent.serializer())
                subclass(ContentType.BannerTypeContent::class, ContentType.BannerTypeContent.serializer())
                subclass(ContentType.ScrollTypeContent::class, ContentType.ScrollTypeContent.serializer())
                subclass(ContentType.StyleTypeContent::class, ContentType.StyleTypeContent.serializer())
                // footer
                subclass(FooterType.MoreTypeFooter::class, FooterType.MoreTypeFooter.serializer())
                subclass(FooterType.RefreshTypeFooter::class, FooterType.RefreshTypeFooter.serializer())
                // default
                defaultDeserializer { UnknownTypeContent.serializer() }
            }
        }

        return Json {
            isLenient = true
            coerceInputValues = true
            ignoreUnknownKeys = true
            encodeDefaults = true
            serializersModule = module
            classDiscriminator = "Type"
        }
    }
}