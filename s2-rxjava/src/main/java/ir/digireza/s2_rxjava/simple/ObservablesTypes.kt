package ir.digireza.s2_rxjava.simple

import ir.digireza.s2_rxjava.databinding.ActivityMainBinding
import ir.digireza.s2_rxjava.utils.Constants
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ObservablesTypes {
/*
    //Other
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Get data
            getData.setOnClickListener {
                //Create observable
                maybeJust()
            }
        }
    }

    private fun observableRange() {
        disposable = Observable.range(1, 1000000)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e(Constants.TAG, "$it")
            }, { e ->
                Log.e(Constants.TAG, "Err : ${e.message}")
            }, {
                Log.e(Constants.TAG, "Completed")
            })
    }

    private fun flowableRange() {
        disposable = Flowable.range(1, 1000000)
            //.onBackpressureBuffer()
            //.onBackpressureDrop()
            .onBackpressureLatest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread(), false, 10)
            .subscribe({
                Log.e(Constants.TAG, "$it")
            }, { e ->
                Log.e(Constants.TAG, "Err : ${e.message}")
            }, {
                Log.e(Constants.TAG, "Completed")
            })
    }

    private fun singleJust() {
        disposable = Single.just("Reza Nasirzadeh")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e(Constants.TAG, it)
            }, {
                Log.e(Constants.TAG, "Err : ${it.message}")
            })
*/
/*            .subscribe(object : SingleObserver<String>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: String) {

                }

                override fun onError(e: Throwable) {

                }
            })*//*

    }

    private fun completable() {
        disposable = Completable.fromSingle(Single.just(5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
*/
/*            .subscribe(object : CompletableObserver{
                override fun onSubscribe(d: Disposable) {
                    TODO("Not yet implemented")
                }

                override fun onComplete() {
                }

                override fun onError(e: Throwable) {
                }

            })*//*

            .subscribe({
                Log.e(Constants.TAG, "Completed")
            }, {
                Log.e(Constants.TAG, "Err : ${it.message}")
            })
    }

    private fun maybeJust() {
        disposable = Maybe.just(999)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            */
/*.subscribe(object : MaybeObserver<Int> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: Int) {
                    Log.e(Constants.TAG, "$t")
                }

                override fun onError(e: Throwable) {
                    Log.e(Constants.TAG, "Err : ${e.message}")
                }

                override fun onComplete() {
                    Log.e(Constants.TAG, "Completed")
                }
            })*//*

            .subscribe({
                Log.e(Constants.TAG, "$it")
            }, {
                Log.e(Constants.TAG, "Err : ${it.message}")
            }, {
                Log.e(Constants.TAG, "Completed")
            })
    }

    private fun maybeEmpty() {
        disposable = Maybe.empty<Int>()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
*/
/*            .subscribe(object : MaybeObserver<Int>{
                override fun onSubscribe(d: Disposable) {
                }

                override fun onSuccess(t: Int) {
                    Log.e(Constants.TAG, "$t")
                }

                override fun onError(e: Throwable) {
                    Log.e(Constants.TAG, "Err : ${e.message}")
                }

                override fun onComplete() {
                    Log.e(Constants.TAG, "Completed")
                }
            })*//*

            .subscribe({
                Log.e(Constants.TAG, "$it")
            }, {
                Log.e(Constants.TAG, "Err : ${it.message}")
            }, {
                Log.e(Constants.TAG, "Completed")
            })
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
}*/
}
