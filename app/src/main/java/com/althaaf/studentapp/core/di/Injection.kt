package com.althaaf.studentapp.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.althaaf.studentapp.core.data.local.datastore.UserPreference
import com.althaaf.studentapp.core.data.network.retrofit.ApiConfig
import com.althaaf.studentapp.core.data.repository.MainRepository

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
object Injection {

    fun provideMainRepository(context: Context): MainRepository {
        val apiService = ApiConfig.getApiService()
        val dataStore = UserPreference.getInstance(context.dataStore)
        return  MainRepository.getInstance(apiService, dataStore)
    }

    fun provideDataStore(context: Context): UserPreference {
        return UserPreference.getInstance(context.dataStore)
    }
}