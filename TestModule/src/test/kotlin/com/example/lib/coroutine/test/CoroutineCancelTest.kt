package com.example.lib.coroutine.test

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

//참고 : https://thdev.tech/kotlin/2020/12/29/kotlin_effective_17/
class CoroutineCancelTest {

    @Test
    fun `취소가 되지않는 예시`() = runBlocking{
        val job = SupervisorJob()
        CoroutineScope(Dispatchers.IO + job).launch {
            suspendLoop2()
        }
        delay(50)
        job.cancel()
        delay(500)
        println("Job canceled")
    }

    @Test
    fun `취소가 잘 동작하는 예시`() = runBlocking{
        val job = SupervisorJob()
        CoroutineScope(Dispatchers.IO + job).launch {
            suspendLoop()
        }
        delay(50)
        job.cancel()
        delay(500)
        println("Job canceled")
    }

    private suspend fun suspendLoop() = withContext(Dispatchers.IO) {
        while (isActive) {
            println("Job active")
        }

    }

    private suspend fun suspendLoop2() = withContext(Dispatchers.IO) {
        while (true) {
            println("Job active")
        }
    }
}