package com.shamruk.arch.api

import com.shamruk.arch.model.LoginData
import com.shamruk.arch.model.User
import com.shamruk.arch.model.UserDetails

class MockServer {
    companion object {
        fun getUsers():List<User> {
            Thread.sleep(2000)
            return listOf(User("Jhon", "Brown"),
                User("James", "Brown"),
                User("Alice", "Brown"))
        }

        fun getUserDetails(): UserDetails {
            Thread.sleep(2000)
            return UserDetails("Title", "Subtitle",
                "https://www.nretnil.com/avatar/LawrenceEzekielAmos.png")
        }

        fun login(): LoginData {
            Thread.sleep(2000)
            return LoginData("true")
        }
    }
}