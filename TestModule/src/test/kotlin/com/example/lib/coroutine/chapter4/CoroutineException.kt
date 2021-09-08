package com.example.lib.coroutine.chapter4

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import java.io.IOException

class CoroutineException {

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
    fun `Exception propagation`() = runBlocking {
        /**
         * (참조)[https://github.com/Kotlin/kotlinx.coroutines/blob/master/docs/topics/exception-handling.md]
         */
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
    fun `CoroutineExceptionHandler`() = runBlocking {
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
    }

    @DelicateCoroutinesApi
    @Test
    fun `Cancellation and exceptions`() = runBlocking {
        val job = launch {
            val child = launch {
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    println("Child is cancelled")
                }
            }
            yield()
            println("Cancelling child")
            child.cancel()  //작업을 취소하려면 cancel을 이용
            child.join()    //명시적으로 코루틴이 완료되길 기다림
            yield()
            println("Parent is not cancelled")
        }
        job.join()
    }

    /**
     * When multiple children of a coroutine fail with an exception,
     * the general rule is "the first exception wins", so the first exception gets handled.
     * All additional exceptions that happen after the first one are attached to the first exception as suppressed ones.
     */
    @DelicateCoroutinesApi
    @Test
    fun `Exceptions aggregation IOException이 발생했지만 핸들러에 ArithmeticException이 전달됨`() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
        }
        val job = GlobalScope.launch(handler) {
            launch {
                try {
                    delay(Long.MAX_VALUE) // it gets cancelled when another sibling fails with IOException
                } catch (e: Exception) {
                    println("exception ${e.message}")
                } finally {
                    throw ArithmeticException() // the second exception
                }
            }
            launch {
                try {
                    delay(100)
                    throw IOException() // the first exception
                } catch (e: Exception) {
                    println("exception $e")
                } finally {
                    throw IOException() // the first exception
                }
            }
            delay(Long.MAX_VALUE)
        }
        job.join()
    }

    @DelicateCoroutinesApi
    @Test
    fun `Exceptions aggregation 취소오류가 발생했지만 핸들러에 IOException이 전달됨`() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }
        val job = GlobalScope.launch(handler) {
            val inner = launch { // all this stack of coroutines will get cancelled
                launch {
                    launch {
                        throw IOException() // the original exception
                    }
                }
            }
            try {
                inner.join()
            } catch (e: CancellationException) {
                println("Rethrowing CancellationException with original cause")
                throw e // cancellation exception is rethrown, yet the original IOException gets to the handler
            }
        }
        job.join()
    }

    @DelicateCoroutinesApi
    @Test
    fun `Supervision job`() = runBlocking {
        val supervisor = SupervisorJob()
        with(CoroutineScope(coroutineContext + supervisor)) {
            // launch the first child -- its exception is ignored for this example (don't do this in practice!)
            val firstChild = launch(CoroutineExceptionHandler { _, _ -> }) {
                println("The first child is failing")
                throw AssertionError("The first child is cancelled")
            }
            // launch the second child
            val secondChild = launch {
                firstChild.join()
                // Cancellation of the first child is not propagated to the second child
                println("The first child is cancelled: ${firstChild.isCancelled}, but the second one is still active")
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    // But cancellation of the supervisor is propagated
                    println("The second child is cancelled because the supervisor was cancelled")
                }
            }
            // wait until the first child fails & completes
            firstChild.join()
            println("Cancelling the supervisor")
            supervisor.cancel()
            secondChild.join()
        }
    }

    @DelicateCoroutinesApi
    @Test
    fun `Supervision scope`() = runBlocking {
        try {
            supervisorScope {
                val child = launch {
                    try {
                        println("The child is sleeping")
                        delay(Long.MAX_VALUE)
                    } finally {
                        println("The child is cancelled")
                    }
                }
                // Give our child a chance to execute and print using yield
                yield()
                println("Throwing an exception from the scope")
                throw AssertionError()
            }
        } catch (e: AssertionError) {
            println("Caught an assertion error")
        }
    }

    @DelicateCoroutinesApi
    @Test
    fun `Exceptions in supervised coroutines`() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }
        supervisorScope {
            val child = launch(handler) {
                println("The child throws an exception")
                throw AssertionError()
            }
            println("The scope is completing")
        }
        println("The scope is completed")
    }

    //The child throws an exception
    //CoroutineExceptionHandler got The child throws an exception
    //The scope is completing
    //The scope is completed
}