package com.example.lib.thread.excutor

import org.junit.jupiter.api.Test
import java.time.LocalTime
import java.util.*
import java.util.concurrent.*
import kotlin.collections.ArrayList

class ExcutorTest {

    @Test
    fun `executor 는 명시적으로 종료시켜야만 한다 그렇지 않으면 좋료되지 않고 다른 작업을 기다린다`() {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.submit {
            val threadName = Thread.currentThread().name
            println("Hello $threadName")
        }
        executor.shutdown()         //모든 작업이 완료되면 종료
        //executor.shutdownNow()    //즉각 종료
    }

    @Test
    fun `executor의 권장 종료 방법`() {

        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.submit {
            val threadName = Thread.currentThread().name
            TimeUnit.SECONDS.sleep(2)
            println("Hello $threadName")
        }

        try {
            println("attempt to shutdown executor")
            executor.shutdown()
            executor.awaitTermination(5, TimeUnit.SECONDS)
        } catch (e: InterruptedException) {
            System.err.println("tasks interrupted")
        } finally {
            if (!executor.isTerminated) {
                System.err.println("cancel non-finished tasks")
            }
            executor.shutdownNow()
            println("shutdown finished")
        }
    }

    @Test
    fun `Runnable과 흡사한 Callable 그리고 Callable의 값을 전달하는 Future`() {
        val task = Callable<Int> {
            try {
                TimeUnit.SECONDS.sleep(1)
                return@Callable 123
            } catch (e: InterruptedException) {
                throw IllegalStateException("task interrupted", e)
            } finally {
                println("End")
            }
        }

        val executor = Executors.newFixedThreadPool(1)  //newSingleThreadExecutord와 동일
        val future = executor.submit(task)

        println("${LocalTime.now()} future done? " + future.isDone)
        val result = future.get()
        println("${LocalTime.now()} future done? " + future.isDone)
        println("${LocalTime.now()} result: $result")
        executor.shutdown()
    }

    @Test
    fun `Future get() 시간 설정하기`() {
        val task = Callable<Int> {
            try {
                TimeUnit.SECONDS.sleep(2)
                return@Callable 123
            } catch (e: InterruptedException) {
                throw IllegalStateException("task interrupted", e)
            }
        }

        val executor = Executors.newFixedThreadPool(1)
        val future = executor.submit(task)

        println("${LocalTime.now()} future done? " + future.isDone)
        val result = future.get(3, TimeUnit.SECONDS)            //1초 동안만 대기
        println("${LocalTime.now()} future done? " + future.isDone)
        println("${LocalTime.now()} result: $result")
        executor.shutdown()
    }

    @Test
    fun `invokeAll 메서드를 통해 일괄적으로 다수의 callable을 등록`() {
        val callableList = listOf(
                callable("task1", 3),
                callable("task2", 2),
                callable("task3", 1)

        )
        val executor = Executors.newWorkStealingPool()
        executor.invokeAll(callableList)
                .stream()
                .map {
                    try {
                        it.get()
                    } catch (e: Exception) {
                        throw IllegalStateException(e)
                    }
                }.forEach { result ->
                    println(result)
                }
    }

    private fun callable(result: String, sleepSeconds: Long): Callable<String> {
        TimeUnit.SECONDS.sleep(sleepSeconds)
        return Callable { result }
    }

    @Test
    fun futureTest() {
        val executor = Executors.newFixedThreadPool(4) as ThreadPoolExecutor
        val futures: MutableList<Future<String>> = ArrayList()

        for (i in 0..9) {
            println(LocalTime.now().toString() + " Execute task " + i)
            // submit()은 인자로 Callable도 받으며 Future를 리턴합니다.
            val result: Future<String> = executor.submit<String> {
                TimeUnit.SECONDS.sleep(2)
                println(LocalTime.now().toString() + " Done task " + i)
                return@submit "finished job $i"
            }
            futures.add(result)
        }

        // future.get()은 Task의 result가 리턴될 때까지 기다립니다.
        for (future in futures) {
            val result: String = future.get()
            println(LocalTime.now().toString() + " " + result)
        }
        executor.shutdown()
    }

    @Test
    fun `3초의 초기 지연 시간이 지난 이후에 실행`() {
        val executor = Executors.newScheduledThreadPool(1)

        val task = Runnable { println("Scheduling: " + System.nanoTime()) }
        val future = executor.schedule(task, 3, TimeUnit.SECONDS)

        TimeUnit.MILLISECONDS.sleep(1337)
        val remainingDelay = future.getDelay(TimeUnit.MILLISECONDS)
        println("Remaining Delay: $remainingDelay ms")
    }

    @Test
    fun `scheduleAtFixedRate 주기적인 실행`() {
        val executor = Executors.newScheduledThreadPool(1)
        val task = Runnable { println("Scheduling: " + System.nanoTime()) }
        val initialDelay = 0
        val period = 1
        executor.scheduleAtFixedRate(task, initialDelay.toLong(), period.toLong(), TimeUnit.SECONDS)
    }

    @Test
    fun `scheduleWithFixedDelay 주기적인 실행`() {
        val executor = Executors.newScheduledThreadPool(1)

        val task = Runnable {
            try {
                TimeUnit.SECONDS.sleep(2)
                println("Scheduling: " + System.nanoTime())
            } catch (e: InterruptedException) {
                System.err.println("task interrupted")
            }
        }
        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS)
    }
}