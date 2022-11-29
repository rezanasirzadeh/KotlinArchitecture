package ir.digireza.s2_flow.retrofit.repository

import ir.digireza.s2_flow.retrofit.api.ApiServices
import ir.digireza.s2_flow.retrofit.model.ResponseGenresList
import ir.digireza.s2_flow.retrofit.model.ResponseMovies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryWithGenres @Inject constructor(private val api: ApiServices) {

    fun moviesList(): Flow<Response<ResponseMovies>> {
        return flow { emit(api.moviesList(1)) }
    }

    fun genresList(): Flow<Response<ResponseGenresList>> {
        return flow { emit(api.genresList()) }
    }
}