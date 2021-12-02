package com.example.lib.coroutine.chapter5

import kotlinx.coroutines.CancellationException
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

    private val intFlow = flowOf(1, 2, 3).onEach { delay(10) }
    private val charFlow = flowOf("A", "B", "C", "D", "E").onEach { delay(20) }
    private var emptyFlow = emptyFlow<String>()

    @Test
    fun `두 개의 Flow를 결합해서 하나의 결과를 만들고 싶을 때 combine을 사용합니다`() = runBlocking {
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
    fun `combine 의 특성을 테스트 합니다`() = runBlocking {
        //두개의 flow 에서 서로 다른 타이밍으로 값이 방출될때 최신값만을 기준으로 두개의 방출값을 연산함
        //둘중 하나라도 값이 없으면 combine이 수행되지 않음
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
    fun `zip 의 특성을 테스트 합니다 `() = runBlocking {
        //flow 가 개수가 다르면 적은 개수에 맞춰서 출력됨
        //두개의 flow 의 방출 타이밍이 서로 다르면 느린 flow의 방출값을 기다림
        intFlow.zip(charFlow) { num, character ->
            "$num / $character"
        }.collect {
            println(it)
        }
    }

    @Test
    fun `flattenMerge 다른 방출 값과 관계도 없으며, 여러 개의 Flow들의 방출 값이 하나의 Flow의 방출`() = runBlocking {
        val startTime = System.currentTimeMillis()
        flowOf(intFlow, charFlow).flattenMerge().collect {
            println("$it")
        }
        println("실행 시간 ${System.currentTimeMillis() - startTime}")
    }

    @Test
    fun `flatMapLatest 이전에 대기중이거나 동작중인 flow는 cancel`() = runBlocking {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow().onEach { delay(100) }
            .flatMapLatest { requestFlow(it) }.collect { value ->
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }

    private fun requestFlow(i: Int): Flow<String> = flow {
        try{
            emit("$i: First")
            delay(500)
            emit("$i: Second")
        }catch (cancel: CancellationException){
            println("cancelled!!")
        }
    }


}