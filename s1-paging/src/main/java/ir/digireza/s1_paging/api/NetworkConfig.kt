package ir.digireza.s1_paging.api

import ir.digireza.s1_paging.utils.BASE_URL
import ir.digireza.s1_paging.utils.NETWORK_TIME
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val baseUrl = BASE_URL

const val networkTime = NETWORK_TIME

fun provideGson(): Gson = GsonBuilder().setLenient().create()

fun provideHttpClient(time: Long) = OkHttpClient.Builder()
    .readTimeout(time, TimeUnit.SECONDS)
    .writeTimeout(time, TimeUnit.SECONDS)
    .connectTimeout(time, TimeUnit.SECONDS)
    .retryOnConnectionFailure(true)
    .build()

fun provideRetrofit(url: String, client: OkHttpClient, gson: Gson): ApiServices =
    Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiServices::class.java)