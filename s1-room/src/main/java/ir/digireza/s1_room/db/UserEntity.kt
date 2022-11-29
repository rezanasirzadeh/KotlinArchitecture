package ir.digireza.s1_room.db

import ir.digireza.s1_room.utils.Constants
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Constants.USER_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    @ColumnInfo(name = "user_name")
    val userName: String,
    val userAge: Int
)