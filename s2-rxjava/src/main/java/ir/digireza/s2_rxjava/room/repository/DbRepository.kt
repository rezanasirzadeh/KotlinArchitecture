package ir.digireza.s2_rxjava.room.repository

import ir.digireza.s2_rxjava.room.db.NoteDao
import ir.digireza.s2_rxjava.room.db.NoteEntity
import javax.inject.Inject

class DbRepository @Inject constructor(private val dao: NoteDao) {
    fun saveNote(entity: NoteEntity) = dao.saveNote(entity)
    fun deleteNote(entity: NoteEntity) = dao.deleteNote(entity)
    fun getAllNotes() = dao.getAllNotes()
}