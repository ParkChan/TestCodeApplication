package com.example.lib.thread.excutor

import org.junit.jupiter.api.Test
import java.time.LocalTime
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class FixedSizeThreadPoolExecutorTest {

    private val testNoneTime = 20L
    private val testErrorTime = 5L

    @Test
    fun fixedSizeThreadPoolExecutorTest() {
        val executor = Executors.newFixedThreadPool(getMaxPoolSize()) as ThreadPoolExecutor
        for (i in 0..9) {
            println(LocalTime.now().toString() + " Execute task " + i)
            executor.execute {
                println(LocalTime.now().toString() + " Doing job " + i)
                sleepSec(3)
                println(LocalTime.now().toString() + " Done job " + i)
            }
        }

        // 더이상 ThreadPoolExecutor에 Task를 추가할 수 없습니다.
        // 작업이 모두 완료되면 쓰레드풀을 종료시킵니다.
        executor.shutdown()

        // shutdown() 호출 전에 등록된 Task 중에 아직 완료되지 않은 Task가 있을 수 있습니다.
        // Timeout을 20초 설정하고 완료되기를 기다립니다.
        // 20초 전에 완료되면 true를 리턴하며, 20초가 지나도 완료되지 않으면 false를 리턴합니다.

        // shutdown() 호출 전에 등록된 Task 중에 아직 완료되지 않은 Task가 있을 수 있습니다.
        // Timeout을 20초 설정하고 완료되기를 기다립니다.
        // 20초 전에 완료되면 true를 리턴하며, 20초가 지나도 완료되지 않으면 false를 리턴합니다.
        if (executor.awaitTermination(testNoneTime, TimeUnit.SECONDS)) {
            println(LocalTime.now().toString() + " All jobs are terminated")
        } else {
            println(LocalTime.now().toString() + " some jobs are not terminated")

            // 모든 Task를 강제 종료합니다.
            executor.shutdownNow()
        }
    }

    private fun sleepSec(sec: Long) {
        try {
            TimeUnit.SECONDS.sleep(sec)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun getMaxPoolSize() : Int{
        println("Max thread pool size: ${Runtime.getRuntime().availableProcessors()}")
        return Runtime.getRuntime().availableProcessors()
    }
}