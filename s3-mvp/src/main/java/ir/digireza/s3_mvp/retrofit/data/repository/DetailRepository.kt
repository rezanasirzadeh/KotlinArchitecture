package ir.digireza.s3_mvp.retrofit.data.repository

import ir.digireza.s3_mvp.retrofit.data.database.FoodDao
import ir.digireza.s3_mvp.retrofit.data.database.FoodEntity
import ir.digireza.s3_mvp.retrofit.data.server.ApiServices
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: ApiServices, private val dao: FoodDao) {
    fun loadFoodDetail(id: Int) = api.foodDetail(id)
    fun saveFood(entity: FoodEntity) = dao.saveFood(entity)
    fun deleteFood(entity: FoodEntity) = dao.deleteFood(entity)
    fun existsFood(id: Int) = dao.existsFood(id)
}