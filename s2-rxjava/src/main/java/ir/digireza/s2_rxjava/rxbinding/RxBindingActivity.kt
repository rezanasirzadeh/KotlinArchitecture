package ir.digireza.s2_rxjava.rxbinding

import ir.digireza.s2_rxjava.R
import ir.digireza.s2_rxjava.databinding.ActivityRxBindingBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.checkedChanges
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RxBindingActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityRxBindingBinding

    //Other
    private val disposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Close
            disposable.add(closeImg.clicks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { finish() })

            //Submit
            disposable.add(submit.clicks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { showInfoTxt.text = "Button Clicked" })

            //Search
            disposable.add(searchEdt.textChanges()
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { showInfoTxt.text = it.toString() })

            //Checkbox
            disposable.add(checkBox.checkedChanges()
                .skipInitialValue()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { showInfoTxt.text = it.toString() })

            //Radio group
            disposable.add(genderGroup.checkedChanges()
                .skipInitialValue()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it == R.id.radioMale) {
                        showInfoTxt.text = "Male"
                    } else {
                        showInfoTxt.text = "Female"
                    }
                })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}