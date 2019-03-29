package com.shamruk.arch.api

import com.shamruk.arch.model.LoginTitles
import io.reactivex.Single

object ProjectsRepository {
    fun getTestList():Single<List<String>> {
        return Single.create { emitter ->
            val testList = MockServer.getTestList()
            emitter.onSuccess(testList)
        }
    }

    fun getLoginTitles():Single<LoginTitles> {
        return Single.create { emitter ->
            val testList = MockServer.getLoginTitles()
            emitter.onSuccess(testList)
        }
    }
}