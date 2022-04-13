package com.example.lib.coroutine.test

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class CoroutineCancellation {

    @Test
    fun `공식문서 예제 정상적인 코루틴 cancel() 동작 테스트`() = runBlocking {
        val job = launch {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancel() // cancels the job
        job.join() // waits for job's completion
        println("main: Now I can quit.")
    }

    @Test
    fun `동작이 멈추지 않고 계속해서 실행되는 경우`() = runBlocking {
        val job = launch {
            while (true) {
                println("while")
            }
        }
        delay(1000L) // delay a bit
        println("main: I'm tired of waiting!")
        job.cancel() // cancels the job
        job.join() // waits for job's completion
        println("main: Now I can quit.")
    }

    @Test
    fun `메인 쓰레드를 Dispatcher Default로 변경하면 동작은 바뀌지만 끝나지 않는것은 동일`() = runBlocking {
        val job = launch(Dispatchers.Default) {
            while (true) {
            }
        }
        delay(1000L) // delay a bit
        println("main: I'm tired of waiting!") //백그라운드 job을 cancel시키고, job이 종료될 때 까지 join 한다.

        job.cancel() // cancels the job
        job.join() // waits for job's completion
        println("main: Now I can quit.")
    }

}