package com.example.lib.basic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class HashCodeTest {

    @Test
    fun 해시코드_재정의() {
        val map: HashMap<ExtendedPhoneNumber, String> = HashMap()
        map[ExtendedPhoneNumber(707, 867, 5307)] = "제니"
        println("Instance 1 hashcode : " + ExtendedPhoneNumber(707, 867, 5307).hashCode())
        println("Instance 2 hashcode : " + ExtendedPhoneNumber(707, 867, 5307).hashCode())
        println("get map : ${map[ExtendedPhoneNumber(707, 867, 5307)]}")
        assertEquals(map[ExtendedPhoneNumber(707, 867, 5307)], "제니")
    }

    @Test
    fun 해시코드_재정의안함() {
        val map: HashMap<PhoneNumber, String> = HashMap()
        map[PhoneNumber(707, 867, 5307)] = "제니"
        println("Instance 1 hashcode : " + PhoneNumber(707, 867, 5307).hashCode())
        println("Instance 2 hashcode : " + PhoneNumber(707, 867, 5307).hashCode())
        println("get map : ${map[PhoneNumber(707, 867, 5307)]}")
        assertNotEquals(map[PhoneNumber(707, 867, 5307)], "제니")
    }

    open class PhoneNumber(
        val firstNumber: Int,
        val secondNumber: Int,
        val thirdNumber: Int
    ) {
        override fun equals(o: Any?): Boolean {
            if (o !is PhoneNumber) {
                return false
            }
            val p = o
            return firstNumber == p.firstNumber && secondNumber == p.secondNumber && thirdNumber == p.thirdNumber
        }
    }

    class ExtendedPhoneNumber(firstNumber: Int, secondNumber: Int, thirdNumber: Int) :
        PhoneNumber(firstNumber, secondNumber, thirdNumber) {
        override fun hashCode(): Int {
            val c = 31
            var hashcode = Integer.hashCode(firstNumber)
            hashcode = c * hashcode + Integer.hashCode(secondNumber)
            hashcode = c * hashcode + Integer.hashCode(thirdNumber)
            return hashcode
        }
    }
}