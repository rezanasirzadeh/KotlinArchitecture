package ir.digireza.s1_livedata.room.db

import ir.digireza.s1_livedata.room.utils.NOTE_TABLE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var desc: String = ""
)