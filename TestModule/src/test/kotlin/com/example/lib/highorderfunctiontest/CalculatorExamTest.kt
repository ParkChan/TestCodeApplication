package com.example.lib.highorderfunctiontest

import org.junit.jupiter.api.Test


class CalculatorExamTest {

    private fun showResult(body: (Int, Int) -> Int) {
        println(body(1, 2))
    }

    @Test
    fun test() {
        showResult(::sum)
        showResult(::minus)
        showResult(::multiply)
        showResult(::division)
    }
}

fun sum(a: Int, b: Int) = a + b
fun minus(a: Int, b: Int) = a - b
fun multiply(a: Int, b: Int) = a * b
fun division(a: Int, b: Int) = a / b

