package com.example.lib.effectivekotlin.chapter1

import org.junit.jupiter.api.Test

class Chapter1_ETC {

    @Test
    fun `generateSequence 테스트1`() {
        val oddNumbers = generateSequence(1) { it + 2 } // `it` is the previous element
        println(oddNumbers.take(5).toList()) // [1, 3, 5, 7, 9]
        //println(oddNumbers.count())     // error: the sequence is infinite
    }

    @Test
    fun `generateSequence 테스트2`() {
        val oddNumbersLessThan10 = generateSequence(1) { if (it < 10) it + 2 else null }
        //[1, 3, 5, 7, 9, 11]
        println(oddNumbersLessThan10.count()) // 6
    }

    @Test
    fun `generateSequence 테스트3`() {
        // generateSequence()의 사용
        val seq = sequence {
            val start = 0
            // 단일 값 산출
            yield(start)
            // 반복 값 산출
            yieldAll(1..5 step 2)
            // 무한한 시퀀스에서 산출
            yieldAll(generateSequence(8) { it * 3 })
        }

        println(seq.take(7).toList()) // [0, 1, 3, 5, 8, 24, 72]
    }

    @Test
    fun `drop test`() {
        val data = (0..10).drop(1)
        println(data)       // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }
}