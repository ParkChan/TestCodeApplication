package com.example.lib.collection.basic.map

import org.junit.jupiter.api.Test

class LinkedHashMapTest {
    @Test
    fun `LinkedHashMap 을 테스트합니다`() {
        //내부 Double-Linked List 로 구성하여 순서를 유지
        val hashMap = LinkedHashMap<Int, String>()
        hashMap[3] = "셋"
        hashMap[1] = "하나"
        hashMap[2] = "둘"

        hashMap.forEach {
            println("${it.key}  ${it.value}")
        }
    }
}