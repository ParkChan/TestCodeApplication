package com.example.lib.effectivekotlin.chapter1

import org.junit.jupiter.api.Test

/**
 * 에라토스테네스의 체 란?
 * 소수들을 대량으로 빠르고 정확하게 구하는 방법
 */
class Chapter1_1 {

    //에라토스테네스의 체 알고리즘
    //1. 부터 까지의 자연수를 전부 나열한다.
    //2. 소수도, 합성수도 아닌 을 지운다.
    //3. 남아 있는 자연수 중 가장 작은 수인 는 소수다. 이제 의 배수들을 모두 지운다.
    //4. 남아 있는 자연수 중 가장 작은 수인 은 소수다. 이제 의 배수들을 모두 지운다.
    //5. 남아 있는 자연수 중 가장 작은 수는 소수다. 이 수의 배수들을 모두 지운다.
    //6. 남은 자연수 중 가장 작은 수가 의 제곱근을 넘을 때까지 과정 5를 반복하면, 남아 있는 수가 모두 소수다.
    @Test
    fun `간단한 구현`() {
        var numbers = (2..50).toList()
        val primes = mutableListOf<Int>()
        while (numbers.isNotEmpty()) {
            val prime = numbers.first()
            primes.add(prime)
            numbers = numbers.filter { it % prime != 0 }
        }
        print(primes)
    }

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
    fun `drop test`() {
        val data = (0..10).drop(1)
        println(data)       // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    }

    @Test
    fun `시퀀스를 활용하는 예제로 확장 및 원하는 범위를 나중에 집어넣는 형태로 수정`() {
        val primes: Sequence<Int> = sequence {
            var numbers = generateSequence(2) { it + 1 }
            while (true) {
                val prime = numbers.first()
                yield(prime)
                //println(">>> " + numbers.take(20).toList())
                numbers = numbers.drop(1).filter { it % prime != 0 }
            }
        }
        print(primes.take(10).toList())
        //[2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
    }

    @Test
    fun `시퀀스 예제에 대한 잘못된 최적화`() {
        val primes: Sequence<Int> = sequence {
            var numbers = generateSequence(2) { it + 1 }

            var prime: Int
            while (true) {
                prime = numbers.first()
                yield(prime)
                numbers = numbers.drop(1).filter { it % prime != 0 }
            }
        }
        print(primes.take(10).toList())
        // [2, 3, 5, 6, 7, 8, 9, 10, 11, 12]
    }
}