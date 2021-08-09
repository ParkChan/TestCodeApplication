package com.example.lib.thread.excutor

import org.junit.jupiter.api.Test
import java.time.LocalTime
import java.util.concurrent.*

class BlockingQueueTest {

    @Test
    fun blockingQueueTest() {
        val executor = Executors.newFixedThreadPool(4) as ThreadPoolExecutor
        val queue: BlockingQueue<String> = ArrayBlockingQueue(10)

        for (i in 0..9) {
            val sleepSec = 10 - i
            println(LocalTime.now().toString() + " Execute task " + i)
            // Result를 BlockingQueue에 저장합니다.
            executor.submit {
                println(
                    LocalTime.now()
                        .toString() + " Doing Task " + i + ", sleepSec: " + sleepSec
                )
                try {
                    TimeUnit.SECONDS.sleep(sleepSec.toLong())
                    val result = "finished job $i"
                    queue.put(result)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

        // take()는 Queue에 아이템이 추가될 때까지 기다렸다가 리턴합니다.
        for (i in 0..9) {
            val result: String = queue.take()
            println(LocalTime.now().toString() + " " + result)
        }
        executor.shutdown()
    }
}