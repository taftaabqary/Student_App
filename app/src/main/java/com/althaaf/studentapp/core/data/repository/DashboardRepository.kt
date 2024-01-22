package com.althaaf.studentapp.core.data.repository

import com.althaaf.studentapp.core.data.local.UserPreference
import com.althaaf.studentapp.core.data.network.retrofit.ApiService

class DashboardRepository (
    private val apiService: ApiService,
    private val dataStore: UserPreference
) {

}