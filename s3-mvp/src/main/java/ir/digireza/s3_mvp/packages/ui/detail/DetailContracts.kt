package ir.digireza.s3_mvp.packages.ui.detail

interface DetailContracts {

    interface View {
        fun loadDetailData()
        fun saveState()
        fun emptyList()
    }

    interface Presenter {
        fun callDetailPage(id: Int)
        fun saveFavorite()
    }
}