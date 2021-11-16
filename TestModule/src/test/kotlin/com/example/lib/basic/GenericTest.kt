package com.example.lib.basic

import org.junit.jupiter.api.Test

//kotlin
// in 반공변:Write-Only
// out 공변:Read-Only
class GenericTest {

    open class Fruit
    class Apple : Fruit()
    class Banana : Fruit()
    
    @Test
    fun `배열로 테스트합니다`(){
        val fruits: Array<Apple> = arrayOf(Apple())
        //receiveFruits(fruits)// 제네릭에 대한 타입불변성으로 인한 컴파일 에러
    }

    fun receiveFruits(fruits: Array<Fruit>) {
        println("Number of fruits: ${fruits.size}")
    }

    @Test
    fun `리스트로 테스트`(){
        val fruits: List<Apple> = listOf(Apple())   //List<out E> 공변타입 read 허용
        receiveFruits2(fruits)
    }

    fun receiveFruits2(fruits: List<Fruit>) {
        println("Number of fruits: ${fruits.size}")
    }
}