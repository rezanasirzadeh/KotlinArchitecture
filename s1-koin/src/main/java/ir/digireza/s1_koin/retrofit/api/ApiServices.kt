package ir.digireza.s1_koin.retrofit.api

import ir.digireza.s1_koin.retrofit.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("movies")
    suspend fun allMovies(): Response<ResponseMovies>
}