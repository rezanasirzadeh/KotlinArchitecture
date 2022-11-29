package ir.digireza.s2_rxjava.retrofit.api

import ir.digireza.s2_rxjava.retrofit.model.ResponseDetail
import ir.digireza.s2_rxjava.retrofit.model.ResponseGenres
import ir.digireza.s2_rxjava.retrofit.model.ResponseMovies
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("movies")
    fun searchMovies(@Query("q") search: String): Observable<Response<ResponseMovies>>

    @GET("movies")
    fun moviesApi1(@Query("page") page: Int): Single<Response<ResponseMovies>>

    @GET("movies")
    fun moviesApi2(@Query("page") page: Int): Single<Response<ResponseMovies>>

    @GET("movies/{movie_id}")
    fun movieDetail(@Path("movie_id") id: Int): Single<Response<ResponseDetail>>

    @GET("genres")
    fun genresList(): Single<Response<ResponseGenres>>
}