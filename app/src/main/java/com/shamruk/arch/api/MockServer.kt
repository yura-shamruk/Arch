package com.shamruk.arch.api

import com.shamruk.arch.model.LoginTitles

class MockServer {
    companion object {
        fun getTestList():List<String> {
            Thread.sleep(3000)
            return listOf("First", "Second", "Third")
        }

        fun getLoginTitles(): LoginTitles {
            Thread.sleep(3000)
            return LoginTitles("Title", "Subtitle")
        }
    }
}