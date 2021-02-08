package com.example.lib.collection

import org.junit.Test

class CollectionTest {

    private val testList = listOf("A", "B", "C")
    private val newList1 = testList.map { "$it!" }

    private val newList2 = testList.flatMap {
        "$it!".toList()
    }

    @Test
    fun `테스트`() {
        println(newList1)
        println(newList2)
    }

    var data = arrayOf(arrayOf("1", "2"), arrayOf("3", "4"))

    @Test
    fun `flatMap테스트`() {
        println(data.flatMap { x -> x.toList() })
    }

    private val animals = listOf("사자", "호랑이", "코끼리")

    @Test
    fun `동물 테스트 map`() {
        animals.map { animal -> "동물의 이름은? = $animal" }.forEach {
            println(it)
        }
    }

    @Test
    fun `동물 테스트 groupBy`() {
        animals.groupBy { animals -> if (animals.length > 2) "One" else "Two" }
                .forEach { (key, animals) ->
                    println("$key animals = $animals")
                }
    }
}