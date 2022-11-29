package ir.digireza.s2_rxjava.simple

import ir.digireza.s2_rxjava.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ObserverWithDisposable {

/*    class MainActivity : AppCompatActivity() {
        //Binding
        private lateinit var binding: ActivityMainBinding

        //Other
        private lateinit var disposable: Disposable

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            //InitViews
            binding.apply {
                //Get data
                getData.setOnClickListener {
                    //Create observable
                    Observable.just("Reza", "Nooshin", "Mohsen")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<String> {
                            override fun onSubscribe(d: Disposable) {
                                disposable = d
                            }

                            override fun onNext(t: String) {
                                showData.append("$t\n")
                            }

                            override fun onError(e: Throwable) {
                                showData.append("${e.message}")
                            }

                            override fun onComplete() {
                                showData.append("onComplete called")
                            }
                        })
                }
            }

            if (disposable.isDisposed) {

            }
        }

        override fun onPause() {
            super.onPause()
            disposable.dispose()
        }
    }*/
}