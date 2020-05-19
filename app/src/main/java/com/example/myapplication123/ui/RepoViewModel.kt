package com.example.myapplication123.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.myapplication123.datastore.LocalData
import com.example.myapplication123.datastore.RepoDb
import com.example.myapplication123.datastore.DataRepository
import com.example.myapplication123.model.SbModel
import com.example.myapplication123.retrofit.GithubService
import com.example.myapplication123.retrofit.RepoResult
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class RepoViewModel(repository: DataRepository) : ViewModel() {

    private val queryLiveData = MutableLiveData<String>()

    private val repoResult: LiveData<RepoResult> = Transformations.map(queryLiveData) {
        repository.getData()

    }.also { Log.d("In VM","VM"+repository.getData().data.value+repository.getData().networkErrors.value) }

    val repos: LiveData<PagedList<SbModel>> = Transformations.switchMap(repoResult) { it -> it.data }
    val networkErrors: LiveData<String> = Transformations.switchMap(repoResult) { it ->
        it.networkErrors
    }

    fun getRepo() {
        queryLiveData.postValue("")
    }

}