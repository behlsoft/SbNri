package com.example.myapplication123


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.codelabs.paging.Injection
import com.example.myapplication123.model.SbModel
import com.example.myapplication123.ui.AdapterRepo
import com.example.myapplication123.ui.RepoViewModel
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

//object Injection {
//    val service: GithubService by lazy { GithubService() }
//val repoDao:RepoDao by lazy { RepoDb.getInstance().reposDao() }
//    val localData:LocalData by lazy { LocalData(repoDao, Executor {  }) }
//    val repository: Repository by lazy { Repository(service) }
//    val viewModelFactory: ViewModelFactory by lazy {
//        ViewModelFactory(repository)
//    }
//}
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: RepoViewModel
    private val adapter = AdapterRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this);

        // get the view model
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this))
            .get(RepoViewModel::class.java)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        list.addItemDecoration(decoration)
// initialize adapter
        initAdapter()
        viewModel.getRepo()

        updateRepoList(savedInstanceState?.getInt(SCROLL_POSITION) ?: 0)
    }




    private fun updateRepoList(defaultPosition: Int) {
        viewModel.getRepo()
        adapter.submitList(null)
        list.scrollToPosition(defaultPosition)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCROLL_POSITION, (list.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())

    }

    private fun initAdapter() {
        list.adapter = adapter
        viewModel.repos.observe(this, Observer<PagedList<SbModel>> {
            showEmptyList(it?.size == 0)

            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "Error $it", Toast.LENGTH_LONG).show()
        })
    }
    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            emptyList.text=getString(R.string.no_data_found)
            list.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
        }
    }

    companion object {
        private const val SCROLL_POSITION: String = "top_position"
    }
}
