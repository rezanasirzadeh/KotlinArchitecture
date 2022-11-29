package ir.digireza.s1_hilt.retrofit.di

import ir.digireza.s1_hilt.BASE_URL
import ir.digireza.s1_hilt.NAMED_BODY
import ir.digireza.s1_hilt.NAMED_HEADER
import ir.digireza.s1_hilt.NETWORK_TIMEOUT
import ir.digireza.s1_hilt.retrofit.api.ApiServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideConnectionTimeout() = NETWORK_TIMEOUT

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    @Named(NAMED_HEADER)
    fun provideHeaderInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    @Singleton
    @Named(NAMED_BODY)
    fun provideBodyInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideClient(
        time: Long,
        @Named(NAMED_HEADER) header: HttpLoggingInterceptor,
        @Named(NAMED_BODY) body: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(header)
        .addInterceptor(body)
        .connectTimeout(time, TimeUnit.SECONDS)
        .readTimeout(time, TimeUnit.SECONDS)
        .writeTimeout(time, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): ApiServices =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)
}