package com.shamruk.arch.api

class MockServer {
    companion object {
        fun getTestList():List<String> {
            Thread.sleep(3000)
            return listOf("First", "Second", "Third")
        }
    }
}