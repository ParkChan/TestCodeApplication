package com.example.lib.coroutine.chapter5

import kotlinx.coroutines.debug.junit5.CoroutinesTimeout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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

    val intFlow = flowOf(1, 2, 3).onEach { delay(10) }
    val charFlow = flowOf("A", "B", "C","D","E").onEach { delay(20) }

    @Test
    fun `combine 테스트 두 개의 Flow를 결합해서 하나의 결과를 만들고 싶을 때 combine을 사용합니다`() = runBlocking {
        println("Start")
        val startTime = System.currentTimeMillis()
        intFlow.combine(charFlow) { num, character ->
            "$num / $character"
        }.collect {
            println(it)
        }
        println("실행 시간 ${System.currentTimeMillis() - startTime}")
        println("End")
    }

    @Test
    fun `zip 테스트 combine과 다른 점은 두 Flow의 방출 값을 모아서 한 번에 방출`() = runBlocking {
        intFlow.zip(charFlow) { num, character ->
            "$num / $character"
        }.collect {
            println(it)
        }
    }

    @Test
    fun `flattenMerge 테스트 다른 방출 값과 관계도 없으며, 여러 개의 Flow들의 방출 값이 하나의 Flow의 방출`() = runBlocking {
        val startTime = System.currentTimeMillis()
        flowOf(intFlow, charFlow).flattenMerge().collect {
            println("$it")
        }
        println("실행 시간 ${System.currentTimeMillis() - startTime}")
    }
}