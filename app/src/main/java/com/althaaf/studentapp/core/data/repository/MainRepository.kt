package com.althaaf.studentapp.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.althaaf.studentapp.core.adapter.StudentPagingSource
import com.althaaf.studentapp.core.data.local.datastore.UserPreference
import com.althaaf.studentapp.core.data.local.model.UserModel
import com.althaaf.studentapp.core.data.network.response.DataItem
import com.althaaf.studentapp.core.data.network.retrofit.ApiService

class MainRepository (
    private val apiService: ApiService,
    private val dataStore: UserPreference
) {

    fun getStudentsList(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 4
            ),
            pagingSourceFactory = {
                StudentPagingSource(apiService = apiService, dataStore = dataStore)
            }
        ).liveData
    }

    suspend fun saveUserToken() {
        dataStore.saveUser(user = UserModel(token = "QpwL5tke4Pnpja7X4"))
    }

    suspend fun logout() {
        dataStore.clearUser()
    }

    companion object {
        @Volatile
        private var instance: MainRepository? = null

        fun getInstance(apiService: ApiService, dataStore: UserPreference): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(apiService, dataStore)
            }.also { instance = it }
    }
}