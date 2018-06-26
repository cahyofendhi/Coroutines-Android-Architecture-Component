package bcr.coroutinesaac.util

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import bcr.coroutinesaac.BuildConfig

fun debug(message: String){
    if (BuildConfig.DEBUG) Log.d("Result", message)
}

fun Activity.toast(message: String){
    Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
}
