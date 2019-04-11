package com.shamruk.arch.screen

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun start()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}