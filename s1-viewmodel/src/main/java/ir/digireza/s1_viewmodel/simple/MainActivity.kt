package ir.digireza.s1_viewmodel.simple

import ir.digireza.s1_viewmodel.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Other
    private var counter = 0
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Set text
            showTxt.text = mainViewModel.counter.toString()
            //Click
            addBtn.setOnClickListener {
                //Normally
/*                counter++
                showTxt.text = counter.toString()*/

                //ViewModel
                mainViewModel.addNumber()
                showTxt.text = mainViewModel.counter.toString()
            }
        }
    }
}