package ir.digireza.s2_flow.retrofit.viewmodel

import ir.digireza.s2_flow.retrofit.model.ResponseMovies
import ir.digireza.s2_flow.retrofit.repository.MovieRepositoryMulti
import ir.digireza.s2_flow.utils.MyResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModelSeries @Inject constructor(private val repository: MovieRepositoryMulti) : ViewModel() {

    val moviesList = MutableLiveData<MyResponse<List<ResponseMovies.Data>>>()

    fun loadMovies() = viewModelScope.launch {
        moviesList.postValue(MyResponse.loading())
        val moviesFromApi = mutableListOf<ResponseMovies.Data>()
        repository.movieListPage1().flatMapConcat { page1 ->
            moviesFromApi.addAll(page1.body()!!.data)
            repository.movieListPage2()
        }.catch { moviesList.postValue(MyResponse.error(it.message.toString())) }
            .flowOn(Dispatchers.IO)
            .collect { page2 ->
                moviesFromApi.addAll(page2.body()!!.data)
                moviesList.postValue(MyResponse.success(moviesFromApi))
            }
    }
}