package ir.digireza.s2_rxjava.operators.transforming

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class BufferWindow {
/*
    //Buffer
*//*                Observable.range(1, 10)
                    .buffer(3)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<List<Int>>{
                        override fun onSubscribe(d: Disposable) {
                            Log.e(Constants.TAG, "Subscribe")
                        }

                        override fun onNext(t: List<Int>) {
                            Log.e(Constants.TAG, "Next")
                            Log.e(Constants.TAG, t.toString())
                        }

                        override fun onError(e: Throwable) {
                            Log.e(Constants.TAG, "Error")
                        }

                        override fun onComplete() {
                            Log.e(Constants.TAG, "Complete")
                        }

                    })*//*

    //Window
    Observable.range(1, 10)
    .window(3)
    .subscribe(object : Observer<Observable<Int>> {
        override fun onSubscribe(d: Disposable) {
            Log.e(Constants.TAG, "Subscribe")
        }

        override fun onNext(t: Observable<Int>) {
            Log.e(Constants.TAG, "Next")
            t.subscribe {
                Log.e(Constants.TAG, it.toString())
            }
        }

        override fun onError(e: Throwable) {
            Log.e(Constants.TAG, "Error")
        }

        override fun onComplete() {
            Log.e(Constants.TAG, "Complete")
        }

    })
    */
}