package ir.digireza.s1_project.viewmodel

import ir.digireza.s1_project.models.register.BodyRegister
import ir.digireza.s1_project.models.register.ResponseRegister
import ir.digireza.s1_project.repository.RegisterRepository
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repository: RegisterRepository) : ViewModel() {

    val registerUser = MutableLiveData<ResponseRegister>()
    val loading = MutableLiveData<Boolean>()

    fun sendRegisterUser(body: BodyRegister) = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.registerUser(body)
        if (response.isSuccessful) {
            registerUser.postValue(response.body())
        }
        loading.postValue(false)
    }

}