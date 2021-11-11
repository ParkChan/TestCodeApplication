package com.example.lib.collection.basic.list

import org.junit.jupiter.api.Test
import java.util.*

class ListTest {
    @Test
    fun `List 를 테스트합니다`() {
        val list = Collections.synchronizedList(listOf("하나", "둘", "셋"))   //동기화 지원
        list.forEach {
            println(it)
        }
    }
}