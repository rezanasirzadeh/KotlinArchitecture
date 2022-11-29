package ir.digireza.s1_paging.di

import ir.digireza.s1_paging.api.ApiServices
import ir.digireza.s1_paging.utils.BASE_URL
import ir.digireza.s1_paging.utils.NETWORK_TIME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

    @Provides
    fun provideNetworkTime() = NETWORK_TIME

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideClient(time: Long) = OkHttpClient.Builder()
        .readTimeout(time, TimeUnit.SECONDS)
        .writeTimeout(time, TimeUnit.SECONDS)
        .connectTimeout(time, TimeUnit.SECONDS)
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