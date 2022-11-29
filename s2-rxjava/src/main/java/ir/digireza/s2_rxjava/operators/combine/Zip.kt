package ir.digireza.s2_rxjava.operators.combine

import ir.digireza.s2_rxjava.databinding.ActivityMainBinding
import ir.digireza.s2_rxjava.utils.Constants
import ir.digireza.s2_rxjava.utils.User
import ir.digireza.s2_rxjava.utils.Utils
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class Zip {
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Get data
            getData.setOnClickListener {
                //Zip
                Observable.zip(maleObservable(), femaleObservable()) {
                        t1, t2 -> "${t1.name} -> ${t2.name}" }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it.toString()) }
            }
        }
    }

    private fun maleObservable(): Observable<User> {
        return Observable.create<User> { emitter ->
            Utils.maleList().forEach {
                if (!emitter.isDisposed) {
                    Thread.sleep(1000)
                    emitter.onNext(it)
                }
            }

            if (!emitter.isDisposed) {
                emitter.onComplete()
            }

        }.subscribeOn(Schedulers.io())
    }

    private fun femaleObservable(): Observable<User> {
        return Observable.create<User> { emitter ->
            Utils.femaleList().forEach {
                if (!emitter.isDisposed) {
                    Thread.sleep(600)
                    emitter.onNext(it)
                }
            }

            if (!emitter.isDisposed) {
                emitter.onComplete()
            }

        }.subscribeOn(Schedulers.io())
    }
    */
}