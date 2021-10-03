package com.example.lib.coroutine.chapter5

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.debug.junit5.CoroutinesTimeout
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

/**
 * (참조)https://play.kotlinlang.org/hands-on/Introduction%20to%20Coroutines%20and%20Channels/08_Channels
 * (참조)[https://bb-library.tistory.com/248]
 * (참조:태환)[https://thdev.tech/kotlin/2021/01/09/Coroutines-Callback/]
 */
@CoroutinesTimeout(10000)
class ChannelTest {

    @Test
    fun `채널의 유형에 따른 버퍼 크기 지정`() {
        val rendezvousChannel = Channel<String>()           //버퍼가 크기가 없는 채널(일반적
        val bufferedChannel = Channel<String>(10)   //버퍼 크기를 지정 하는 채널
        val conflatedChannel = Channel<String>(CONFLATED)   //이전에 전송된 요소를 덮어 쓰는 채널
        val unlimitedChannel = Channel<String>(UNLIMITED)   //무제한 채널
    }

    @Test
    fun `Channel의 capacity의 기본값인 RENDEZVOUS 채널 send 및 receive 테스트`() = runBlocking<Unit> {
        val channel = Channel<String>()
        launch {
            channel.send("A1")
            channel.send("A2")
            log("A done")
        }
        launch {
            channel.send("B1")
            log("B done")
        }
        launch {
            repeat(3) {
                val x = channel.receive()
                log(x)
            }
        }
    }

    private fun log(message: Any?) {
        println("[${Thread.currentThread().name}] $message")
    }

    @Test
    fun testChannel() = runBlocking {
        val channel = Channel<Int>()
//        val channel = Channel<Int>(capacity = 10)
//        val channel = Channel<Int>(CONFLATED)
        launch {
            (0..10).forEach {
                println("send $it")
                channel.send(it)
            }
        }
        repeat(11) {
            println("receive ${channel.receive()}")
        }
    }

}