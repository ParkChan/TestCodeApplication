package com.example.lib.coroutine.test

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

//참고 : https://thdev.tech/kotlin/2020/12/29/kotlin_effective_17/
class CoroutineCancelTest {

    @Test
    fun `취소가 되지않는 예시`() = runBlocking{
        val job = SupervisorJob()
        CoroutineScope(Dispatchers.IO + job).launch {
            suspendLoop2()
        }
        delay(50)
        job.cancel()
        delay(500)
        println("Job canceled")
    }

    @Test
    fun `취소가 잘 동작하는 예시`() = runBlocking{
        //먼저 이 코드의 while 문을 별도의 suspend 함수로 변경해보자.
        //그럼 종료할 수 있도록 수정을 해야 하는데 job을 아래와 같이 추가하면 자연스럽게 종료한다.
        val job = SupervisorJob()
        CoroutineScope(Dispatchers.IO + job).launch {
            suspendLoop()
        }
        delay(50)
        job.cancel()
        delay(500)
        println("Job canceled")
    }
    private suspend fun suspendLoop() = withContext(Dispatchers.IO) {
        while (isActive) {
            println("Job active")
        }
    }

    //단순히 무한 루프를 동작 시켰다면 동작하지 않는다.
    private suspend fun suspendLoop2() = withContext(Dispatchers.IO) {
        while (true) {
            println("Job active")
        }
    }
}