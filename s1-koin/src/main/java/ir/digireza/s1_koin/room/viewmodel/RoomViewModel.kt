package ir.digireza.s1_koin.room.viewmodel

import ir.digireza.s1_koin.room.db.NoteModel
import ir.digireza.s1_koin.room.repository.RoomRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.dsl.module

class RoomViewModel(private val repository: RoomRepository) : ViewModel() {
    val notesList = repository.getAllNotes()

    fun saveNote(note: NoteModel) = viewModelScope.launch {
        repository.saveNote(note)
    }
}

/*
val roomViewModelModule = module {
    factory { RoomViewModel(get()) }
}*/
