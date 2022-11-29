package ir.digireza.s3_mvp.retrofit.data.server

import ir.digireza.s3_mvp.retrofit.data.model.home.ResponseCategoriesList
import ir.digireza.s3_mvp.retrofit.data.model.home.ResponseFoodsList
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("random.php")
    fun foodRandom(): Single<Response<ResponseFoodsList>>

    @GET("categories.php")
    fun categoriesList(): Single<Response<ResponseCategoriesList>>

    @GET("search.php")
    fun foodsList(@Query("f") letter: String): Single<Response<ResponseFoodsList>>

    @GET("search.php")
    fun searchFood(@Query("s") letter: String): Single<Response<ResponseFoodsList>>

    @GET("filter.php")
    fun foodsByCategory(@Query("c") letter: String): Single<Response<ResponseFoodsList>>

    @GET("lookup.php")
    fun foodDetail(@Query("i") id: Int): Single<Response<ResponseFoodsList>>
}