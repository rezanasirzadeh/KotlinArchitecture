package ir.digireza.s2_flow.room.db

import ir.digireza.s2_flow.utils.NOTE_TABLE
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNotes(): Flow<List<NoteModel>>
}