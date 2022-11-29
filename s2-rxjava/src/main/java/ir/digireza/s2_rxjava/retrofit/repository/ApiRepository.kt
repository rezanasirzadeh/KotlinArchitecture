package ir.digireza.s2_rxjava.retrofit.repository

import ir.digireza.s2_rxjava.retrofit.api.ApiServices
import dagger.hilt.android.scopes.ActivityScoped
import java.net.IDN
import javax.inject.Inject

@ActivityScoped
class ApiRepository @Inject constructor(private val api: ApiServices) {
    fun searchMovies(search: String) = api.searchMovies(search)
    fun moviesApi1(page: Int) = api.moviesApi1(page)
    fun moviesApi2(page: Int) = api.moviesApi2(page)
    fun movieDetail(id: Int) = api.movieDetail(id)
    fun genreList() = api.genresList()
}