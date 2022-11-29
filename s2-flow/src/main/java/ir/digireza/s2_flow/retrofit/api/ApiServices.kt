package ir.digireza.s2_flow.retrofit.api

import ir.digireza.s2_flow.retrofit.model.ResponseGenresList
import ir.digireza.s2_flow.retrofit.model.ResponseMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("movies")
    suspend fun moviesList(@Query("page") page: Int): Response<ResponseMovies>

    @GET("genres")
    suspend fun genresList(): Response<ResponseGenresList>
}