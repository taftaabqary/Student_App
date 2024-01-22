package com.althaaf.studentapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.althaaf.studentapp.core.data.network.response.DataItem
import com.althaaf.studentapp.core.data.repository.MainRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val mainRepository: MainRepository): ViewModel() {

    fun getStudentsList(): LiveData<PagingData<DataItem>> {
        return mainRepository.getStudentsList().cachedIn(viewModelScope)
    }

    fun logout() {
        viewModelScope.launch {
            mainRepository.logout()
        }
    }

}