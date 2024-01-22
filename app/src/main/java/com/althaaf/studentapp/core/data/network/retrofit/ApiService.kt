package com.althaaf.studentapp.core.data.network.retrofit

import com.althaaf.studentapp.core.data.network.response.StudentsListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    suspend fun getStudentsList(
        @Header("Authorization") token: String,
        @Query("delay") delay: Int,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ) : StudentsListResponse
}