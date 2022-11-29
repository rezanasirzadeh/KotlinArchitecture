package ir.digireza.s1_paging.repository

import ir.digireza.s1_paging.api.ApiServices
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: ApiServices) {
    suspend fun getAllMovies(page: Int) = api.getAllMovies(page)
}