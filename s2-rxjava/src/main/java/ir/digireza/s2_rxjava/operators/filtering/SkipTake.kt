package ir.digireza.s2_rxjava.operators.filtering

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class SkipTake {
/*
    //Skip
    *//*Observable.range(1, 10)
        //.skip(6)
        //.skipLast(6)
        .skipWhile {
            it < 5
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { Log.e(Constants.TAG, it.toString()) }*//*

    //Take
    Observable.range(1, 10)
    //.take(2)
    //.takeLast(2)
    .takeWhile { it < 5 }
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { Log.e(Constants.TAG, it.toString()) }
    */
}