package ir.digireza.s1_hilt.room.repository

import ir.digireza.s1_hilt.room.db.NoteDao
import ir.digireza.s1_hilt.room.db.NoteModel
import javax.inject.Inject

class DbRepository @Inject constructor(private val dao: NoteDao) {
    fun saveNote(entity: NoteModel) = dao.saveNote(entity)
    fun getAllNotes() = dao.getAllNote()
}