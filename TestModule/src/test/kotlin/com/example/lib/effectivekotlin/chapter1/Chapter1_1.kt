package com.example.lib.effectivekotlin.chapter1

import org.junit.jupiter.api.Test

/**
 * 에라토스테네스의 체 란?
 * 소수들을 대량으로 빠르고 정확하게 구하는 방법
 */
class Chapter1_1 {

    //에라토스테네스의 체 원리
    //에라토스테네스의 체는 가장 먼저 소수를 판별할 범위만큼 배열을 할당하여, 해당하는 값을 넣어주고, 이후에 하나씩 지워나가는 방법을 이용한다.
    //배열을 생성하여 초기화한다.
    //2부터 시작해서 특정 수의 배수에 해당하는 수를 모두 지운다.(지울 때 자기자신은 지우지 않고, 이미 지워진 수는 건너뛴다.)
    //2부터 시작하여 남아있는 수를 모두 출력한다.
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
    fun `시퀀스를 활용하는 예제로 확장 및 원하는 범위를 나중에 집어넣는 형태로 수정`() {
        val primes: Sequence<Int> = sequence {
            var numbers = generateSequence(2) { it + 1 }

            while (true) {
                val prime = numbers.first()
                yield(prime)
                numbers = numbers.drop(1).filter { it % prime != 0 }
            }
        }
        print(primes.take(10).toList())
    }

}