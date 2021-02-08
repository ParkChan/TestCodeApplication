package com.example.lib.collection

import org.junit.Test

class CollectionTest {

    private val testList = listOf("A", "B", "C")
    private val newList1 = testList.map { "$it!" }

    private val newList2 = testList.flatMap {
        println("test >>>  $it")
        println("test >>>  ${it.toList()}")
        "$it!".toList()
    }

    @Test
    fun `테스트`(){
        println(newList1)
        println(newList2)
    }
}