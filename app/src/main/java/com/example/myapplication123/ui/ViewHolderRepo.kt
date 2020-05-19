package com.example.myapplication123.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication123.R
import com.example.myapplication123.model.SbModel

class ViewHolderRepo(view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.repo_name)
    private val description: TextView = view.findViewById(R.id.repo_description)
    private val permission: TextView = view.findViewById(R.id.repo_permission)
    private val licence: TextView = view.findViewById(R.id.repo_licence)
    private val openIssueCount: TextView = view.findViewById(R.id.repo_open_issue_count)
    private val permissionAdmin: CheckBox = view.findViewById(R.id.permission_admin)
    private val permissionPush: CheckBox = view.findViewById(R.id.permission_push)
    private val permissionPull: CheckBox = view.findViewById(R.id.permission_pull)

    private var repo: SbModel? = null

    init {
        view.setOnClickListener {
// item click
        }
    }

    fun bind(repo: SbModel?) {
        if (repo == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)

            permission.text = resources.getString(R.string.unknown)
            openIssueCount.text = resources.getString(R.string.unknown)
        } else {
            showRepoData(repo)
        }
    }

    private fun showRepoData(repo: SbModel) {
        this.repo = repo
        name.text = repo.name

        // if the description is missing, hide the TextView
        var descriptionVisibility = View.GONE
        if (repo.description != null) {
            description.text = repo.description
            descriptionVisibility = View.VISIBLE
        }
        description.visibility = descriptionVisibility

        licence.text = repo.license?.name
        permission.text = "Permission:"

if(repo.permissions?.admin!!)
    permissionAdmin.isChecked=true

        if(repo.permissions.push)
            permissionPush.isChecked=true

        if(repo.permissions.pull)
            permissionPull.isChecked=true

            openIssueCount.text = "Open Issue count: ${repo.open_issues_count}"

    }

    companion object {
        fun create(parent: ViewGroup): ViewHolderRepo {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.sb_item, parent, false)
            return ViewHolderRepo(view)
        }
    }
}