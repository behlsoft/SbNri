package com.example.myapplication123.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication123.datastore.DataRepository

class ViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RepoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}