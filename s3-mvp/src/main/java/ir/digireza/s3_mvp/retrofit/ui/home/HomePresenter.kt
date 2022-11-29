package ir.digireza.s3_mvp.retrofit.ui.home

import ir.digireza.s3_mvp.base.BasePresenterImpl
import ir.digireza.s3_mvp.retrofit.data.repository.HomeRepository
import ir.digireza.s3_mvp.utils.applyIoScheduler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject constructor(private val repository: HomeRepository, val view: HomeContracts.View) :
    BasePresenterImpl(), HomeContracts.Presenter {

    override fun callFoodRandom() {
        if (view.checkInternet()) {
            disposable = repository.loadFoodRandom()
                .applyIoScheduler()
                .subscribe({ response ->
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let {
                                view.loadFoodRandom(it)
                            }
                        }
                        422 -> {

                        }
                        in 400..499 -> {

                        }
                        in 500..599 -> {

                        }
                    }

                }, {
                    view.serverError(it.message.toString())
                })
        } else {
            view.internetError(false)
        }
    }

    override fun callCategoriesList() {
        if (view.checkInternet()) {
            view.showLoading()
            disposable = repository.loadCategoriesList()
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let {
                                if (it.categories.isNotEmpty()) {
                                    view.loadCategories(it)
                                }
                            }
                        }
                    }

                }, {
                    view.hideLoading()
                    view.serverError(it.message.toString())
                })
        } else {
            view.internetError(false)
        }
    }

    override fun callFoodsList(letter: String) {
        if (view.checkInternet()) {
            view.foodsLoadingState(true)
            disposable = repository.loadFoodsList(letter)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.foodsLoadingState(false)
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let {
                                if (it.meals!!.isNotEmpty()) {
                                    view.loadFoodsList(it)
                                }
                            }
                        }
                    }

                }, {
                    view.foodsLoadingState(false)
                    view.serverError(it.message.toString())
                })
        } else {
            view.internetError(false)
        }
    }

    override fun callSearchFood(letter: String) {
        if (view.checkInternet()) {
            view.foodsLoadingState(true)
            disposable = repository.loadSearchFood(letter)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.foodsLoadingState(false)
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                if (itBody.meals != null) {
                                    if (itBody.meals.isNotEmpty()) {
                                        view.loadFoodsList(itBody)
                                    }
                                } else {
                                    view.emptyList()
                                }
                            }
                        }
                    }

                }, {
                    view.foodsLoadingState(false)
                    view.serverError(it.message.toString())
                })
        } else {
            view.internetError(false)
        }
    }

    override fun callFoodsByCategory(letter: String) {
        if (view.checkInternet()) {
            view.foodsLoadingState(true)
            disposable = repository.loadFoodsByCategory(letter)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.foodsLoadingState(false)
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let {
                                if (it.meals!!.isNotEmpty()) {
                                    view.loadFoodsList(it)
                                }
                            }
                        }
                    }

                }, {
                    view.foodsLoadingState(false)
                    view.serverError(it.message.toString())
                })
        } else {
            view.internetError(false)
        }
    }
}