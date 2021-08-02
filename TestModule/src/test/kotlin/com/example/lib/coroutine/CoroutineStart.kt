package com.example.lib.coroutine

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.jupiter.api.Test

/*
CoroutineContext 코루틴의 동작을 정의하는 요소 집합입니다. 다음으로 구성되어 있습니다.
Job — 코루틴의 수명 주기를 제어합니다.
CoroutineDispatcher — 작업을 적절한 스레드로 디스패치합니다.
CoroutineName — 디버깅에 유용한 코루틴의 이름입니다.
CoroutineExceptionHandler — 잡히지 않은 예외를 처리하며 시리즈의 3부에서 다룹니다.
 */
class CoroutineStart {

    //private val scope = CoroutineScope(Job() + Dispatchers.Main)
    @ExperimentalCoroutinesApi
    private val scope = TestCoroutineScope(Job() + TestCoroutineDispatcher()
            + CoroutineName("테스트 중인 코루틴"))

    @ExperimentalCoroutinesApi
    @Test
    fun `코루틴 기본 동작`() {
        val job = scope.launch {
            println("새로운 코루틴 동작쓰!!")
            println("${Thread.currentThread()}")
        }
    }
}