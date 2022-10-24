package com.example.lib.coroutine.chapter5

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.debug.junit5.CoroutinesTimeout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.io.IOException

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
    fun `데이터를 발행하는 시간보다 처리하는 시간이 더 긴 경우 collectLastest를 사용하면 마지막 데이터만이 소비됩니다`() = runBlocking {
        val flow = flow<Int> {
            (0..10).forEach {
                //데이터를 0.1초마다 발행
                delay(100)
                emit(it)
            }
        }
        flow.collectLatest {
            //데이터를 1초동안 처리하는 중...
            delay(1000)
            println("number >>> $it")
        }
    }

    @Test
    fun `한 번 시작된 데이터 소비는 끝날때까지 하고, 데이터 소비가 끝난 시점의 가장 최신 데이터를 다시 소비됩니다`() = runBlocking {
        val flow = flow<Int> {
            for(i in 0..10){
                emit(i)
                delay(300)
            }
        }
        flow.onEach {
            println("number >>> emit $it")
        }.conflate().collect {
            delay(3000)
            println("number >>> $it")
        }
    }

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
            .flatMapLatest {
                requestFlow(it)
            }.collect { value ->
                println("$value at ${System.currentTimeMillis() - startTime} ms from start")
            }
    }

    private fun requestFlow(i: Int): Flow<String> = flow {
        try {
            emit("$i: First")
            delay(500)
            emit("$i: Second")
        } catch (cancel: CancellationException) {
            println("cancelled!!")
        }
    }

    /**
     * https://amitshekhar.me/blog/retry-operator-in-kotlin-flow
     */
    @Test
    fun `Kotlin Flow의 재시도 retry 연산자 `() = runBlocking {
        doLongRunningTask()
            .flowOn(Dispatchers.Default)
            .retry(retries = 3) { cause ->
                if (cause is IOException) {
                    delay(1000)
                    println("retry true")
                    return@retry true
                } else {
                    println("retry false")
                    return@retry false
                }
            }.catch { error ->
                // error
                println(error)
            }.collect {
                // success
                println(it)
            }
    }

    @Test
    fun `Kotlin Flow의 재시도 retryWhen 연산자 `() = runBlocking {
        doLongRunningTask()
            .flowOn(Dispatchers.Default)
            .retryWhen { cause, attempt ->
                if (cause is IOException && attempt < 3) {
                    delay(1000)
                    println("retry true")
                    return@retryWhen true
                } else {
                    println("retry false")
                    return@retryWhen false
                }
            }.catch { error ->
                // error
                println(error)
            }.collect {
                // success
                println(it)
            }
    }

    private fun doLongRunningTask(): Flow<Int> {
        return flow {
            // your code for doing a long running task
            // Added delay, random number, and exception to simulate
            delay(2000)
            val randomNumber = (0..2).random()
            if (randomNumber == 0) {
                throw IOException()
            } else if (randomNumber == 1) {
                throw IndexOutOfBoundsException()
            }
            delay(2000)
            emit(0)
        }
    }
}