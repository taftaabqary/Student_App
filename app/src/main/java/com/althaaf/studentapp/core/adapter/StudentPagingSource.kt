package com.althaaf.studentapp.core.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.althaaf.studentapp.core.data.local.datastore.UserPreference
import com.althaaf.studentapp.core.data.network.response.DataItem
import com.althaaf.studentapp.core.data.network.retrofit.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class StudentPagingSource(
    private val apiService: ApiService,
    private val dataStore: UserPreference
) : PagingSource<Int, DataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val sessionUser = withContext(Dispatchers.IO) {
                dataStore.getUser().first()
            }

            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getStudentsList(
                token = "Bearer ${sessionUser.token}",
                page = page,
                per_page = 4
            )

            val responseStudentList = responseData.data

            LoadResult.Page(
                data = responseStudentList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseStudentList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }



    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}