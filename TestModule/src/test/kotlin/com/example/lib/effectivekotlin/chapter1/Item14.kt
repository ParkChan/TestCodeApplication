package com.example.lib.effectivekotlin.chapter1

import org.junit.jupiter.api.Test

class Item14 {

    @Test
    fun `코틀린이 제공하는 타입 추론`() {
        val num = 10
        val name = "Marcin"
        val ids = listOf(12, 112, 554, 997)
    }

    @Test
    fun `유형이 명확하지 않을 떄는 남용하면 좋지 않음`() {
        //val data = getSomeData()
    }

    @Test
    fun `최대한 플랫폼 타입을 사용하지 말라`() {
        //val data: UserData = getSomeData()
    }
}