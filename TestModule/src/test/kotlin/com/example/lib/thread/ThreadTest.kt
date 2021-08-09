package com.example.lib.thread

import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

class ThreadTest {

    @Test
    fun `스레드 테스트`() {
        val task = Runnable {
            try {
                val threadName = Thread.currentThread().name
                println("Start $threadName")
                TimeUnit.SECONDS.sleep(1)
                println("End $threadName")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val thread = Thread(task)
        thread.start()
    }
}