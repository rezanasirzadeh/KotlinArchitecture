package ir.digireza.s2_flow.simple

import ir.digireza.s2_flow.R
import ir.digireza.s2_flow.databinding.ActivityLifecycleBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class Operators1Activity : AppCompatActivity() {
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
                    takeWhileFlow()
                }
            }
        }
    }

    private suspend fun takeFlow() {
        (1..10).asFlow()
            .take(4)
            .collect { binding.showData.append("$it\n") }
    }

    private suspend fun mapFlow() {
        (1..10).asFlow()
            .map {
                it * it
            }.collect { binding.showData.append("$it\n") }
    }

    private suspend fun filterFlow() {
        (1..10).asFlow()
            .filter {
                it % 2 == 0
            }.collect { binding.showData.append("$it\n") }
    }

    private suspend fun takeWhileFlow() {
        (1..10).asFlow()
            .takeWhile {
                it < 5
                //it > 5
            }.collect { binding.showData.append("$it\n") }
    }
}