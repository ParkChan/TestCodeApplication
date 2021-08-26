package com.example.lib.coroutine.chapter4

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

/**
 * (참조)[https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/topics/exception-handling.md]
 */
class Exception {

    /*
    출력 결과
    Throwing exception from launch
    Exception in thread "DefaultDispatcher-worker-2 @coroutine#2" java.lang.IndexOutOfBoundsException
    Joined failed job
    Throwing exception from async
    Caught ArithmeticException
    */
    @DelicateCoroutinesApi
    @Test
    fun `테스트`() = runBlocking {
        val job = GlobalScope.launch { // 실행이 있는 루트 코루틴
            println("throwing exception from launch ")
            throw  IndexOutOfBoundsException() // 다음으로 콘솔에 인쇄됩니다. Thread.defaultUncaughtExceptionHandler
        }
        job.join()
        println(" Joined failed job ")
        val deferred = GlobalScope.async { // 비동기가 있는 루트 코루틴
            println(" throwing exception from async ")
            throw  ArithmeticException() // 아무 것도 인쇄되지 않으며 사용자가 await를 호출해야 합니다.
        }
        try {
            deferred.await()
            println("Unreached")
        } catch (e: ArithmeticException) {
            println("Caught ArithmeticException")
        }
    }

    @DelicateCoroutinesApi
    @Test
    fun main() = runBlocking {
        //sampleStart
        val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }
        val job = GlobalScope.launch(handler) { // root coroutine, running in GlobalScope
            throw AssertionError()
        }
        val deferred = GlobalScope.async(handler) { // also root, but async instead of launch
            throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
        }
        joinAll(job, deferred)
        //sampleEnd
    }
}