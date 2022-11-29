package ir.digireza.s1_paging.api

import ir.digireza.s1_paging.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("movies")
    suspend fun getAllMovies(@Query("page") page: Int): Response<ResponseMovies>
}