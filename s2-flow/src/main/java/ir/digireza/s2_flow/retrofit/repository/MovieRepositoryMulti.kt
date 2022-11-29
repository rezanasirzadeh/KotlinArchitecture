package ir.digireza.s2_flow.retrofit.repository

import ir.digireza.s2_flow.retrofit.api.ApiServices
import ir.digireza.s2_flow.retrofit.model.ResponseMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryMulti @Inject constructor(private val api:ApiServices){

    suspend fun movieListPage1():Flow<Response<ResponseMovies>>{
        return flow { emit(api.moviesList(1)) }
    }

    suspend fun movieListPage2():Flow<Response<ResponseMovies>>{
        return flow { emit(api.moviesList(2)) }
    }
}