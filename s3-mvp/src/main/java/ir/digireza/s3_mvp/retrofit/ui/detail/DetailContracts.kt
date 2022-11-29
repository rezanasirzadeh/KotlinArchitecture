package ir.digireza.s3_mvp.retrofit.ui.detail

import ir.digireza.s3_mvp.base.BasePresenter
import ir.digireza.s3_mvp.base.BaseView
import ir.digireza.s3_mvp.retrofit.data.database.FoodEntity
import ir.digireza.s3_mvp.retrofit.data.model.home.ResponseFoodsList

interface DetailContracts {
    interface View : BaseView {
        fun loadDetail(data: ResponseFoodsList)
        fun updateFavorite(isAdded: Boolean)
    }

    interface Presenter : BasePresenter {
        fun callDetailApi(id: Int)
        fun saveFood(entity: FoodEntity)
        fun deleteFood(entity: FoodEntity)
        fun checkFavorite(id: Int)
    }
}