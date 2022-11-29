package ir.digireza.s1_koin.retrofit.api

import ir.digireza.s1_koin.BASE_URL
import ir.digireza.s1_koin.NETWORK_TIME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.sin

const val baseUrl = BASE_URL

const val networkTime = NETWORK_TIME

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun provideInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun provideClient(time: Long, interceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
    .readTimeout(time, TimeUnit.SECONDS)
    .connectTimeout(time, TimeUnit.SECONDS)
    .writeTimeout(time, TimeUnit.SECONDS)
    .addInterceptor(interceptor)
    .retryOnConnectionFailure(true)
    .build()

fun provideRetrofit(url: String, client: OkHttpClient, gson: Gson): ApiServices =
    Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(ApiServices::class.java)

val networkModule = module {
    single { baseUrl }
    single { networkTime }
    single { provideGson() }
    single { provideInterceptor() }
    single { provideClient(get(), get()) }
    single { provideRetrofit(get(), get(), get()) }
}