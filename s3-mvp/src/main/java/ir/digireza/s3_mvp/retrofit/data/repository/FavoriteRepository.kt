package ir.digireza.s3_mvp.retrofit.data.repository

import ir.digireza.s3_mvp.retrofit.data.database.FoodDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: FoodDao) {
    fun loadAllFoods() = dao.getAllFoods()
}