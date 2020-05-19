package com.example.myapplication123.ui

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication123.model.SbModel

class AdapterRepo : PagedListAdapter<SbModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderRepo.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as ViewHolderRepo).bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<SbModel>() {
            override fun areItemsTheSame(oldItem: SbModel, newItem: SbModel): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: SbModel, newItem: SbModel): Boolean =
                oldItem == newItem
        }
    }
}