package ir.digireza.s2_flow.simple

import ir.digireza.s2_flow.databinding.ActivityLifecycleBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class Operators5Activity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityLifecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Click
            getData.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    flatMapLatest()
                }
            }
        }
    }

    private fun baseNumberFlow(str: String) = flowOf(1, 2, 3)
        .onEach { delay(1000) }
        .map { "$it _ $str" }

    private suspend fun flatMapConcat() {
        flowOf("A", "B", "C")
            .flatMapConcat { baseNumberFlow(it) }
            .collect { binding.showData.append("$it\n") }
    }

    private suspend fun flatMapMerge(){
        flowOf("A", "B", "C")
            //.flatMapMerge { baseNumberFlow(it) }
            .flatMapMerge(2){baseNumberFlow(it)}
            .collect { binding.showData.append("$it\n") }
    }

    private suspend fun flatMapLatest(){
        flowOf("A", "B", "C")
            .flatMapLatest { baseNumberFlow(it) }
            .collect { binding.showData.append("$it\n") }
    }
}