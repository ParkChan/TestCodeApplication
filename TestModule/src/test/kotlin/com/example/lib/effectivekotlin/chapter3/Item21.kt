package com.example.lib.effectivekotlin.chapter3

import org.junit.jupiter.api.Test
import kotlin.reflect.KProperty

class Item21 {

    var token: String? = null
        get() {
            println("token returned value $field")
            return field
        }
        set(value) {
            println("token returned value $value")
            field = value
        }
    var attempt: Int = 0
        get() {
            println("attempt returned value $field")
            return field
        }
        set(value) {
            println("attempt returned value $value")
            field = value
        }

    private class LoggingProperty<T>(var value: T) {
        operator fun getValue(
            thisRef: Any?,
            prop: KProperty<*>
        ): T {
            println("${prop.name} returned value $value")
            return value
        }

        operator fun setValue(
            thisRef: Any?,
            prop: KProperty<*>,
            newValue: T
        ) {
            val name = prop.name
            println("$name changed from $value to $newValue")
            value = newValue
        }
    }

    // 프로퍼티 위임
    var token2: String? by LoggingProperty(null)
    var attempt2: Int by LoggingProperty(0)

    @Test
    fun `일부 프로퍼티가 사용될 때, 간단한 로그를 출력하는 예시`() {

    }

    @Test
    fun `일반적인 프로퍼티 패턴은 프로퍼티 위음으로 만들어라`() {
    }
}