package com.example.lib.thread.excutor

import org.junit.jupiter.api.Test
import java.time.LocalTime
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class FutureTest {

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
}