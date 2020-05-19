package com.example.myapplication123.datastore

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.myapplication123.model.SbModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class LocalData(
    private val repoDao: RepoDao,
    private val ioExecutor: Executor
) {
    fun insert(repos: List<SbModel>, insertFinished: () -> Unit) {
        ioExecutor.execute {
            repoDao.insert(repos)
            insertFinished()

        }
    }
    fun reposByName(): DataSource.Factory<Int, SbModel> {
        return repoDao.getRepos()
    }
}