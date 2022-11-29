package ir.digireza.s1_koin.room.db

import ir.digireza.s1_koin.NOTE_TABLE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NOTE_TABLE)
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = ""
)
