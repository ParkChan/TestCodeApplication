package com.example.lib.coroutine.test

import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

class Testing {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDelayInSuspend() = runBlockingTest {
        val realStartTime = System.currentTimeMillis()
        val virtualStartTime = currentTime

        foo()

        println("${System.currentTimeMillis() - realStartTime} ms")  // ~ 6 ms
        println("${currentTime - virtualStartTime} ms")              // 1000 ms
    }

    suspend fun foo() {
        delay(1000)     // auto-advances without delay
        println("foo")  // executes eagerly when foo() is called
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDelayInLaunch() = runBlockingTest {
        val realStartTime = System.currentTimeMillis()
        val virtualStartTime = currentTime

        bar()

        println("${System.currentTimeMillis() - realStartTime} ms")  // ~ 11 ms
        println("${currentTime - virtualStartTime} ms")              // 1000 ms
    }

    suspend fun bar() = coroutineScope {
        launch (Dispatchers.Default){
            delay(1000)     // auto-advances without delay
            println("bar")  // executes eagerly when bar() is called
        }
    }
}