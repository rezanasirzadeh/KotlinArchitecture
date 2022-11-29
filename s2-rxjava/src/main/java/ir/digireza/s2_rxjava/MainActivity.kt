package ir.digireza.s2_rxjava

import ir.digireza.s2_rxjava.databinding.ActivityMainBinding
import ir.digireza.s2_rxjava.utils.Constants
import ir.digireza.s2_rxjava.utils.User
import ir.digireza.s2_rxjava.utils.Utils
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import hu.akarnokd.rxjava3.math.MathObservable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Get data
            getData.setOnClickListener {

            }
        }
    }
}