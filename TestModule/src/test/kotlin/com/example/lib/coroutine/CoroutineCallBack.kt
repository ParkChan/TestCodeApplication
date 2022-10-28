package com.example.lib.coroutine

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


// https://amitshekhar.me/blog/callback-to-coroutines-in-kotlin
class CoroutineCallBack {

    object SampleLibrary {
        interface Listener {
            fun onSuccess(result: Boolean)
            fun onError(throwable: Throwable)
        }

        fun doSomethig(listener: Listener) {
            println("뭔가를 합니다.")
        }

        fun cancel() {
            println("작업을 취소합니다.")
        }
    }

    @Test
    fun `아래와 같이 라이브러리를 사용할 수 있다고 가정`() = runBlocking {
        SampleLibrary.doSomethig(object : SampleLibrary.Listener {
            override fun onSuccess(result: Boolean) {
            }

            override fun onError(throwable: Throwable) {
            }
        })
    }

    @Test
    fun `Coroutine 방식으로 사용하기`() = runBlocking {

        suspend fun doSomethig(): Boolean {
            return suspendCoroutine { continuation ->
                SampleLibrary.doSomethig(object : SampleLibrary.Listener {
                    override fun onSuccess(result: Boolean) {
                        continuation.resume(result)
                    }

                    override fun onError(throwable: Throwable) {
                        continuation.resumeWithException(throwable)
                    }

                })
            }
        }

        // 이제 위의 기능을 아래와 같이 사용할 수 있다.
        launch {
            val result = doSomethig()
        }
    }

    @Test
    fun `작업 취소 지원`() = runBlocking {
        suspend fun doSomethig(): Boolean {

            // suspendCancellableCoroutine 사용!!
            return suspendCancellableCoroutine { continuation ->
                SampleLibrary.doSomethig(object : SampleLibrary.Listener {
                    override fun onSuccess(result: Boolean) {
                        continuation.resume(result)
                    }

                    override fun onError(throwable: Throwable) {
                        continuation.resumeWithException(throwable)
                    }
                })

                continuation.invokeOnCancellation {
                    SampleLibrary.cancel()
                }
            }
        }
        // 이제 위의 기능을 아래와 같이 사용할 수 있다.
        launch {
            val result = doSomethig()
        }

    }
}