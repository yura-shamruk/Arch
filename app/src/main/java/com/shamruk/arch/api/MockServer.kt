package com.shamruk.arch.api

import com.shamruk.arch.model.UserDetails

class MockServer {
    companion object {
        fun getTestList():List<String> {
            Thread.sleep(3000)
            return listOf("First", "Second", "Third")
        }

        fun getUserDetails(): UserDetails {
            Thread.sleep(3000)
            return UserDetails("Title", "Subtitle",
                "https://www.nretnil.com/avatar/LawrenceEzekielAmos.png")
        }
    }
}