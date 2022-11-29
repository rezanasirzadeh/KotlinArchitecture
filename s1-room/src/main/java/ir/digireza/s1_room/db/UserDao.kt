package ir.digireza.s1_room.db

import ir.digireza.s1_room.utils.Constants
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM ${Constants.USER_TABLE} ORDER BY userId DESC")
    fun getAllUser(): MutableList<UserEntity>

    @Query("SELECT * FROM ${Constants.USER_TABLE} WHERE userId LIKE :id")
    fun getUser(id: Int): UserEntity

    @Query("DELETE FROM ${Constants.USER_TABLE}")
    fun getDeleteAllUser()
}