package com.shamruk.arch.screen

import android.util.Log
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

class MainListViewModel : BaseViewModel() {

    val isProgressVisible = MutableLiveData<Boolean>()

    val items = MutableLiveData<List<User>>().apply { value = emptyList() }

    override fun start() {
        showProgress()
        disposables.add(ProjectsRepository.getTestList()
            .compose(RxUtil.ioSingle())
            .doFinally { hideProgress() }
            .subscribe(this::onTestListReceive, this::onTestListError))
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

    companion object {
        @BindingAdapter("items")
        @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<User>) {
            val adapter = recyclerView.adapter
            if(adapter is MainListAdapter){
                adapter.replaceData(items)
            }
        }
    }
}
