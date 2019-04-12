package com.shamruk.arch.screen

import android.util.Log
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.shamruk.arch.adapter.MainListAdapter
import com.shamruk.arch.api.ProjectsRepository
import com.shamruk.arch.model.User
import com.shamruk.arch.utils.RxUtil
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MainListViewModel : BaseViewModel() {

    companion object {
        const val TAG: String = "MainListViewModel"

        @BindingAdapter("items")
        @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<User>) {
            val adapter = recyclerView.adapter
            if(adapter is MainListAdapter){
                adapter.replaceData(items)
            }
        }
    }

    val isProgressVisible = MutableLiveData<Boolean>()

    val toastSubject =  BehaviorSubject.create<String>()

    val items = MutableLiveData<List<User>>().apply { value = emptyList() }

    override fun start() {
        showProgress()
        disposables.add(ProjectsRepository.getTestList()
            .compose(RxUtil.ioSingle())
            .doFinally { hideProgress() }
            .subscribe(this::onTestListReceive, this::onTestListError))
    }

    fun onListItemClick(user:User){
        Log.d(TAG, "user: " + user.name)
        toastSubject.onNext("click: " + user.name)
    }


    private fun onTestListError(error: Throwable) {
        Log.d(MainListFragment.TAG, "onTestListError: " + error.message)
    }


    private fun onTestListReceive(testList: List<User>) {
        Log.d(MainListFragment.TAG, "onTestListReceive:$testList")
        items.value = testList
    }

    private fun showProgress(){
        isProgressVisible.value = true
    }

    private fun hideProgress(){
        isProgressVisible.value = false
    }

}
