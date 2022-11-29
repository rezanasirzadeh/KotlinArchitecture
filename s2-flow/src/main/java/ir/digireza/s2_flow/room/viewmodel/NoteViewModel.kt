package ir.digireza.s2_flow.room.viewmodel

import ir.digireza.s2_flow.room.db.NoteModel
import ir.digireza.s2_flow.room.repository.NoteRepository
import ir.digireza.s2_flow.utils.MyResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    val notesList = MutableLiveData<MyResponse<List<NoteModel>>>()

    fun saveNote(note: NoteModel) = viewModelScope.launch {
        repository.saveNote(note)
    }

    fun deleteNote(note: NoteModel) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun getAllNotes() = viewModelScope.launch {
        notesList.postValue(MyResponse.loading())
        repository.getAllNotes()
            .onEach { delay(2000) }
            .catch { e ->
                notesList.postValue(MyResponse.error(e.message.toString()))
            }.collect { notesList.postValue(MyResponse.success(it)) }
    }
}