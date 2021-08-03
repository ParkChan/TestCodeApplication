package com.example.lib.coroutine.chapter2

import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.jupiter.api.Test

/*
일반적으로
부모 Scope(Job)이 있고
하위에 Child 가 4개가 있다고 가정
Child 중하나가 실패하면 부모 코루틴에게 전파되고 모든 자식 루틴에게 전파되어 실행이 취소된다.
그후 스레드의 기본 예외 처리기까지 전파 된다.

SupervisorJob을 사용하면 발생한 예외를 부모에게 전달하지 않는다.
다른 자식 루틴에게 전파되지 않는다.
 */
class CoroutineSupervisorJob {

    private val coroutineScope = TestCoroutineScope(
        Job() + TestCoroutineDispatcher()
                + CoroutineName("SupervisorJob")
    )

    @Test
    fun `coroutineScope에서 실행`() {
        coroutineScope.launch {
            try {
                coroutineScope {
                    launch {
                        println("task 1 입니다")
                        throw Exception()
                    }
                    launch {
                        println("task 2 입니다")
                    }
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    @Test
    fun `supervisorScope에서 실행`() {
        coroutineScope.launch {
            try {
                supervisorScope {
                    launch {
                        try {
                            println("task 1 입니다")
                            throw Exception()
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                    launch {
                        println("task 2 입니다")
                    }
                }
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}