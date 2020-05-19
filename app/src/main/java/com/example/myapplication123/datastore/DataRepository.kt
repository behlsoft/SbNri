package com.example.myapplication123.datastore

import android.util.Log
import androidx.paging.LivePagedListBuilder
import com.example.myapplication123.retrofit.GithubService
import com.example.myapplication123.retrofit.RepoResult

class DataRepository(
    private val service: GithubService,
    private val cache: LocalData
) {

    fun getData(): RepoResult {
        Log.d("In DR", "DR")
        val dataSourceFactory = cache.reposByName()

        val boundaryConditionCallback = BoundaryCondition(service, cache)

        val networkApiErrors = boundaryConditionCallback.networkErrors
        val data = LivePagedListBuilder(dataSourceFactory, DB_PAGE_SIZE).setBoundaryCallback(
            boundaryConditionCallback
        ).build()

        return RepoResult(data, networkApiErrors)
    }

    companion object {
        private const val DB_PAGE_SIZE = 10
    }
}