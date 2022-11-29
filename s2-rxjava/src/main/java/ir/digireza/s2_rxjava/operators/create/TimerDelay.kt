package ir.digireza.s2_rxjava.operators.create

class TimerDelay {
    /*
    *                 //Timer
                Observable.timer(2, TimeUnit.SECONDS)
                    .flatMap {
                        return@flatMap Observable.create<String> { emitter ->
                            Log.e(Constants.TAG, "Create")
                            emitter.onNext("Reza Nasirzadeh")
                            emitter.onComplete()
                        }
                    }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it) }

                //Delay
                Observable.create<String> { emitter ->
                    Log.e(Constants.TAG, "Create")
                    emitter.onNext("Reza Nasirzadeh")
                    emitter.onComplete()
                }.delay(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it) }
                    * */
}