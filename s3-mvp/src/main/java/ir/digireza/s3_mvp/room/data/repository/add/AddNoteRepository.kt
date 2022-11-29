package ir.digireza.s3_mvp.room.data.repository.add

import ir.digireza.s3_mvp.room.data.database.NoteDao
import ir.digireza.s3_mvp.room.data.model.NoteEntity
import javax.inject.Inject

class AddNoteRepository @Inject constructor(private val dao: NoteDao) {
    fun saveNote(entity: NoteEntity) = dao.saveNote(entity)
    fun detailNote(id: Int) = dao.getNote(id)
    fun updateNote(entity: NoteEntity) = dao.updateNote(entity)
}