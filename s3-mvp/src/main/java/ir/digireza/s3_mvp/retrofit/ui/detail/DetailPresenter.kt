package ir.digireza.s3_mvp.retrofit.ui.detail

import ir.digireza.s3_mvp.base.BasePresenterImpl
import ir.digireza.s3_mvp.retrofit.data.database.FoodEntity
import ir.digireza.s3_mvp.retrofit.data.repository.DetailRepository
import ir.digireza.s3_mvp.utils.applyIoScheduler
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val repository: DetailRepository, val view: DetailContracts.View) :
    BasePresenterImpl(), DetailContracts.Presenter {

    override fun callDetailApi(id: Int) {
        if (view.checkInternet()) {
            view.showLoading()
            disposable = repository.loadFoodDetail(id)
                .applyIoScheduler()
                .subscribe({ response ->
                    view.hideLoading()
                    when (response.code()) {
                        in 200..202 -> {
                            response.body()?.let { itBody ->
                                if (itBody.meals != null) {
                                    if (itBody.meals.isNotEmpty()) {
                                        view.loadDetail(itBody)
                                    }
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

    override fun saveFood(entity: FoodEntity) {
        disposable = repository.saveFood(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.updateFavorite(true)
            }
    }

    override fun deleteFood(entity: FoodEntity) {
        disposable = repository.deleteFood(entity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.updateFavorite(false)
            }
    }

    override fun checkFavorite(id: Int) {
        disposable = repository.existsFood(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.updateFavorite(it)
            }
    }
}