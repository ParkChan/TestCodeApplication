package com.example.lib.effectivekotlin.chapter3

import org.junit.jupiter.api.Test
import java.util.*

/**
 * 제네릭 타입과 variance 한정자를 활용하라
 */
class Item24 {

    // 파라미터 타입 T는 variance 한정자(in 또는 out)가 없으므로 기본적으로 invariant(불공변성) 이다.
    class Cup<T>

    @Test
    fun `out 은 타입 파라미터를 covariant(공변성)로 만듭니다`() {
        class Cup<out T>
        open class Dog
        class Puppy : Dog()

        val b: Cup<Dog> = Cup<Puppy>()          // OK
//        val a: Cup<Puppy> = Cup<Dog>()          // 오류
        val anys: Cup<Any> = Cup<Int>()         // OK
//        val nothings: Cup<Nothing> = Cup<Int>() // 오류
    }
    @Test
    fun `in 한정자는 타입 파라미터를 contravariant(반공변)으로 만듭니다`() {
        class Cup<in T>
        open class Dog
        class Puppy : Dog()

//        val b: Cup<Dog> = Cup<Puppy>()          // 오류
        val a: Cup<Puppy> = Cup<Dog>()          // OK
//        val anys: Cup<Any> = Cup<Int>()         // 오류
        val nothings: Cup<Nothing> = Cup<Int>() // OK
    }

    class Student(val name:String, val age:Int){
        override fun hashCode(): Int {
            return 11
        }

        override fun equals(other: Any?): Boolean {
            if(other is Student){
                return name == other.name
            }
            return super.equals(other)
        }
    }
}