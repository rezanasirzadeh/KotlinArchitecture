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
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModelParallel @Inject constructor(private val repository: MovieRepositoryMulti) : ViewModel() {

    val moviesList = MutableLiveData<MyResponse<List<ResponseMovies.Data>>>()

    fun loadMovies() = viewModelScope.launch {
        repository.movieListPage1().zip(repository.movieListPage2()) { page1, page2 ->
            moviesList.postValue(MyResponse.loading())

            val movieFromApi = mutableListOf<ResponseMovies.Data>()
            page1.body()?.data?.let { movieFromApi.addAll(it) }
            page2.body()?.data?.let { movieFromApi.addAll(it) }
            return@zip movieFromApi
        }.catch { moviesList.postValue(MyResponse.error(it.message.toString())) }
            .flowOn(Dispatchers.IO)
            .collect{
                moviesList.postValue(MyResponse.success(it))
            }
    }

}