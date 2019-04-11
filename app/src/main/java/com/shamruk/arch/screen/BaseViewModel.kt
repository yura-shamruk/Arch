package com.shamruk.arch.screen

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.shamruk.arch.api.ProjectsRepository
import com.shamruk.arch.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel(){

    protected val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun start()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}