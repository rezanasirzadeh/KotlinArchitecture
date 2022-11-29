package ir.digireza.s2_flow.room.repository

import ir.digireza.s2_flow.room.db.NoteDao
import ir.digireza.s2_flow.room.db.NoteModel
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NoteDao) {
    suspend fun saveNote(entity: NoteModel) = dao.saveNote(entity)
    suspend fun deleteNote(entity: NoteModel) = dao.deleteNote(entity)
    fun getAllNotes() = dao.getAllNotes()
}