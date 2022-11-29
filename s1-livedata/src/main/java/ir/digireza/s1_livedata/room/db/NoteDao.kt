package ir.digireza.s1_livedata.room.db

import ir.digireza.s1_livedata.room.utils.NOTE_TABLE
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note: NoteEntity)

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNotes(): LiveData<MutableList<NoteEntity>>
}