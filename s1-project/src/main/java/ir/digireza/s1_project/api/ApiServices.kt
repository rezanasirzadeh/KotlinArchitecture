package ir.digireza.s1_project.api

import ir.digireza.s1_project.models.detail.ResponseDetail
import ir.digireza.s1_project.models.home.ResponseGenresList
import ir.digireza.s1_project.models.home.ResponseMoviesList
import ir.digireza.s1_project.models.register.BodyRegister
import ir.digireza.s1_project.models.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {
    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun genresList(): Response<ResponseGenresList>

    @GET("movies")
    suspend fun moviesLastList(): Response<ResponseMoviesList>

    @GET("movies")
    suspend fun searchMovie(@Query("q") name: String): Response<ResponseMoviesList>

    @GET("movies/{movie_id}")
    suspend fun detailMovie(@Path("movie_id") id: Int): Response<ResponseDetail>
}