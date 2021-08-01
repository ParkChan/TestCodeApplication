package com.example.lib.highorderfunctiontest

import org.junit.jupiter.api.Test


class ArrayExamTest {

    private fun showResult(body: (Array<Int>, Int) -> List<Int>): List<Int> {
        val array = arrayOf(1, 2, 3, 4, 5)
        val increaseNumber = 1
        return body(array, increaseNumber)
    }

    @Test
    fun `배열에 있는 값에 지정된 값 더해주기`() {
        val result = showResult { ints: Array<Int>, i: Int ->
            ints.map {
                it.plus(i)
            }
        }
        println(result)

        val result2 = showResult { ints: Array<Int>, i: Int ->
            ints.map {
                it.plus(i)
            }
        }
        println(result2)

    }
}