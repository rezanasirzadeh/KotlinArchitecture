package ir.digireza.s2_rxjava.room.db

import ir.digireza.s2_rxjava.utils.Constants
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(entity: NoteEntity): Completable

    @Delete
    fun deleteNote(entity: NoteEntity): Completable

    @Query("SELECT * FROM ${Constants.NOTE_TABLE}")
    fun getAllNotes(): Observable<List<NoteEntity>>
}