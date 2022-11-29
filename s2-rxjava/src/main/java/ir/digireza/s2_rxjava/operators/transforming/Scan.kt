package ir.digireza.s2_rxjava.operators.transforming

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class Scan {
/*
    *//*                Observable.range(1, 5)
                    .scan(object : BiFunction<Int, Int, Int> {
                        override fun apply(t1: Int, t2: Int): Int {
                            Log.e(Constants.TAG, "$t1 --- $t2")
                            return t1 + t2
                        }
                    })
                    .delay(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it.toString()) }*//*

*//*                Observable.range(1, 5)
                    .scan { t1, t2 ->
                        Log.e(Constants.TAG, "$t1 --- $t2")
                        t1 + t2
                    }
                    .delay(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it.toString()) }*//*

*//*                Observable.range(1, 5)
                    .scan(10, object : BiFunction<Int,Int,Int>{
                        override fun apply(t1: Int, t2: Int): Int {
                            Log.e(Constants.TAG, "$t1 --- $t2")
                            return t1 + t2
                        }
                    })
                    .delay(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it.toString()) }*//*

    Observable.range(1, 5)
    .scan(10) { t1, t2 ->
        Log.e(Constants.TAG, "$t1 --- $t2")
        t1 + t2
    }
    .delay(3, TimeUnit.SECONDS)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { Log.e(Constants.TAG, it.toString()) }

    */
}