package ir.digireza.s1_koin.room.repository

import ir.digireza.s1_koin.room.db.NoteDao
import ir.digireza.s1_koin.room.db.NoteModel
import org.koin.dsl.module

class RoomRepository(private val dao: NoteDao) {
    suspend fun saveNote(note: NoteModel) = dao.saveNote(note)
    fun getAllNotes() = dao.getAllNote()
}

/*
val repositoryModule = module {
    factory { RoomRepository(get()) }
}*/
