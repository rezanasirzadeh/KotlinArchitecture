package ir.digireza.s1_hilt.retrofit.repository

import ir.digireza.s1_hilt.retrofit.api.ApiServices
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(private val api: ApiServices) {
    fun getAllMovies() = api.moviesList()
}