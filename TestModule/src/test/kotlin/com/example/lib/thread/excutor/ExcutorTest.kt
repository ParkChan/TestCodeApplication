package com.example.lib.thread.excutor

import org.junit.jupiter.api.Test
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ExcutorTest {

    @Test
    fun `executor 는 명시적으로 종료시켜야만 한다 그렇지 않으면 좋료되지 않고 다른작업을 기다린다`() {
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
            TimeUnit.SECONDS.sleep(10)
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
}