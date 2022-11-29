package ir.digireza.s1_coroutine

import ir.digireza.s1_coroutine.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    private val TAG = "CoroutineTags"

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*        CoroutineScope(Dispatchers.Main).launch {

            val result1 = async { doWork1() }
            val result2 = async { doWork2() }

            var finalResult = ""

            val time = measureTimeMillis {
                finalResult = "${result1.await()} ${result2.await()}"
            }

            Log.e(TAG, "$finalResult => $time")
        }*/

/*        CoroutineScope(Dispatchers.Main).launch {
            doWork1()
            doWork1()
            doWork1()
        }

        CoroutineScope(Dispatchers.Main).launch {
            runBlocking {
                doWork2()
                delay(3000)
                doWork2()
                doWork2()
            }
        }*/

/*        CoroutineScope(newSingleThreadContext("Reza")).launch {
            Log.e(TAG,Thread.currentThread().name)
        }*/

/*        CoroutineScope(Dispatchers.IO).launch {
            doWork1()
            withContext(Dispatchers.Main){
                binding.infoTxt.text = "Done"
            }
        }*/

/*        CoroutineScope(Dispatchers.IO).launch {
            repeat(3) {
                doWork1()
            }
        }*/

/*        CoroutineScope(Dispatchers.IO).launch {
            withTimeoutOrNull(3000) {
                for (i in 1000..1100) {
                    Log.e(TAG, i.toString())
                    delay(1000)
                }
            }
        }*/

/*        job = CoroutineScope(Main).launch {
            delay(1000)
            Log.e(TAG, "doWork1")
        }

        job.cancel()

        CoroutineScope(Main).launch {
            delay(2000)
            Log.e(TAG, "Active : " + job.isActive.toString())
            Log.e(TAG, "Completed : " + job.isCompleted.toString())
            Log.e(TAG, "Cancelled : " + job.isCancelled.toString())
        }*/

/*        CoroutineScope(Main).launch {
            val job = CoroutineScope(Main).launch {
                repeat(3){
                    delay(1000)
                    Log.e(TAG,"Coroutine is working...")
                }
            }
            delay(4000)
            job.cancelAndJoin()
            Log.e(TAG,"Done")
        }*/

        lifecycleScope.launch() {
            while (true) {
                delay(1000)
                Log.e(TAG, "Coroutine is working...")
            }
        }

        CoroutineScope(Main).launch {
            delay(3000)
            Intent(this@MainActivity, SecondActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

    }

    private suspend fun doWork1() {
        Log.e(TAG, "doWork1")
        delay(1000)
    }

    private suspend fun doWork2() {
        Log.e(TAG, "doWork2")
        delay(1000)
    }
}