package bcr.coroutinesaac.network

class RepositoryCallback<T>{
    var onResult: T? = null
    var error: String ?= null
}