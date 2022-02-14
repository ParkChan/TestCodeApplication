package com.example.lib.coroutine.test

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test

class CoroutineCancelTest {

    @Test
    fun `코루틴 취소 테스트`() = runBlocking{
        val job = SupervisorJob()
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                println("Job active")
                delay(1)
            }
        }

        delay(50)
        job.cancel()
        delay(500)
        //이 코드 내부에 있는 CoroutineScope은 종료하지 않는다.
//        while은 쉬지 않고 돌아간다.
//        내부의 CoroutineScope에 대한 종료를 호출하는 코드가 없기 때문에 종료하지 못한다.
    }

    @Test
    fun `코루틴 취소 테스트2`() = runBlocking{
        //먼저 이 코드의 while 문을 별도의 suspend 함수로 변경해보자.
        //그럼 종료할 수 있도록 수정을 해야 하는데 job을 아래와 같이 추가하면 자연스럽게 종료한다.
        val job = SupervisorJob()
        CoroutineScope(Dispatchers.IO + job).launch {
            suspendLoop()
        }
        delay(50)
        job.cancel()
        delay(500)
    }
    private suspend fun suspendLoop() = withContext(Dispatchers.IO) {
        while (isActive) {
            println("Job active")
            delay(1)
        }
    }

    //단순히 무한 루프를 동작 시켰다면 동작하지 않는다.
    private suspend fun suspendLoop2() = withContext(Dispatchers.IO) {
        while (true) {
            println("Job active")
            delay(1)
        }
    }

}