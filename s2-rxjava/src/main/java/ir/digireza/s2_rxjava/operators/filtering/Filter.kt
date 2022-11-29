package ir.digireza.s2_rxjava.operators.filtering

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class Filter {
/*
    //Int
    *//*Observable.range(1, 10)
        .filter {
            return@filter it % 2 == 0
        }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            Log.e(Constants.TAG, it.toString())
        }*//*

    //users
    Observable.fromIterable(Utils.usersList())
    .filter {
        return@filter it.name.startsWith("M")
    }
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe {
        Log.e(Constants.TAG, it.toString())
    }
    */
}