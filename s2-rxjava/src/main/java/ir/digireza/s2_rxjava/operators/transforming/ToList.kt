package ir.digireza.s2_rxjava.operators.transforming

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class ToList {
/*
    //ToList
*//*                Observable.just(78, 954, 12, 1, 6, 45, 83, 9, 90)
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Consumer<List<Int>> {
                        override fun accept(t: List<Int>) {
                            Log.e(Constants.TAG, t.toString())
                        }
                    })*//*

*//*                Observable.just(78, 954, 12, 1, 6, 45, 83, 9, 90)
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(Consumer<List<Int>> {
                        Log.e(Constants.TAG, it.toString())
                    })*//*

    Observable.just(78, 954, 12, 1, 6, 45, 83, 9, 90)
    //.toSortedList()
    .toSortedList(Collections.reverseOrder())
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(Consumer<List<Int>>{
        Log.e(Constants.TAG, it.toString())
    })*/

}