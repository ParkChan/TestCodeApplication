package com.example.lib.basic

import org.junit.jupiter.api.Test

class BasicDelegationTest {

    interface Vehicle {
        fun go(): String
    }

    class CarImpl(private val where: String) : Vehicle {
        override fun go() = "is going to $where"
    }

    class AirplaneImpl(private val where: String) : Vehicle {
        override fun go() = "is flying to $where"
    }

    class CarOrAirplane(
        private val model: String,
        impl: Vehicle
    ) : Vehicle by impl {
        fun tellMeYourTrip() {
            println("$model ${go()}")
        }
    }

    @Test
    fun `클래스 위임을 통한 모듈을 유연하게 구성하는 예제`() {
        val myAirbus330 = CarOrAirplane("Lamborghini", CarImpl("Seoul"))
        val myBoeing337 = CarOrAirplane("Boeing 337", AirplaneImpl("Seoul"))

        myAirbus330.tellMeYourTrip()
        myBoeing337.tellMeYourTrip()

    }

}