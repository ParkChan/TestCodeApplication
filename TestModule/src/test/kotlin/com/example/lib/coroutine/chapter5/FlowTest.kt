package com.example.lib.coroutine.chapter5

import kotlinx.coroutines.debug.junit5.CoroutinesTimeout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * (참조)https://play.kotlinlang.org/hands-on/Introduction%20to%20Coroutines%20and%20Channels/08_Channels
 * (참조)[https://bb-library.tistory.com/248]
 * (참조:태환)[https://thdev.tech/kotlin/2021/01/09/Coroutines-Callback/]
 */
@CoroutinesTimeout(10000)
class FlowTest {

    @Test
    fun testFlow() = runBlocking {
        flow<Int> {
            (0..10).forEach {
                println("emit $it")
                emit(it) // 1을 입력하면
            }
        }.collect {
            println("collect $it") // 1이 출력되고,
            delay(1) // 1ms를 대기한다. 그동안 blocking 한다.
        }
        delay(100)
    }



}