package com.shamruk.arch.api

import com.shamruk.arch.model.UserDetails

class MockServer {
    companion object {
        fun getTestList():List<String> {
            Thread.sleep(2000)
            return listOf("First", "Second", "Third")
        }

        fun getUserDetails(): UserDetails {
            Thread.sleep(2000)
            return UserDetails("Title", "Subtitle",
                "https://www.nretnil.com/avatar/LawrenceEzekielAmos.png")
        }
    }
}