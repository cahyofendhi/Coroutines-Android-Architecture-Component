package bcr.coroutinesaac.network

import retrofit2.HttpException
import java.io.IOException

class NetworkCallback<T> {
    var success: ((T) -> Unit) ?= null
    var error: ((RequestError)-> Unit) ?= null
}


