package com.shamruk.arch.api

import com.shamruk.arch.model.LoginData
import com.shamruk.arch.model.User
import com.shamruk.arch.model.UserDetails
import io.reactivex.Single

object ProjectsRepository {
    fun getTestList():Single<List<User>> {
        return Single.create { emitter ->
            val testList = MockServer.getUsers()
            emitter.onSuccess(testList)
        }
    }

    fun getLoginTitles():Single<UserDetails> {
        return Single.create { emitter ->
            val testList = MockServer.getUserDetails()
            emitter.onSuccess(testList)
        }
    }

    fun login() : Single<LoginData> {
        return Single.create { emitter ->
            val loginData = MockServer.login()
            emitter.onSuccess(loginData)
        }
    }
}