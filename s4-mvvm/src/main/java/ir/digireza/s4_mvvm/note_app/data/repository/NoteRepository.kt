package ir.digireza.s4_mvvm.note_app.data.repository

import ir.digireza.s4_mvvm.note_app.data.database.NoteDao
import ir.digireza.s4_mvvm.note_app.data.model.NoteEntity
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NoteDao) {
    suspend fun saveNote(entity: NoteEntity) = dao.saveNote(entity)
    suspend fun updateNote(entity: NoteEntity) = dao.updateNote(entity)
    fun getNote(id: Int) = dao.getNote(id)
}