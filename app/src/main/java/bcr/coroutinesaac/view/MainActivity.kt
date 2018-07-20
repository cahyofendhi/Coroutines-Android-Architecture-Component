package bcr.coroutinesaac.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import bcr.coroutinesaac.R
import bcr.coroutinesaac.databinding.ActivityMainBinding
import bcr.coroutinesaac.model.Repository
import bcr.coroutinesaac.util.Util
import bcr.coroutinesaac.view.adapter.RepositoryAdapter
import bcr.coroutinesaac.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecycleView()
        setupViewModel()
    }

    private fun setupViewModel() {
        val factory = MainViewModel.Companion.Factory(application)
        val viewModel = ViewModelProviders.of(this, factory)[MainViewModel::class.java]
        binding.vm  = viewModel
        viewModel.mDataObservable?.observe(this, Observer<List<Repository>> { t ->
            if (t != null) {
                onRepositoriesChanged(t)
            }
        })
    }

    private fun setupRecycleView() {
        adapter = RepositoryAdapter()
        binding.reposRecyclerView.adapter = adapter
        binding.reposRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun onRepositoriesChanged(repositories: List<Repository>) {
        adapter.setRepositories(repositories)
        adapter.notifyDataSetChanged()
        Util.hideSoftKeyboard(this, binding.editTextUsername)
    }

}
