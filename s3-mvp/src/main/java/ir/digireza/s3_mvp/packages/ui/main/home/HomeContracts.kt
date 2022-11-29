package ir.digireza.s3_mvp.packages.ui.main.home

import ir.digireza.s3_mvp.packages.data.model.home.ResponseMoviesList
import ir.digireza.s3_mvp.packages.ui.base.BaseView

interface HomeContracts {
    interface View : BaseView {
        fun loadTopMovies(data: ResponseMoviesList)
        fun loadCategories()
        fun loadLastMovies()
    }

    interface Presenter {
        fun callTopMovies()
        fun callCategories()
        fun callLastMovies()
    }
}