package bcr.coroutinesaac.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import bcr.coroutinesaac.R
import bcr.coroutinesaac.databinding.ItemRepoBinding
import bcr.coroutinesaac.model.Repository
import bcr.coroutinesaac.viewmodel.ItemRepoViewModel
import java.util.*

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var repositories: List<Repository> = Collections.emptyList()

    fun setRepositories(repositories: List<Repository>) {
        this.repositories   = repositories
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.bindRepository(repositories.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = DataBindingUtil.inflate<ItemRepoBinding>(LayoutInflater.from(parent?.context),
                R.layout.item_repo, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    class ViewHolder(var binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.cardView) {

        fun bindRepository(repository: Repository) {
            if (binding.vm == null){
                binding.vm = ItemRepoViewModel(itemView.context, repository)
            } else {
                ItemRepoViewModel.setRepository(binding.vm!!, repository)
            }
        }
    }
}