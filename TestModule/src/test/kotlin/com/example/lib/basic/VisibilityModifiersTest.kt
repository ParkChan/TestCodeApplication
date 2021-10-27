package com.example.lib.basic

import org.junit.jupiter.api.Test

class ProtectedTest {
    @Test
    fun `Kotlin의 접근 제한자(Visibility Modifiers)에 대해서 알아보기`() {
        val test = Subclass()
        // o.a, o.b 는 접근할 수 없습니다.
        // o.c, o.d 는 접근할 수 있습니다.
        // Outer.Nested와 Nested::e 는 접근할 수 없습니다.
//        test.a = 4
//        test.b = 5
        test.c = 6
        test.d = 7
    }
}

open class Outer {
    private val a = 1
    protected open val b = 2
    internal var c = 3
    var d = 4  // 기본으로 public이 설정됩니다

    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a 는 접근할 수 없습니다.
    // b, c, d 는 접근할 수 있습니다.
    // Nested, e 는 접근할 수 있습니다.
    override val b = 5   // 'b' 는 protected

    fun test(){
        c = 4
        d = 5
        val nested = Nested()
        nested.e
    }

}
