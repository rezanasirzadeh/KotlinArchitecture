package ir.digireza.s2_flow.simple

import ir.digireza.s2_flow.databinding.ActivityLifecycleBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.*

class Operators2Activity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityLifecycleBinding

    private val TAG = "OperatorsLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Click
            getData.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    foldFlow()
                }
            }
        }
    }

    private suspend fun repeatFlow() {
        flow {
            repeat(3) {
                (1..3).forEach {
                    emit(it)
                }
            }
        }.collect { binding.showData.append("$it\n") }
    }

    private suspend fun countFlow() {
        val count = (1..10).asFlow()
            .count {
                it % 2 == 0
            }
        binding.showData.append("$count\n")
    }

    private suspend fun reduceFlow() {
        val reduce = (1..5).asFlow()
            .reduce { accumulator, value ->
                Log.e(TAG, "reduceFlow: $accumulator - $value")
                accumulator + value
            }
        binding.showData.append("$reduce\n")
    }

    private suspend fun reduceStringFlow() {
        val reduce = flowOf("Reza", "Nasirzadeh", "Morvarid", "Mozafari")
            .reduce { accumulator, value ->
                Log.e(TAG, "reduceFlow: $accumulator - $value")
                accumulator + value
            }
        binding.showData.append("$reduce\n")
    }

    private suspend fun foldFlow() {
        val fold = (1..5).asFlow()
            .fold("Numbers ") { accumulator, value ->
                Log.e(TAG, "reduceFlow: $accumulator - $value")
                accumulator + value
            }
        binding.showData.append("$fold\n")
    }
}