package com.example.lib.coroutine.chapter1

import com.example.lib.coroutine.MainCoroutineRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep

/*
CoroutineContext 코루틴의 동작을 정의하는 요소 집합입니다. 다음으로 구성되어 있습니다.
Job — 코루틴의 수명 주기를 제어합니다.
CoroutineDispatcher — 작업을 적절한 스레드로 디스패치합니다.
CoroutineName — 디버깅에 유용한 코루틴의 이름입니다.
CoroutineExceptionHandler — 잡히지 않은 예외를 처리하며 시리즈의 3부에서 다룹니다.
 */
class CoroutineBasic {

    //private val scope = CoroutineScope(Job() + Dispatchers.Main)
    @ExperimentalCoroutinesApi
    private val scope = TestCoroutineScope(
        Job() + TestCoroutineDispatcher()
                + CoroutineName("테스트 용도의 코루틴 스레드")
    )

    @ExperimentalCoroutinesApi
    @Test
    fun `코루틴 실행`() {
        val job = scope.launch {
            try {
                println("New coroutine!!")
                println("You can call suspend functions")
                println("${Thread.currentThread()}")
            } catch (e: Throwable) {
                cancel()
            }
        }
    }

    @Test
    fun `코루틴 정지`() {
        scope.cancel()
    }

    @Test
    fun `코루틴내에서 CoroutineContext를 변경하기`() {
        val job = scope.launch {
            withContext(Dispatchers.Default) {
                println("withContext New coroutine!!")
                println("You can call suspend functions")
                println("${Thread.currentThread()}")
            }
        }
    }

    /**
     * https://kotlinlang.org/docs/coroutines-basics.html#your-first-coroutine
     */
    @Test
    fun `코루틴 첫 시작`() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            delay(5_000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello") // main coroutine continues while a previous one is delayed
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Test
    @ExperimentalCoroutinesApi
    fun `MainCoroutineRule 테스트`() = mainCoroutineRule.runBlockingTest {
        launch { // launch a new coroutine and continue
            delay(5_000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello") // main coroutine continues while a previous one is delayed
    }

    //[참조](https://developer88.tistory.com/2130)
    @Test
    @ExperimentalCoroutinesApi
    fun `결과값을 전달할게 있을때 사용 Deferred await 테스트`() = runBlockingTest {
        val deferred: Deferred<Int> = async {
            loadData()
        }
        println("waiting...")
        val result = deferred.await()
        println(result)
//        println(deferred.start())       //결과값을 리턴x 코루틴이 끝날때까지 기다리지 않음 : await과의 차이
    }

    private suspend fun loadData(): Int {
        println("loading...")
        delay(3_000L)
        println("loaded!")
        return 42
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `결과값을 전달할게 없고 새 코루틴에서 실행하는 launch`() = runBlockingTest {
        val job = launch {
            println(loadData())
        }
        println("waiting...")
        job.join()
        println("End")
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `코루틴의 Context 변경 또는 Dispatchers를 변경하여 실행중인 스레드를 변경하는 withContext`() = runBlocking {
        val job = launch {
            withContext(Dispatchers.IO) {
                println(loadData())
            }
        }
        delay(1_000L)
        println("waiting...")
        job.join()
        println("End")
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `여러개의 비동기 처리의 결과를 완료 되기까지 기다렸다가 수행 되는 예제 코드`() = runBlockingTest {
        val deferreds: List<Deferred<Int>> = (1..3).map {
            async {
                delay(1000L * it)
                println("Loading $it")
                it
            }
        }
        val sum = deferreds.awaitAll().sum()
        println("$sum")
    }

    /**
     * 아래부터는 하단 링크 참조를 통한 실습 예제 내용 입니다.
     * https://kotlinlang.org/docs/coroutines-basics.html#scope-builder-and-concurrency
     */
    @Test
    @ExperimentalCoroutinesApi
    fun `first suspending function`() = runBlocking {
        launch { doWorld1() }
        println("Hello") // main coroutine continues while a previous one is delayed
    }
}

private suspend fun doWorld1() {
    delay(3000L) // non-blocking delay for 1 second (default time unit is ms)
    println("World!") // print after delay
}

@Test
@ExperimentalCoroutinesApi
fun `coroutineScope Test`() = runBlocking {
    doWorld2()
}

private suspend fun doWorld2() = coroutineScope {
    launch {
        delay(3000L)
        println("World!")
    }
    println("Hello")
}

@Test
@ExperimentalCoroutinesApi
fun `동시성 테스트`() = runBlocking {
    doWorld3()
    println("Done")
}

private suspend fun doWorld3() = coroutineScope { // this: CoroutineScope
    launch {
        delay(2000L)
        println("World 2")
    }
    launch {
        delay(1000L)
        println("World 1")
    }
    println("Hello")
}

@Test
@ExperimentalCoroutinesApi
fun `An explicit job`() = runBlocking {
    val job = launch { // launch a new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello")
    job.join() // wait until child coroutine completes
    println("Done")
}

@Test
@ExperimentalCoroutinesApi
fun `코루틴의 경량화 확인하기`() = runBlocking {
    repeat(100_000) { // launch a lot of coroutines
        launch {
            delay(5000L)
            print(".")
        }
    }
}

@Test
@ExperimentalCoroutinesApi
fun `코루틴의 경량화 스레드와의 비교`() {
    repeat(100_000) {
        Thread {
            sleep(5000L)
            print(".")
        }.start()
    }
    //당신의 코드는 일종의 메모리 부족 오류를 일으킬 가능성이 높습니다.
}