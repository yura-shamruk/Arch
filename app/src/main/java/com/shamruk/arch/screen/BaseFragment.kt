package com.shamruk.arch.screen

import androidx.fragment.app.Fragment
import com.shamruk.arch.utils.RxUtil
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment : Fragment(){

    protected var compactDisposable: CompositeDisposable = CompositeDisposable()

    protected fun unbindViewModel(){
        compactDisposable.clear()
    }

    protected abstract fun bindViewModel()
}