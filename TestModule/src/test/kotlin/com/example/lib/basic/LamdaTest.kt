package com.example.lib.basic

import org.junit.jupiter.api.Test

class LamdaTest {

    //Lambda expression은 람다식(람다표현식)이라고 번역되고, 간단히 람다라고도 합니다.
    // 쉽게 말하면 람다는 익명함수입니다.
    // 익명함수는 함수의 이름이 없는 함수를 말합니다.
    // 보통 한번 사용되고 재사용되지 않는 함수를 만들때 익명함수로 만듭니다.
    // 굳이 함수를 따로 생성하지 않고, 코드 중간에 익명함수를 만들 수 있거든요.
    // 이러면 코드 가독성이 높아지고, 함수형 프로그래밍에서 자주 사용하는 패턴입니다.

    @Test
    fun `익명함수 생성 및 실행`() {
        val greeting = fun() { println("hello") }
        greeting()
    }

    @Test
    fun `람다를 이용한 익명함수 정의`() {
        val greeting: () -> Unit = { println("hello") }
        greeting()
    }

    @Test
    fun `인자를 받고 값을 리턴하는 익명함수`() {
        val greeting = { name: String,
                         age: String ->
            "Hello. My name is $name. I'm $age year old"
        }
        val result = greeting("chan", "37")
        println(result)
    }

    @Test
    fun `인자 타입을 생략하는 익명함수`() {
        val greeting: (String, String) -> String =
            { name, age ->
                "Hello. My name is $name. I'm $age year old"
            }
        val result = greeting("chan", "37")
        println(result)
    }

    @Test
    fun `익명함수의 인자가 1개인 경우, name과 같은 인자 이름을 생략할 수 있습니다`() {
        val greeting: (String) -> String = { "Hello. My name is $it." }
        val result = greeting("chacha")
        println(result)
    }

    @Test
    fun `라이브러리에서 사용되는 익명함수`() {
        val numbers = listOf(5, 1, 3, 2, 9, 6, 7, 8, 4)
        println(numbers)

        val sorted = numbers.sortedBy { it }
        println(sorted)

        val biggerThan5 = numbers.sortedBy { it }.filter { it > 5 }
        println(biggerThan5)
    }

}