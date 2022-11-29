package ir.digireza.s4_mvvm.note_app.data.database

import ir.digireza.s4_mvvm.note_app.data.model.NoteEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}