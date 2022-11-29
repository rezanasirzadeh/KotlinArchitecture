package ir.digireza.s2_flow.simple

import ir.digireza.s2_flow.databinding.ActivityLifecycleBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class Operators4Activity : AppCompatActivity() {
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
                    simpleMergeData()
                }
            }
        }
    }

    private suspend fun simpleZipData() {
        val numbers = (1..3).asFlow()
        val chars = flowOf("A", "B", "C")
        numbers.zip(chars) { a, b -> "$a => $b" }
            .collect {
                delay(800)
                binding.showData.append("$it\n")
            }
    }

    private suspend fun notMatchZipData() {
        val numbers = (1..4).asFlow()
        val chars = flowOf("A", "B", "C", "D")
        numbers.zip(chars) { a, b -> "$a => $b" }
            .collect {
                binding.showData.append("$it\n")
            }
    }

    private suspend fun simpleZipDataWithDelay() {
        val numbers = (1..3).asFlow().onEach { delay(1000) }
        val chars = flowOf("A", "B", "C").onEach { delay(2000) }
        numbers.zip(chars) { a, b -> "$a => $b" }
            .collect {
                binding.showData.append("$it\n")
            }
    }

    private suspend fun simpleCombineData() {
        val numbers = (1..3).asFlow().onEach { delay(1000) }
        val chars = flowOf("A", "B", "C").onEach { delay(2000) }

        numbers.combine(chars) { a, b -> "$a => $b" }
            .collect { binding.showData.append("$it\n") }
    }

    private suspend fun simpleMergeData() {
        //Without delay
        //val numbers = (1..3).asFlow()
        //val chars = flowOf("A", "B", "C")
        //Delay for numbers
        //val numbers = (1..3).asFlow().onEach { delay(1000) }
        //Delay for both
        val numbers = (1..3).asFlow().onEach { delay(300) }
        val chars = flowOf("A", "B", "C").onEach { delay(500) }

        merge(numbers, chars)
            .collect { binding.showData.append("$it\n") }
    }
}