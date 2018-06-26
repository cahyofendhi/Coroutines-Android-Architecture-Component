package bcr.coroutinesaac.network

import bcr.coroutinesaac.util.debug
import retrofit2.HttpException
import java.io.IOException

class RequestError{
    var code: Int? = 0
    var message: String? = "An error occured"


    constructor(error: Throwable) {
        when (error){
            is HttpException -> {
                val errorJsonString = error.response().errorBody()?.string()
                this.code   = error.code()
                this.message = errorJsonString!!
            }
            is IOException -> {
                error.message?.let {
                    this.message = it

                }
                debug("2 ")
            }else -> {
                debug("3 ")
                this.message    = error.message ?: this.message
            }
        }
    }
}