package com.shamruk.arch.api

import com.shamruk.arch.model.LoginUserDetails
import io.reactivex.Single

object ProjectsRepository {
    fun getTestList():Single<List<String>> {
        return Single.create { emitter ->
            val testList = MockServer.getTestList()
            emitter.onSuccess(testList)
        }
    }

    fun getUserDetails():Single<LoginUserDetails> {
        return Single.create { emitter ->
            val testList = MockServer.getUserDetails()
            emitter.onSuccess(testList)
        }
    }
}