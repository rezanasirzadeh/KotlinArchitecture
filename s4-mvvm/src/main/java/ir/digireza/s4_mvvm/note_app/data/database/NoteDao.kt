package ir.digireza.s4_mvvm.note_app.data.database

import ir.digireza.s4_mvvm.note_app.data.model.NoteEntity
import ir.digireza.s4_mvvm.note_app.utils.NOTE_TABLE
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(entity: NoteEntity)

    @Delete
    suspend fun deleteNote(entity: NoteEntity)

    @Update
    suspend fun updateNote(entity: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNotes(): Flow<MutableList<NoteEntity>>

    @Query("SELECT * FROM $NOTE_TABLE WHERE id == :id")
    fun getNote(id: Int): Flow<NoteEntity>

    @Query("SELECT * FROM $NOTE_TABLE WHERE priority == :priority")
    fun filetNote(priority: String): Flow<MutableList<NoteEntity>>

    @Query("SELECT * FROM $NOTE_TABLE WHERE title LIKE '%' || :title || '%' ")
    fun searchNote(title: String): Flow<MutableList<NoteEntity>>
}