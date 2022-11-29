package ir.digireza.s2_flow.retrofit.viewmodel

import ir.digireza.s2_flow.retrofit.model.ResponseMovies
import ir.digireza.s2_flow.retrofit.repository.MovieRepository
import ir.digireza.s2_flow.utils.MyResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val moviesList = MutableLiveData<MyResponse<ResponseMovies>>()

    fun loadMovies() = viewModelScope.launch {
        repository.moviesList().collect { moviesList.postValue(it) }
    }

}