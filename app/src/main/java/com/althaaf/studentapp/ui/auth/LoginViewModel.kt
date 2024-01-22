package com.althaaf.studentapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.althaaf.studentapp.core.data.repository.MainRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun saveUserToken() {
        viewModelScope.launch {
            mainRepository.saveUserToken()
        }
    }
}