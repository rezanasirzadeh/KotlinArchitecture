package ir.digireza.s2_flow.simple

import ir.digireza.s2_flow.databinding.ActivityLifecycleBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class LifecycleActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityLifecycleBinding

    private val TAG = "FlowLifecycleLog"

    private val flowCatch = flow {
        emit("Reza")
        throw Throwable("Error message")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Click
            getData.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    flowWithAll()
                }
            }
        }
    }

    private suspend fun flowWithOnStart() {
        flowOf(1, 2, 3)
            .onStart {
                emit(0)
                binding.showData.append("Flow started\n")
            }
            .collect { binding.showData.append("$it\n") }
    }

    private suspend fun flowWithOnCompleted() {
        flowOf(1, 2, 3)
            .onCompletion {
                emit(0)
                binding.showData.append("Flow completed\n")
            }
            .collect { binding.showData.append("$it\n") }
    }

    private suspend fun flowWithOnEach() {
        flowOf(1, 2, 3)
            .onEach {
                delay(1000)
            }
            .collect { binding.showData.append("$it\n") }
    }

    private suspend fun countDownTimer() {
        (5 downTo 1).asFlow()
            .onEach {
                delay(1000)
            }
            .collect { binding.showData.text = "$it" }
    }

    private suspend fun flowWithOnEmpty() {
        flow<List<Int>> {}.onEmpty {
            emit(emptyList())
            binding.showData.append("Empty")
        }
            .collect {
                binding.showData.append("$it\n")
                Log.e(TAG, "flowWithOnEmpty: $it")
            }
    }

    private suspend fun flowWithCatch() {
        flowCatch.catch {
            emit("${it.message}")
        }.collect {
            binding.showData.append("$it\n")
        }
    }

    private suspend fun flowWithAll() {
        flowOf(1, 2, 3)
            .onEach {
                delay(1000)
            }.onStart {
                //view.showLoading()
                binding.showData.append("Start\n")
            }.onCompletion {
                //view.hideLoading()
                binding.showData.append("Complete\n")
            }.catch {
                //view.showErrorUI(it.message)
                binding.showData.append("Error\n")
            }.onEmpty {
                //view.showEmptyUI()
                binding.showData.append("Empty\n")
            }.collect {
                //Update adapter and recyclerview
                binding.showData.append("$it\n")
            }
    }
}