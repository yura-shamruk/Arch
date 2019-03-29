package com.shamruk.arch.screen

import androidx.lifecycle.ViewModel;
import com.shamruk.arch.api.ProjectsRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class MainListViewModel : ViewModel() {
    private var loadingStateSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    private var testList: List<String>? = null

    fun getTestList(): Single<List<String>> {
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

    fun getLoadingStateObservable(): Observable<Boolean> {
        return loadingStateSubject.hide()
    }
}
