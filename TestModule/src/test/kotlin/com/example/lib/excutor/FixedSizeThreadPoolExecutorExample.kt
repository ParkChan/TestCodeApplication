package com.example.lib.excutor

import org.junit.jupiter.api.Test
import java.time.LocalTime
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

object FixedSizeThreadPoolExecutorExample {

    @Test
    fun fixedSizeThreadPoolExecutorExample() {
        val executor = Executors.newFixedThreadPool(4) as ThreadPoolExecutor
        for (i in 0..9) {
            println(LocalTime.now().toString() + " Execute task " + i)

            // ThreadPoolExecutor에 Task를 예약하면, 여유가 있을 때 Task를 수행합니다.
            executor.execute {
                println(LocalTime.now().toString() + " Doing job " + i)
                println(LocalTime.now().toString() + " Done job " + i)
            }
        }

        // 더이상 ThreadPoolExecutor에 Task를 추가할 수 없습니다.
        // 작업이 모두 완료되면 쓰레드풀을 종료시킵니다.
        executor.shutdown()
    }
}