package ir.digireza.s4_mvvm.food_app.data.database

import ir.digireza.s4_mvvm.food_app.utils.FOOD_DB_TABLE
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = FOOD_DB_TABLE)
data class FoodEntity(
    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var img: String = ""
)