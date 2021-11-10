package com.example.lib.collection.basic.map

import org.junit.jupiter.api.Test
import java.util.*

class HashMapTest {
    @Test
    fun `HashMap 을 테스트합니다`() {
        val hashMap = Collections.synchronizedMap(HashMap<Int, String>())   //동기화 지원
        hashMap[3] = "셋"
        hashMap[1] = "하나"
        hashMap[2] = "둘"

        hashMap.forEach {
            println("${it.key}  ${it.value}")
        }
    }
}