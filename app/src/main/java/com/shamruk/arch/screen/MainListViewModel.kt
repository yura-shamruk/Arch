package com.shamruk.arch.screen

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.shamruk.arch.adapter.MainListAdapter
import com.shamruk.arch.api.ProjectsRepository
import com.shamruk.arch.utils.RxUtil
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class MainListViewModel : BaseViewModel() {

    val isProgressVisible = MutableLiveData<Boolean>()

//    var items: List<String> = ArrayList<String>()
    val items = MutableLiveData<List<String>>().apply { value = emptyList() }

    companion object {
        @BindingAdapter("items")
        @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<String>) {
            val adapter = recyclerView.adapter
            if(adapter is MainListAdapter){
                adapter.replaceData(items)
            }
        }
    }


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

    private fun onTestListReceive(testList: List<String>) {
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
