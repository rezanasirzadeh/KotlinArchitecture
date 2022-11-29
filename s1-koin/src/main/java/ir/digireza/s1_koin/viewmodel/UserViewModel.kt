package ir.digireza.s1_koin.viewmodel

import androidx.lifecycle.ViewModel

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    fun showUserInfo(): String {
        return repository.userName()
    }
}