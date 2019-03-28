package com.shamruk.arch.utils

import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxUtil {
    companion object {
        fun <T> io(): ObservableTransformer<T, T> {
            return ObservableTransformer { observable ->
                observable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}