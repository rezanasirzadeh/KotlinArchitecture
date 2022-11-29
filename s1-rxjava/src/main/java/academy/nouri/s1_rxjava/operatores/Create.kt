package ir.digireza.s1_rxjava.operatores

class Create {
    /*
    * //User
        val user = Users(1, "Reza")
        //Observable
        Observable.create(ObservableOnSubscribe<Users> {itObservable->
            Utils.usersList().forEach {itUsers->
                if (!itObservable.isDisposed) {
                    itObservable.onNext(itUsers)
                }
            }

            if (!itObservable.isDisposed) {
                itObservable.onComplete()
            }
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
/*            .subscribe {
                Log.e(TAG, it.name)
            }*/
            .subscribe(object : Observer<Users> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Users) {
                    Log.e(TAG, t.name)
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "Error ${e.message}")
                }

                override fun onComplete() {
                    Log.e(TAG, "Completed")
                }
            })*/
}