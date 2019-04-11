package com.shamruk.arch.api

import com.shamruk.arch.model.LoginUserDetails

class MockServer {
    companion object {
        fun getTestList():List<String> {
            Thread.sleep(3000)
            return listOf("First", "Second", "Third")
        }

        fun getUserDetails(): LoginUserDetails {
            Thread.sleep(3000)
            return LoginUserDetails("Title", "Subtitle",
                "https://www.nretnil.com/avatar/LawrenceEzekielAmos.png")
        }
    }
}