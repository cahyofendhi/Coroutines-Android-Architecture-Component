package bcr.coroutinesaac.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.annotation.NonNull
import android.text.TextWatcher
import android.view.View
import bcr.coroutinesaac.R
import bcr.coroutinesaac.adapter.TextWatcherAdapter
import bcr.coroutinesaac.model.Repository
import bcr.coroutinesaac.repository.MainRepository

class MainViewModel(var context: Context, application: Application) : AndroidViewModel(application) {

    var infoMessage: ObservableField<String> = ObservableField(application.getString(R.string.default_info_message))
    var isInfo: ObservableBoolean = ObservableBoolean(true)
    var isProgress: ObservableBoolean = ObservableBoolean(false)
    var isEmpty: ObservableBoolean = ObservableBoolean(true)
    var isSearch: ObservableBoolean = ObservableBoolean(false)

    lateinit var editTextUsernameValue: String
    var mDataObservable: MutableLiveData<List<Repository>>? = MutableLiveData()

    val repository : MainRepository by lazy {
        MainRepository.getInstance(context)
    }

    fun onClickSearch(view: View) {
        loadRepositories()
    }

    fun loadRepositories(){
        isProgress.set(true)
        isEmpty.set(true)
        isInfo.set(false)
        repository.loadRepositories(editTextUsernameValue, {
            isProgress.set(false)
            isEmpty.set(it.isEmpty())
            mDataObservable?.value = it
        },{
            infoMessage.set(it)

            isProgress.set(false)
            isInfo.set(true)
        })
    }

    fun getUsernameEditTextWatcher(): TextWatcher {
        return object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                editTextUsernameValue = s.toString()
                isSearch.set(s?.length!! > 0)
            }
        }
    }

    companion object {

        public class Factory: ViewModelProvider.NewInstanceFactory {

            var mApplication: Application? = null
            var mContext: Context? = null

            constructor(context: Context, @NonNull application: Application){
                this.mContext = context
                this.mApplication = application
            }

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(mContext!!, mApplication!!) as T
            }

        }

    }

}

