package ir.digireza.s1_koin.retrofit.viewmodel

import ir.digireza.s1_koin.retrofit.model.ResponseMovies
import ir.digireza.s1_koin.retrofit.repository.MovieRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MovieRepository) : ViewModel() {

    val moviesList = MutableLiveData<ResponseMovies>()
    val loading = MutableLiveData<Boolean>()

    fun loadMovies() = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.showMovies()
        if (response.isSuccessful) {
            moviesList.postValue(response.body())
        }
        loading.postValue(false)
    }
}