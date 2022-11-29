package ir.digireza.s2_rxjava.operators.create

class Create {
    /*
    //Other
    private val user = User(1, "Reza")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Get data
            getData.setOnClickListener {
                //Create
                Observable.create(ObservableOnSubscribe<User> { itObservable ->
                    //Next
                    Utils.usersList().forEach {
                        if (!itObservable.isDisposed) {
                            itObservable.onNext(it)
                        }
                    }
                    //Complete
                    if (!itObservable.isDisposed) {
                        itObservable.onComplete()
                    }
                }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.e(Constants.TAG, it.name)
                    }
            }
        }
    }
     */
}