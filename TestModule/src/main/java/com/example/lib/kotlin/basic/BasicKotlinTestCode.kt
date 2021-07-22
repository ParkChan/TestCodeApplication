package com.example.lib.kotlin.basic

fun main() {
    data class Person(val age: Int, val name: String?)
    println("===========")
    println("Hello World")
    println("===========")
    val data1 = Person(10, "A")
    val data2 = Person(15, null)

    val result1 = data1.name
    val result2 = data2.name

    data2.name?.also {
        println("null check ~~~~~~~~~ $it.name")
    }

    println("result1 $result1")
    println("result2 $result2")

}
fun concatTest() {

}