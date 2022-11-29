package ir.digireza.s4_mvvm.food_app.viewmodel

import ir.digireza.s4_mvvm.food_app.data.database.FoodEntity
import ir.digireza.s4_mvvm.food_app.data.repository.FoodsFavoriteRepository
import ir.digireza.s4_mvvm.note_app.utils.DataStatus
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsFavoriteViewModel @Inject constructor(private val repository: FoodsFavoriteRepository) : ViewModel() {

    val favoritesListData = MutableLiveData<DataStatus<List<FoodEntity>>>()
    fun loadFavorites() = viewModelScope.launch(Dispatchers.IO) {
        repository.foodsList().collect {
            favoritesListData.postValue(DataStatus.success(it, it.isEmpty()))
        }
    }
}