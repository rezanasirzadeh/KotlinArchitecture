package ir.digireza.s3_mvp.retrofit.ui.home

import ir.digireza.s3_mvp.base.BasePresenter
import ir.digireza.s3_mvp.base.BaseView
import ir.digireza.s3_mvp.retrofit.data.model.home.ResponseCategoriesList
import ir.digireza.s3_mvp.retrofit.data.model.home.ResponseFoodsList

interface HomeContracts {
    interface View : BaseView {
        fun loadFoodRandom(data: ResponseFoodsList)
        fun loadCategories(data: ResponseCategoriesList)
        fun loadFoodsList(data: ResponseFoodsList)
        fun foodsLoadingState(isShown: Boolean)
        fun emptyList()
    }

    interface Presenter : BasePresenter {
        fun callFoodRandom()
        fun callCategoriesList()
        fun callFoodsList(letter: String)
        fun callSearchFood(letter: String)
        fun callFoodsByCategory(letter: String)
    }
}