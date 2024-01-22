package com.althaaf.studentapp.core.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.althaaf.studentapp.core.data.repository.MainRepository
import com.althaaf.studentapp.core.di.Injection
import com.althaaf.studentapp.ui.auth.LoginViewModel
import com.althaaf.studentapp.ui.dashboard.DashboardViewModel

class MainViewModelFactory(private val mainRepository: MainRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(mainRepository) as T
        }

        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(mainRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: MainViewModelFactory? = null

        fun getInstance(context: Context) : MainViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: MainViewModelFactory(Injection.provideMainRepository(context))
            }.also { instance = it }
        }
    }
}