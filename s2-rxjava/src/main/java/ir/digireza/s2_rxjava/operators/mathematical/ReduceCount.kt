package ir.digireza.s2_rxjava.operators.mathematical

import ir.digireza.s2_rxjava.utils.Constants
import android.util.Log

class ReduceCount {
/*
    //Reduce
*//*                Observable.range(1, 5)
                    .reduce { t1, t2 -> t1 + t2 }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it.toString()) }*//*

    //Count
    Observable.just("Reza", "Maryam", "Nooshin", "Marzie", "Ali", "Nader")
    .count()
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(Consumer { Log.e(Constants.TAG, it.toString()) })
    */
}