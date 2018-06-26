package bcr.coroutinesaac.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.view.View
import android.widget.Toast
import bcr.coroutinesaac.R
import bcr.coroutinesaac.model.Repository

class ItemRepoViewModel(var context: Context, var repository: Repository): BaseObservable() {

    var name: ObservableField<String> = ObservableField(repository.name)
    var description: ObservableField<String> = ObservableField(repository.description)
    var stars: ObservableField<String> = ObservableField(context.getString(R.string.text_stars, repository.stargazers_count))
    var watchers: ObservableField<String> = ObservableField(context.getString(R.string.text_watchers, repository.watchers))
    var forks: ObservableField<String> = ObservableField(context.getString(R.string.text_forks, repository.forks))

    fun onItemClick(view: View) {
        Toast.makeText(context, "Click", Toast.LENGTH_SHORT)
    }

    companion object {
        fun setRepository(itemRepoViewModel: ItemRepoViewModel, repository: Repository){
            itemRepoViewModel.repository = repository
            itemRepoViewModel.notifyChange()
        }
    }
}
