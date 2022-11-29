package ir.digireza.s2_flow.simple

import ir.digireza.s2_flow.R
import ir.digireza.s2_flow.databinding.ActivityLifecycleBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.debounce

class Operators3Activity : AppCompatActivity() {
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
                    mapFilterTakeFlowWithContext()
                }
            }
        }
    }

    private suspend fun debounceFlow() {
        flow {
            emit(5)
            delay(300)
            emit(8)
            delay(510)
            emit(55)
            delay(499)
            emit(100)
            delay(800)
            emit(63)
            delay(600)
        }.debounce(500)
            .collect { binding.showData.append("$it\n") }
    }

    private suspend fun mapFilterTakeFlow() {
        (1..20).asFlow()
            .map { it * it }
            .filter { (it % 2 == 0).not() }
            .take(5)
            .collect {
                delay(1000)
                binding.showData.append("$it\n")
            }
    }

    private suspend fun mapFilterTakeFlowWithContext() {
        (1..20).asFlow()
            .map { it * it }
            .flowOn(Dispatchers.IO)
            .filter { (it % 2 == 0).not() }
            .flowOn(Dispatchers.IO)
            .take(5)
            .flowOn(Dispatchers.Main)
            .collect {
                delay(1000)
                binding.showData.append("$it\n")
            }
    }
}