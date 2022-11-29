package ir.digireza.s2_rxjava.operators.transforming

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class Map {

    //Int
/*  Observable.just(5, 2, 6, 7, 10, 15)
        .map {
             it * 2
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { Log.e(Constants.TAG, it.toString()) }*/

    //User
/*    Observable.fromIterable(Utils.usersList())
    .map {
        it.name.uppercase()
    }
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { Log.e(Constants.TAG, it.toString()) }*/

}