package ir.digireza.s3_mvp.room.data.database

import ir.digireza.s3_mvp.room.data.model.NoteEntity
import ir.digireza.s3_mvp.room.utils.NOTE_TABLE
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(entity: NoteEntity): Completable

    @Delete
    fun deleteNote(entity: NoteEntity): Completable

    @Update
    fun updateNote(entity: NoteEntity): Completable

    @Query("SELECT * FROM $NOTE_TABLE")
    fun getAllNotes(): Observable<List<NoteEntity>>

    @Query("SELECT * FROM $NOTE_TABLE WHERE id == :id")
    fun getNote(id: Int): Observable<NoteEntity>

    @Query("SELECT * FROM $NOTE_TABLE WHERE priority == :priority")
    fun filetNote(priority: String): Observable<List<NoteEntity>>

    @Query("SELECT * FROM $NOTE_TABLE WHERE title LIKE '%' || :title || '%' ")
    fun searchNote(title: String): Observable<List<NoteEntity>>
}