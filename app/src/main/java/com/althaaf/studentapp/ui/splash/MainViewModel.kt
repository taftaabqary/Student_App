package com.althaaf.studentapp.ui.splash

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.althaaf.studentapp.core.data.local.datastore.UserPreference
import com.althaaf.studentapp.core.data.local.model.UserModel
import com.althaaf.studentapp.core.di.Injection

class MainViewModel(private val dataStore: UserPreference): ViewModel() {
    fun getTokenUser(): LiveData<UserModel> {
        return dataStore.getUser().asLiveData()
    }

    class ViewModelFactory(private val dataStore: UserPreference): ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(dataStore) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class ${modelClass.name}")
        }

        companion object {
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context) : ViewModelFactory {
                return instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideDataStore(context))
                }.also { instance = it }
            }
        }
    }
}