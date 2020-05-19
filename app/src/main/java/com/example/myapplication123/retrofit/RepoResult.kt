package com.example.myapplication123.retrofit

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.myapplication123.model.SbModel

data class RepoResult(
    val data: LiveData<PagedList<SbModel>>,
    val networkErrors: LiveData<String>
)