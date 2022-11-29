package ir.digireza.s3_mvp.room.data.model

import ir.digireza.s3_mvp.room.utils.NOTE_TABLE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var desc: String = "",
    var category: String = "",
    var priority: String = ""
)