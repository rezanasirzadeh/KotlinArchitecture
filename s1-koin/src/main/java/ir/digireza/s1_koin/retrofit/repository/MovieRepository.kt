package ir.digireza.s1_koin.retrofit.repository

import ir.digireza.s1_koin.retrofit.api.ApiServices

class MovieRepository(private val api: ApiServices) {

    suspend fun showMovies() = api.allMovies()
}