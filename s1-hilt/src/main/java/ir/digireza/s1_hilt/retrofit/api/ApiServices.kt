package ir.digireza.s1_hilt.retrofit.api

import ir.digireza.s1_hilt.retrofit.model.ResponseMoviesList
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("movies")
    fun moviesList(): Call<ResponseMoviesList>
}