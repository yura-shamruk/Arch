package com.shamruk.arch.screen

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager
import com.shamruk.arch.adapter.MainListAdapter
import com.shamruk.arch.api.ProjectsRepository
import com.shamruk.arch.utils.RxUtil
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.sample_list_fragment.*

class MainListViewModel : BaseViewModel() {

    private var loadingStateSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    private var testListSubject: BehaviorSubject<List<String>> = BehaviorSubject.create()

    private var testList: List<String>? = null


    fun getLoadingStateObservable(): Observable<Boolean> {
        return loadingStateSubject.hide()
    }

    fun getTestListObservable(): Observable<List<String>>{
        return testListSubject.hide()
    }

    override fun start() {
        disposables.add(getTestList()
            .compose(RxUtil.ioSingle())
            .subscribe(this::onTestListReceive, this::onTestListError))
    }


    private fun getTestList(): Single<List<String>> {
        return if (testList == null) {
            ProjectsRepository.getTestList()
                .doOnSubscribe { loadingStateSubject.onNext(true) }
                .doOnSuccess {
                    loadingStateSubject.onNext(false)
                    testList = it
                }
        } else {
            Single.create { emitter ->
                emitter.onSuccess(testList!!)
            }
        }
    }

    private fun onTestListError(error: Throwable) {
        Log.d(MainListFragment.TAG, "onTestListError: " + error.message)
    }

    private fun onTestListReceive(testList: List<String>) {
        Log.d(MainListFragment.TAG, "onTestListReceive:$testList")
        testListSubject.onNext(testList)
    }
}
