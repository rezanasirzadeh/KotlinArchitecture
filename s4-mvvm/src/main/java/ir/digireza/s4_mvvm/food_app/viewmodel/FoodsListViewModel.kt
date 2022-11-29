package ir.digireza.s4_mvvm.food_app.viewmodel

import ir.digireza.s4_mvvm.food_app.data.model.ResponseCategoriesList
import ir.digireza.s4_mvvm.food_app.data.model.ResponseFoodsList
import ir.digireza.s4_mvvm.food_app.data.repository.FoodsListRepository
import ir.digireza.s4_mvvm.food_app.utils.MyResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodsListViewModel @Inject constructor(private val repository: FoodsListRepository) : ViewModel() {

    init {
        loadFoodRandom()
        loadCategoriesList()
    }

    val randomFoodData = MutableLiveData<List<ResponseFoodsList.Meal>>()
    fun loadFoodRandom() = viewModelScope.launch(Dispatchers.IO) {
        repository.randomFood().collect {
            randomFoodData.postValue(it.body()?.meals!!)
        }
    }

    val filtersListData = MutableLiveData<MutableList<Char>>()
    fun loadFilterList() = viewModelScope.launch(Dispatchers.IO) {
        val letters = listOf('A'..'Z').flatten().toMutableList()
        filtersListData.postValue(letters)
    }

    val categoriesListData = MutableLiveData<MyResponse<ResponseCategoriesList>>()
    fun loadCategoriesList() = viewModelScope.launch(Dispatchers.IO) {
        repository.categoriesList().collect { categoriesListData.postValue(it) }
    }

    val foodsListData = MutableLiveData<MyResponse<ResponseFoodsList>>()
    fun loadFoodsList(letter: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.foodsList(letter).collect { foodsListData.postValue(it) }
    }

    fun loadFoodBySearch(letter: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.foodsBySearch(letter).collect { foodsListData.postValue(it) }
    }

    fun loadFoodByCategory(letter: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.foodsByCategory(letter).collect { foodsListData.postValue(it) }
    }
}