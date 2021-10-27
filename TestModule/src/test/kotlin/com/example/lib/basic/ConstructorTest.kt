package com.example.lib.basic

import org.junit.jupiter.api.Test

/**
 * 1. 호출한 생성자의 arguments에 대해 수행
 * 2. 만약 해당 생성자에서 다른 생성자를 호출하고 있다면, 호출된 생성자의 arguments가 초기화됨.
 * 3. 상단에서부터 차례대로 property와 init 블록들이 초기화
 * 4. 생성자 블럭 내부의 코드들이 수행됩니다.
 * 출처: https://tourspace.tistory.com/122 [투덜이의 리얼 블로그]
 */
class TestConstructor {

    @Test
    fun testConstructor() {
        //위 코드를 Child(1)을 넣어서 수행하면 가장먼저 secondary constructor의 arguments부터 초기화가 진행됩니다.
        Child(1)
    }

}

open class Parent {
    private val a = println("Parent.a - #4")

    constructor (arg: Unit = println("Parent primary constructor default argument - #3")) {
        println("Parent primary constructor - #7")
    }

    init {
        println("Parent.init - #5")
    }

    private val b = println("Parent.b - #6")
}

class Child : Parent {
    val a = println("Child.a - #8")

    init {
        println("Child.init 1 - #9")
    }

    constructor (arg: Unit = println("Child primary constructor default argument - #2")) : super() {
        println("Child primary constructor - #12")
    }

    val b = println("Child.b - #10")

    constructor (
        arg: Int,
        arg2: Unit = println("Child secondary constructor default argument - #1")
    ) : this() {
        println("Child secondary constructor - #13")
    }

    init {
        println("Child.init 2 - #11")
    }
}

