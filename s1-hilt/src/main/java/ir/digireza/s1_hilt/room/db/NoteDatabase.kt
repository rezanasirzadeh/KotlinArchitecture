package ir.digireza.s1_hilt.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}