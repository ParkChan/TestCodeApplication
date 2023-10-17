package com.example.lib.collection

import org.junit.jupiter.api.Test

class CollectionBasicTest {

    private data class Person(val name: String, val age: Int)

    private val canBeInClub27 = { p: Person -> p.age <= 27 }

    @Test
    fun `Map Test`() {
        val people = listOf(
            Person("Alice", 29),
            Person("Bob", 31),
            Person("Charles", 31)
        )
        println(people.map { it.name })
    }

    @Test
    fun `Find Test`() {
        val people = listOf(
            Person("Alice", 27),
            Person("Bob", 31)
        )
        println(people.find(canBeInClub27))
    }

    @Test
    fun `GroupBy Test`() {
        val people = listOf(
            Person("Alice", 31),
            Person("Bob", 29),
            Person("Carol", 31)
        )
        println(people.groupBy { it.age })
    }

    @Test
    fun `collection의 element를 key로 사용하려면 associateWith()를 사용`(){
        val numbers = listOf("one", "two", "three", "four")
        println(numbers.associateWith { it.length })
        // output:
        // {one=3, two=3, three=5, four=4}
    }

    @Test
    fun `collection의 element를 value로 사용하려면 associateBy()를 사용`(){
        val numbers = listOf("one", "two", "three", "four")
        println(numbers.associateBy{ it.length })
        // output:
        // {one=3, two=3, three=5, four=4}
    }

    @Test
    fun `flatMap Test`() {
        val people = listOf(
            "abc",
            "edf",
            "ghi",
        )
        println(people.flatMap { it.toList() })
    }

    @Test
    fun `flatten Test`() {
        val people = listOf(
            listOf("a", "b", "c"),
            listOf("가", "나", "다")
        )
        println(people.flatten())
    }

    @Test
    fun `list add Test`() {
        val abc = listOf("a", "b", "c")
        val def = listOf("가", "나", "다")
        println(abc + def)
        println(abc.plus(def))
    }

    @Test
    fun `withIndex Test`() {
        val chars = listOf("a", "b", "c", "가", "나", "다")
        for ((index, char) in chars.withIndex()) {
            println("index : ${index.toString()}  value: $char")
        }
    }

    @Test
    fun `중복제거`() {
        val data = arrayOf(1, 3, 6, 4, 1, 2)
        println(data.distinct())

    }

    @Test
    fun `sort 메소드는 해당 Collection 의 원소 위치가 변경됩니다`() {
        val data = arrayOf(1, 3, 6, 4, 1, 2)
        data.sort()
        print("sort Data ${data.toList()}")
    }

    @Test
    fun `sorted 메소드를 사용하면 기존 Collection 은 변하지 않습니다`() {
        val data = arrayOf(1, 3, 6, 4, 1, 2)
        print("sortedData ${data.sorted()}")
        println()
        print("originData ${data.toList()}")
        println()
    }
}