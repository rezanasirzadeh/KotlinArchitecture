package ir.digireza.s2_rxjava.operators.conditional

import ir.digireza.s2_rxjava.utils.Constants
import ir.digireza.s2_rxjava.utils.Utils
import android.util.Log
import io.reactivex.rxjava3.core.Observable

class DefaultSequence {
/*
    //DefaultIfEmpty
    *//*Observable.create(ObservableOnSubscribe<Int> {
        val num = (1..50).random()

        if (num % 2 == 0) {
            it.onNext(num)
        }
        it.onComplete()

    }).defaultIfEmpty(-1)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { Log.e(Constants.TAG, it.toString()) }*//*

    //SequenceEqual
    val users1 = Observable.fromIterable(Utils.usersList())
    val users2 = Observable.fromIterable(Utils.usersList2())

    Observable.sequenceEqual(users1, users2)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })
    */
}