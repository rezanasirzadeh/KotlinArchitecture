package ir.digireza.s2_rxjava.operators.create

class Just {
    /*
    *    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //List
            val numbers1: Array<Int> = arrayOf(5, 984, 658, 98, 49, 49684, 98)
            val numbers2: Array<Int> = arrayOf(8, 986, 63, 165, 9, 846, 5)
            //Get data
            getData.setOnClickListener {
                //Just
                //Observable.just(4, 8, 9, 452, 89)
                //Observable.just("Reza", "Maryam", "Ali", "Zahra", "Nooshin")
                Observable.just(numbers1, numbers2)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<Array<Int>> {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onNext(t: Array<Int>) {
                            t.forEach { number ->
                                Log.e(Constants.TAG, number.toString())
                            }
                        }

                        override fun onError(e: Throwable) {

                        }

                        override fun onComplete() {

                        }
                    })
            }
        }
    }*/
}