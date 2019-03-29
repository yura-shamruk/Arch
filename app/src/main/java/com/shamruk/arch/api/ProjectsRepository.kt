package com.shamruk.arch.api

import io.reactivex.Single

object ProjectsRepository {
    fun getTestList():Single<List<String>> {
        return Single.create { emitter ->
            val testList = MockServer.getTestList()
            emitter.onSuccess(testList)
        }
    }
}