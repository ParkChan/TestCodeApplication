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


    //그렇다면 가장 처음에 보았던 공식 문서 예제는 어떻게 취소가 되었을까?
    //suspend 함수가 Exception을 던진다.
    //비밀은 delay() 에 있다.
    //정확하게 말하자면 suspend 함수가 내부적으로
    //isActive를 체크하여 isActive가 false일 때 Exception을 던지는 것이다.
    //cancel()의 타겟인 코루틴 내부에 suspend 함수가 있다면, 그 suspend 함수가 CancellationException을 throw한다.
    //그러나 suspend가 없다면, CancellationException 는 throw되지 않는다.
    //이런 경우에는 isActive를 체크하여 명시적으로 CancellationException 를 던질 수 있다.

}