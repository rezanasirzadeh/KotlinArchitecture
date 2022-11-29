package ir.digireza.s2_rxjava.operators.transforming

import ir.digireza.s2_rxjava.utils.Constants
import ir.digireza.s2_rxjava.utils.User
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function
import java.util.concurrent.TimeUnit

class FlatConcatMap {
/*
    //Flatmap
    *//*Observable.fromIterable(Utils.usersList())
        *//**//*.flatMap(object : Function<User, Observable<*>> {
                        override fun apply(t: User): Observable<*> {
                            return Observable.just(t)
                                .delay((1..3).random().toLong(), TimeUnit.SECONDS)
                        }
                    })*//**//*
                    .flatMap { t ->
                        Observable.just(t)
                            .delay((1..3).random().toLong(), TimeUnit.SECONDS)
                    }
                    .subscribeOn(Schedulers.io())

                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it.toString()) }

                Thread.sleep(3000)*//*

    //ConcatMap
    Observable.fromIterable(Utils.usersList())
    .concatMap(object : Function<User, Observable<*>> {
        override fun apply(t: User): Observable<*> {
            return Observable.just(t)
                .delay((1..3).random().toLong(), TimeUnit.SECONDS)
        }
    })
    *//*.concatMap { t ->
        Observable.just(t)
            .delay((1..3).random().toLong(), TimeUnit.SECONDS)
    }*//*
    .subscribeOn(Schedulers.io())

    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { Log.e(Constants.TAG, it.toString()) }

    Thread.sleep(3000)
    */
}