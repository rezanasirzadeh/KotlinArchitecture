package ir.digireza.s1_livedata.simple

import ir.digireza.s1_livedata.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Click
            setDataBtn.setOnClickListener {
                viewModel.siteName.value = "digireza.ir"
            }
            //Get data
            val siteObserver = Observer<String> {
                infoTxt.text = it
            }
            viewModel.siteName.observe(this@MainActivity, siteObserver)
        }
    }
}