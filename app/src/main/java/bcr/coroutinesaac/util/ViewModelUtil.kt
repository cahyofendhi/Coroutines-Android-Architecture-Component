package bcr.coroutinesaac.util

import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

//inline fun <reified T : ViewModel> FragmentActivity.getViewModel(): T =
//        ViewModelProviders.of(this)[T::class.java]
//
//inline fun <reified T: ViewModel> FragmentActivity.getActivityFactoryViewModel(factory: ViewModelProvider.NewInstanceFactory):
//        T = ViewModelProviders.of(this, factory)[T::class.java]
//
//inline fun <reified T : ViewModel> Fragment.getFragmentViewModel(): T =
//        ViewModelProviders.of(this)[T::class.java]
//
//inline fun <reified T: ViewModel> Fragment.getFactoryViewModel(factory: ViewModelProvider.NewInstanceFactory):
//        T = ViewModelProviders.of(this, factory)[T::class.java]
//
//inline fun <T> LiveData<T>.subscribe(lifecycle: LifecycleOwner, crossinline onChanged: (T) -> Unit) {
//    observe(lifecycle, Observer { it?.run(onChanged) })
//}