package com.shamruk.arch.api

import io.reactivex.Single

object ProjectsRepository {
    fun getTestList():Single<List<String>> {
        return Single.create { emitter ->
            Thread.sleep(3000)
            val listOf = listOf("1", "2", "3")
            emitter.onSuccess(listOf)
        }
    }
}