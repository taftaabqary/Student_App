package com.althaaf.studentapp.core.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.althaaf.studentapp.core.adapter.StudentPagingSource
import com.althaaf.studentapp.core.data.local.datastore.UserPreference
import com.althaaf.studentapp.core.data.network.response.DataItem
import com.althaaf.studentapp.core.data.network.retrofit.ApiService

class DashboardRepository (
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
    companion object {
        @Volatile
        private var instance: DashboardRepository? = null

        fun getInstance(apiService: ApiService, dataStore: UserPreference): DashboardRepository =
            instance ?: synchronized(this) {
                instance ?: DashboardRepository(apiService, dataStore)
            }.also { instance = it }
    }
}