package ir.digireza.s1_datastore

import ir.digireza.s1_datastore.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityMainBinding

    //Datastore
    private val datastore: DataStore<Preferences> by preferencesDataStore("userInfo")
    private val userName = stringPreferencesKey("USERNAME")
    private val userAge = intPreferencesKey("AGE")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //Save btn
            saveBtn.setOnClickListener {
                CoroutineScope(IO).launch {
                    storeUserInfo(nameEdt.text.toString(), 29)
                }
            }
            //Get data
            lifecycle.coroutineScope.launchWhenCreated {
                getName().collect{
                    showDataTxt.text = it
                }
            }
        }
    }

    private suspend fun storeUserInfo(name: String, age: Int) {
        datastore.edit {
            it[userName] = name
            it[userAge] = age
        }
    }

    private fun getName() = datastore.data.map {
        it[userName] ?: ""
    }
}