package com.example.lib.highorderfunctiontest

import org.junit.jupiter.api.Test


class BasicExamTest {

    private fun highOrderFunction(func: () -> Unit) {
        func()
    }

    private fun highOrderFunction1(): () -> Unit {
        return { println("hello world") }
    }

    private fun highOrderFunction2() {
        println("hello world")
    }

    @Test
    fun `기본형 테스트`() {
        println("highOrderFunction1 과 highOrderFunction2 은 동일")
        highOrderFunction(highOrderFunction1())
        highOrderFunction(::highOrderFunction2)
    }


    private fun print1(body: (String) -> String) {
        println(body("이걸 더해서"))
    }

    @Test
    fun `하나의 String 변수를 받아 String 리턴`() {
        print1 { a -> "$a 리턴되는 값을 정의" }
    }

    private fun print2(body: (String, String) -> String) {
        println(body("이걸 더해서", "이것도 더해서"))
    }

    @Test
    fun `두개의 String 변수를 받아 String 리턴`() {
        print2 { a, b -> "$a $b 파라미터 2개 리턴되는 값을 정의" }
    }

    private fun print3(
        body1: (String) -> String,
        body2: (String) -> String,
        body3: (String) -> String
    ) {
        println(body1("1번째"))
        println(body2("2번째"))
        println(body3("3번째"))
    }

    @Test
    fun `하나의 변수를 받아 String을 리턴하는 고차 함수 3개 호출`() {
        print3({ a ->
            "$a 리턴되는 값"
        }, { b ->
            "$b 리턴되는 값"
        }, { c ->
            "$c 리턴되는 값"
        })
    }

    private fun sample(a: String, b: String) = "$a $b 리턴되는 값을 정의"

    @Test
    fun `다른 메서드를 참조`() {
        print2 { a, b ->
            sample(a, b)
        }
    }

    @Test
    fun `다른 메서드를 참조 다른방식으로 호출`() {
        print2(::sample)
    }

    private lateinit var higherOrder: (String) -> Unit

    @Test
    fun `lateinit 테스트`() {
        higherOrder = {
            println("$it print higherOrder")
        }
        higherOrder("print message")
    }

    val higherOrder2: () -> Unit = {
        println("Higher order functions")
    }

    @Test
    fun `변수로 생성`() {
        higherOrder2()
    }
}
