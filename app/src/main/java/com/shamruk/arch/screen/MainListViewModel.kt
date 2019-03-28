package com.shamruk.arch.screen

import androidx.lifecycle.ViewModel;
import com.shamruk.arch.api.ProjectsRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class MainListViewModel : ViewModel() {
    private var loadingStateSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getTestList():Observable<List<String>>{
        return ProjectsRepository.getTestList()
            .doOnSubscribe {loadingStateSubject.onNext(true)}
            .doOnSuccess { loadingStateSubject.onNext(false) }
            .toObservable()
    }

    fun getLoadingStateObservable(): Observable<Boolean> {
        return loadingStateSubject.hide()
    }
}
