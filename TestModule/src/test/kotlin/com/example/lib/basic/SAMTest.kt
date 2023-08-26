package com.example.lib.basic

import org.junit.jupiter.api.Test

class SAMTest {

    // kotlin인 경우 interface 를 함수형으로 선언
    fun interface TestInterface{
        fun test()
    }
    @Test
    fun `single abstract method test`() {
        val test = TestInterface{ }
    }
}