package ir.digireza.s1_paging.viewmodel

import ir.digireza.s1_paging.paging.MoviesPagingSource
import ir.digireza.s1_paging.repository.MoviesRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    val moviesList = Pager(PagingConfig(1)){
        MoviesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}