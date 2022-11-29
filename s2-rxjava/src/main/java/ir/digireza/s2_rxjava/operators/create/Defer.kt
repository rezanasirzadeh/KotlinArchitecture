package ir.digireza.s2_rxjava.operators.create

class Defer {

    /*
    //Other
    private val animal = Animal()
    val nameWithJust = animal.getNameWithJust()
    val nameWithDefer = animal.getNameWithDefer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Set name
        animal.name = "Jessi"
        animal.name = "Jessi"
        animal.name = "Jessi"
        animal.name = "Jessi"
        animal.name = "Jessi"
        animal.name = "Jacky"
        //InitViews
        binding.apply {
            //Get data
            getData.setOnClickListener {
                nameWithJust.subscribe { name -> Log.e(Constants.TAG, name) }

                nameWithDefer.subscribe { name -> Log.e(Constants.TAG, name) }
            }
        }
    }

    class Animal {
        var name = "No Name"

        fun getNameWithJust(): Observable<String> {
            return Observable.just(name)
        }

        fun getNameWithDefer(): Observable<String> {
            return Observable.defer {
                return@defer Observable.just(name)
            }
        }
    }
    */

}