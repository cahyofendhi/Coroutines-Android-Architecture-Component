package bcr.coroutinesaac.repository

import android.content.Context
import bcr.coroutinesaac.model.Repository
import bcr.coroutinesaac.network.ApiProvider
import bcr.coroutinesaac.network.Network
import bcr.coroutinesaac.network.NetworkCallback
import bcr.coroutinesaac.util.debug

class MainRepository(var context: Context) {

    companion object {
        var sInstance: MainRepository? = null
        fun getInstance(context: Context): MainRepository {
            if (sInstance == null){
                synchronized(MainRepository::class){
                    if (sInstance == null){
                        sInstance   = MainRepository(context)
                    }
                }
            }
            return sInstance!!
        }
    }

    fun loadRepositories(name: String, result: (List<Repository>) -> Unit,errorMessage: (String) -> Unit) {
        Network.request(ApiProvider.provideApi().getRepositories(name),
                NetworkCallback<List<Repository>>().apply {
                    success = {
                        debug("Data Size : "+it.size)
                        result(it)
                    }
                    error = {
                        val message = "Code : "+it.code+" \nMessage : "+it.message
                        debug("Error : "+message)
                        errorMessage(message!!)
                    }
                })
    }

}