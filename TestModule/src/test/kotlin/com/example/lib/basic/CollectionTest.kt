package com.example.lib.basic

import org.junit.jupiter.api.Test

class CollectionTest {

    private data class Person(val name: String, val age: Int)

    private val canBeInClub27 = { p: Person -> p.age <= 27 }

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
}