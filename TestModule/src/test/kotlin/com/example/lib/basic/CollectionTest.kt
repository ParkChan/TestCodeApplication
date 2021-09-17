package com.example.lib.basic

import org.junit.jupiter.api.Test

class CollectionTest {

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
            listOf("a","b","c"),  
            listOf("가","나","다")
        )
        println(people.flatten())
    }
}