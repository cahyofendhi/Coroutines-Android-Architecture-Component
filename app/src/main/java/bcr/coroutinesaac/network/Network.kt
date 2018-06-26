package bcr.coroutinesaac.network

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException
import java.io.IOException

object Network {

    fun <T> request(call: Deferred<T>, callback: NetworkCallback<T>) {
        request(call, callback.success, callback.error)
    }

    private fun <T> request(call: Deferred<T>, onSuccess: ((T) -> Unit)?, onError: ((RequestError) -> Unit)?) {
        launch(UI) {
            try {
                onSuccess?.let {
                    onSuccess(call.await())
                }
            } catch (httpException: HttpException) {
                // a non-2XX response was received
                val er = RequestError(httpException)
                onError?.let { it(er) }
            } catch (t: Throwable) {
                    // a networking or data conversion error
                    val er = RequestError(t)
                    onError?.let { it(er) }
                }
            }
    }

}