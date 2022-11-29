package ir.digireza.s3_mvp.packages.ui.main.favorites

interface FavoriteContracts {

    interface View {
        fun emptyList()
        fun loadFavoritesData()
    }

    interface Presenter {
        fun getFavoritesList()
    }

}