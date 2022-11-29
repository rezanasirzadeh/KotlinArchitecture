package ir.digireza.s2_rxjava.operators.create

class Interval {
    /*
    //Other
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Get data
            getData.setOnClickListener {
                //Interval
                val interval = Observable.interval(5, 2, TimeUnit.SECONDS)
                    .take(3)
                    .flatMap {
                        return@flatMap Observable.create<String> { emitter ->
                            Log.e(Constants.TAG, "Create")
                            emitter.onNext("Reza Nasirzadeh")
                            emitter.onComplete()
                        }
                    }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.e(Constants.TAG, it) }

                compositeDisposable.add(interval)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }*/
}