package com.example.lib.collection

import org.junit.Test

/**
 * https://namget.tistory.com/entry/Kotlin-%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%8A%A4%ED%8A%B8%EB%A6%BC-%ED%95%A8%EC%88%98-map-flatMap-groupBy-filter-take-drop-first-distinct-zip-joinToString-count-any-none-max-min-average
 */
class CollectionTest {

    private val testList = listOf("A", "B", "C")

    private val newList1 = testList.map { "$it!" }
    private val newList2 = testList.flatMap {
        "$it!".toList()
    }

    @Test
    fun `map 그리고 flatMap 테스트`() {
        println(newList1)
        println(newList2)
    }

    var data = arrayOf(arrayOf("1", "2"), arrayOf("3", "4"))

    @Test
    fun `flatMap Test1`() {
        println(data.flatMap { x -> x.toList() })
    }

    @Test
    fun `flatMap Test2`() {
        //하나의 인자에서 여러개의 인자로 매핑이 필요할때 사용
        val numbers = 0..5
        numbers.flatMap { number -> 1..number }.forEach { print("$it") }
    }

    private val animals = listOf("사자", "호랑이", "코끼리")

    @Test
    fun `map Test`() {
        animals.map { animal -> "동물의 이름은? = $animal" }.forEach {
            println(it)
        }
    }

    @Test
    fun `groupBy Test`() {
        animals.groupBy { animals -> if (animals.length > 2) "One" else "Two" }
            .forEach { (key, animals) ->
                println("$key animals = $animals")
            }
    }

    @Test
    fun `filter Test`() {
        animals.filter { animal -> animal.length > 2 }.forEach { println(it) }
    }

    @Test
    fun `take Test`() {
        animals.take(1).forEach { println(it) }
        animals.takeLast(1).forEach { println(it) }

        println("takeWhile===========")
        animals.takeWhile { animal -> animal.length > 2 }.forEach { println(it) }
        println("takeLastWhile===========")
        animals.takeLastWhile { animal -> animal.length > 2 }.forEach { println(it) }
    }
}