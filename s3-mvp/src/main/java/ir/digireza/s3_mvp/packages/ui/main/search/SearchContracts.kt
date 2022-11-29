package ir.digireza.s3_mvp.packages.ui.main.search

import ir.digireza.s3_mvp.packages.data.model.home.ResponseMoviesList
import ir.digireza.s3_mvp.packages.ui.base.BaseView

interface SearchContracts {

    interface View : BaseView {
        fun loadSearchData(data: ResponseMoviesList)
    }

    interface Presenter {
        fun callSearch(search: String)
    }
}