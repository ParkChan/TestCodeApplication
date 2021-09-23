package com.example.lib.coroutine.channel

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.debug.junit5.CoroutinesTimeout
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

@CoroutinesTimeout(100)
class ChannelTest {


    @Test
    fun `채널의 유형에 따른 버퍼 크기 지정`(){
        val rendezvousChannel = Channel<String>()           //버퍼가 없는 채널(일반적
        val bufferedChannel = Channel<String>(10)   //버퍼 크기를 지정 하는 채널
        val conflatedChannel = Channel<String>(CONFLATED)   //이전에 전송된 요소를 덮어 쓰는 채널
        val unlimitedChannel = Channel<String>(UNLIMITED)   //무제한 채널
    }

    @Test
    fun `Rendezvous 채널 생성을 통한 send 및 receive 테스트`() = runBlocking{
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

    fun log(message: Any?) {
        println("[${Thread.currentThread().name}] $message")
    }

}