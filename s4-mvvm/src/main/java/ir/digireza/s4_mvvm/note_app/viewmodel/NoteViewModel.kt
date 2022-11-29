package ir.digireza.s4_mvvm.note_app.viewmodel

import ir.digireza.s4_mvvm.note_app.data.model.NoteEntity
import ir.digireza.s4_mvvm.note_app.data.repository.NoteRepository
import ir.digireza.s4_mvvm.note_app.utils.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    //Spinners
    val categoriesList = MutableLiveData<MutableList<String>>()
    val prioritiesList = MutableLiveData<MutableList<String>>()
    val noteData = MutableLiveData<DataStatus<NoteEntity>>()

    fun loadCategoriesData() = viewModelScope.launch(Dispatchers.IO) {
        val data = mutableListOf(WORK, EDUCATION, HOME, HEALTH)
        categoriesList.postValue(data)
    }

    fun loadPrioritiesData() = viewModelScope.launch(Dispatchers.IO) {
        val data = mutableListOf(HIGH, NORMAL, LOW)
        prioritiesList.postValue(data)
    }

    fun saveEditNote(isEdit: Boolean, entity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        if (isEdit) {
            repository.updateNote(entity)
        } else {
            repository.saveNote(entity)
        }
    }

    fun getData(id: Int) = viewModelScope.launch {
        repository.getNote(id).collect {
            noteData.postValue(DataStatus.success(it, false))
        }
    }
}