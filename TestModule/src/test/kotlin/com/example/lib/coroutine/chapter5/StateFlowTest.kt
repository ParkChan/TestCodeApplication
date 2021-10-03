package com.example.lib.coroutine.chapter5

import com.example.lib.coroutine.MainCoroutineRule
import kotlinx.coroutines.debug.junit5.CoroutinesTimeout
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

@CoroutinesTimeout(10_000)
class StateFlowTest {

    companion object {
        @JvmField
        @RegisterExtension
        val mainCoroutineRule = MainCoroutineRule()
    }

    private val userNameFlow = MutableStateFlow("")
    private val userName: StateFlow<String> = userNameFlow

    @Test
    fun testStateFlow() = runBlocking {
        userName.collect { userName -> println("Receive $userName") }
        delay(1000)
        userNameFlow.value = "안녕하세요."
        delay(1000)
        userNameFlow.value = "반갑습니다."
    }

}