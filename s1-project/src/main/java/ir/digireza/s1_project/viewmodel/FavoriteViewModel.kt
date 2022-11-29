package ir.digireza.s1_project.viewmodel

import ir.digireza.s1_project.db.MovieEntity
import ir.digireza.s1_project.models.home.ResponseMoviesList
import ir.digireza.s1_project.repository.FavoriteRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  FavoriteViewModel @Inject constructor(private val repository: FavoriteRepository) : ViewModel() {

    val favoriteList = MutableLiveData<MutableList<MovieEntity>>()
    val empty = MutableLiveData<Boolean>()

    fun loadFavoriteList() = viewModelScope.launch {
        val list = repository.allFavoriteList()
        if (list.isNotEmpty()) {
            favoriteList.postValue(list)
            empty.postValue(false)
        } else {
            empty.postValue(true)
        }
    }
}