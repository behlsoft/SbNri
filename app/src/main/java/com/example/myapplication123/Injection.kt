/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.paging

import android.content.Context
import androidx.lifecycle.ViewModelProvider

import com.example.myapplication123.datastore.DataRepository
import com.example.myapplication123.datastore.LocalData
import com.example.myapplication123.datastore.RepoDb
import com.example.myapplication123.retrofit.GithubService
import com.example.myapplication123.ui.ViewModelFactory
import java.util.concurrent.Executors

object Injection {

    private fun provideCache(context: Context): LocalData {
        val database = RepoDb.getInstance(context)
        return LocalData(database.reposDao(), Executors.newSingleThreadExecutor())
    }

    private fun provideGithubRepository(context: Context): DataRepository {
        return DataRepository(GithubService.create(), provideCache(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
}
