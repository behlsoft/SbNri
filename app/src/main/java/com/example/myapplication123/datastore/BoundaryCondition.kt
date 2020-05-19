package com.example.myapplication123.datastore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.myapplication123.model.SbModel
import com.example.myapplication123.retrofit.GithubService
import com.example.myapplication123.retrofit.getRequiredRepos


class BoundaryCondition(
    private val service: GithubService,
    private val cache: LocalData
) : PagedList.BoundaryCallback<SbModel>() {

    private var lastPageRequested = 1

    private val _networkApiErrors = MutableLiveData<String>()

    val networkErrors: MutableLiveData<String>
        get() = _networkApiErrors

    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        getAndSaveData()
        Log.d("In BC", "BC")
    }

    override fun onItemAtEndLoaded(itemAtEnd: SbModel) {
        getAndSaveData()
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }

    private fun getAndSaveData() {

        if (isRequestInProgress) return

        isRequestInProgress = true
        getRequiredRepos(service, lastPageRequested, NETWORK_PAGE_SIZE, { repos ->
            cache.insert(repos) {
                lastPageRequested++
                isRequestInProgress = false
            }
        }, { error ->
            networkErrors.postValue(error)
            isRequestInProgress = false
        })
    }
}