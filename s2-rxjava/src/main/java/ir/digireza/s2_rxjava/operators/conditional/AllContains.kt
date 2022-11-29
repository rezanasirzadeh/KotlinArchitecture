package ir.digireza.s2_rxjava.operators.conditional

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class AllContains {
/*
    //All - Int
    *//*Observable.range(1,10)
        .all {
            it > 0
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })*//*

    //All - Users
    *//*Observable.fromIterable(Utils.usersList())
        .all {
            it.name.endsWith("d")
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })*//*

    //Contains - Int
    *//*Observable.range(1, 10)
        .contains(7)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })*//*

    //Contains - Users
    Observable.fromIterable(Utils.usersList())
    .contains(User(1, "Reza2"))
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })
    */
}