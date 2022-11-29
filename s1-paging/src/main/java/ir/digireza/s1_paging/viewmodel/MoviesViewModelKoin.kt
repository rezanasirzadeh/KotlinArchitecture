package ir.digireza.s1_paging.viewmodel

import ir.digireza.s1_paging.paging.MoviesPagingSource
import ir.digireza.s1_paging.paging.MoviesPagingSourceKoin
import ir.digireza.s1_paging.repository.MoviesRepository
import ir.digireza.s1_paging.repository.MoviesRepositoryKoin
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MoviesViewModelKoin (private val repository: MoviesRepositoryKoin) : ViewModel() {

    val moviesList = Pager(PagingConfig(1)){
        MoviesPagingSourceKoin(repository)
    }.flow.cachedIn(viewModelScope)

}