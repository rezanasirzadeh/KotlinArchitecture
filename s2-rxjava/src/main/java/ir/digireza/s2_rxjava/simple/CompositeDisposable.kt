package ir.digireza.s2_rxjava.simple

import ir.digireza.s2_rxjava.databinding.ActivityMainBinding
import ir.digireza.s2_rxjava.utils.Constants
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class CompositeDisposable {
/*
    //Other
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Get data
            getData.setOnClickListener {
                //Male
                compositeDisposable.add(maleObservable().subscribeWith(nameObserver()))
                //Female
                compositeDisposable.add(femaleObservable().subscribeWith(nameObserver()))
            }
        }
    }

    private fun maleObservable(): Observable<String> {
        return Observable.just("Reza", "Ali", "Mohsen", "Naser")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun femaleObservable(): Observable<String> {
        return Observable.just("Nooshin", "Zahra", "Morvarid")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun nameObserver(): DisposableObserver<String> {
        return object : DisposableObserver<String>() {
            override fun onNext(t: String) {
                Log.e(Constants.TAG, t)
            }

            override fun onError(e: Throwable) {
                Log.e(Constants.TAG, e.message.toString())
            }

            override fun onComplete() {
                Log.e(Constants.TAG, "onCompleted")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }*/

}