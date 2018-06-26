package bcr.coroutinesaac.network

import bcr.coroutinesaac.model.Repository
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("users/{username}/repos")
    fun getRepositories(@Path("username") username: String): Deferred<List<Repository>>

}