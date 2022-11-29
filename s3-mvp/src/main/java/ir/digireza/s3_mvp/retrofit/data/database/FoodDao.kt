package ir.digireza.s3_mvp.retrofit.data.database

import ir.digireza.s3_mvp.retrofit.utils.FOOD_DB_TABLE
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveFood(entity: FoodEntity): Completable

    @Delete
    fun deleteFood(entity: FoodEntity): Completable

    @Query("SELECT * FROM $FOOD_DB_TABLE")
    fun getAllFoods(): Observable<MutableList<FoodEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM $FOOD_DB_TABLE WHERE id = :id)")
    fun existsFood(id: Int): Observable<Boolean>
}