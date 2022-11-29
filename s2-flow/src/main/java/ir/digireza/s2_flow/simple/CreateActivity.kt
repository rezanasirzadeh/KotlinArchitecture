package ir.digireza.s2_flow.simple

import ir.digireza.s2_flow.databinding.ActivityCreateBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@FlowPreview
class CreateActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityCreateBinding

    //Other
    private lateinit var staticNamesFlow: Flow<String>
    private lateinit var dynamicNumbersFlow: Flow<Int>
    private lateinit var collectionFlow: Flow<Int>
    private val myList = listOf(5, 8, 64, 31, 99)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Call methods
        setupStaticNames()
        setupDynamicNumbers()
        setupCollectionFlow()
        //InitViews
        binding.apply {
            //Click
            getData.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    convertFunToFlow()
                }
            }
        }
    }

    private suspend fun simpleIntFlow() {
        flowOf(1, 2, 4, 8, 9)
            .collect {
                binding.showData.append("$it\n")
            }
    }

    private fun setupStaticNames() {
        staticNamesFlow = flowOf("Reza", "Sahar", "Ali")
    }

    private fun setupDynamicNumbers() {
        dynamicNumbersFlow = flow {
            (1..5).forEach {
                emit(it)
            }
        }
    }

    private fun setupCollectionFlow() {
        collectionFlow = myList.asFlow()
    }

    private fun convertToFlow() {
        lifecycleScope.launchWhenCreated {
            (1..7).asFlow().collect {
                binding.showData.append("$it\n")
            }
        }
    }

    private suspend fun getUserInfo(): String {
        delay(1000)
        return "Reza Nasirzadeh"
    }

    private suspend fun convertFunToFlow(){
        ::getUserInfo.asFlow().collect{
            binding.showData.append("$it\n")
        }
    }
}