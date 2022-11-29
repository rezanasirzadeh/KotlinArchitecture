package ir.digireza.s2_rxjava.operators.filtering

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class ElementAt {
/*
    //ElementAt
    *//*Observable.range(1,10)
        //.elementAt(3)
        .elementAt(20,-1)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })*//*

    //ignoreElements
    Observable.range(1, 10)
    .ignoreElements()
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { Log.e(Constants.TAG, "OnCompleted") }
    */
}