package com.shamruk.arch

import android.app.Application
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins



class ArchApplication : Application(){


    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable -> Log.e("ArchApplication", throwable.message)}
    }
}