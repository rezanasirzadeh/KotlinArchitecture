package ir.digireza.s1_retrofit.server

import ir.digireza.s1_retrofit.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("movies")
    fun moviesList(@Query("page") page: Int): Call<ResponseMoviesList>

    @GET("movies/{movie_id}")
    fun movieDetail(@Path("movie_id") id: Int): Call<ResponseMovieDetail>

    @GET("genres/{genre_id}/movies")
    fun genresMovies(@Path("genre_id") id: Int, @Query("page") page: Int): Call<ResponseGenresMovies>

    @POST("register")
    fun userRegister(@Body body: BodyUserRegister): Call<ResponseUserRegister>
}