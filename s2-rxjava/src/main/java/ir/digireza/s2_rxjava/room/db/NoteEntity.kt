package ir.digireza.s2_rxjava.room.db

import ir.digireza.s2_rxjava.utils.Constants
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Constants.NOTE_TABLE)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String = ""
)
