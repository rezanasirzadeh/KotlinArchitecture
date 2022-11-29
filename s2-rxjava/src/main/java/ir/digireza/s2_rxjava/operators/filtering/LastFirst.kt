package ir.digireza.s2_rxjava.operators.filtering

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class LastFirst {
/*
    //Last
    *//*Observable.range(1,10)
        .last(0)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })*//*

    *//*Observable.empty<Int>()
        .last(0)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })*//*

    //First
    *//*Observable.range(1,10)
        .first(0)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })*//*

    Observable.empty<Int>()
    .first(0)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })
    */
}