package com.example.lib.collection.basic.map

import org.junit.jupiter.api.Test
import java.util.*

class HashTableTest {

    @Test
    fun `HashTable 을 테스트합니다`() {
        val hashTable = Hashtable<Int, String>()
        hashTable[1] = "하나"
        hashTable[3] = "셋"
        hashTable[2] = "둘"
//        hashTable[4] = null     //exception!!
//        hashTable[null] = "넷"  //exception!!

        hashTable.forEach {
            println("${it.key}  ${it.value}")
        }
    }
}