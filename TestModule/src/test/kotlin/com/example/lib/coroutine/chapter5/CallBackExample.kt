package com.example.lib.coroutine.chapter5

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.debug.junit5.CoroutinesTimeout
import org.junit.jupiter.api.Test

/**
 * (참조:태환)[https://thdev.tech/kotlin/2021/01/09/Coroutines-Callback/]
 */
@CoroutinesTimeout(10000)
class CallBackExample {

    @Test
    fun test() = runBlocking {
        val channel = Channel<String>()
        val callback = object : CallbackListener {
            override fun onCallback(value: String) {
                GlobalScope.launch {
//                runBlocking {
                    println("callback $value")
                    channel.send(value)
                }
            }
        }
        val end = launch {
            while (isActive) {
                println("------ receive !!!! ${channel.receive()}")
            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            (0..30).forEach {
                callback.onCallback("input one - $it")
                println("input one - $it")
            }
        }.join()

        end.cancel()
    }

    @Test
    fun test2() = runBlocking {
        val channel = Channel<String>()
        val end = launch {
            while (isActive) {
                println("------ receive !!!! ${channel.receive()}")
            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            (0..30).forEach {
                channel.send("input one - $it")
            }
        }.join()

        end.cancel()
    }
}

interface CallbackListener {

    fun onCallback(value: String)
}