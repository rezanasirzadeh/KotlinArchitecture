package ir.digireza.s3_mvp.retrofit.ui.favorite

import ir.digireza.s3_mvp.base.BasePresenter
import ir.digireza.s3_mvp.retrofit.data.database.FoodEntity

interface FavoriteContracts {
    interface View {
        fun showAllFoods(list: MutableList<FoodEntity>)
    }

    interface Presenter : BasePresenter {
        fun loadAllFood()
    }
}