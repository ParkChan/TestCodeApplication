package com.example.lib.basic

import org.junit.jupiter.api.Test

//kotlin
//코드를 작성하다 보면 다양한 타입에 동일한 로직을 적용하기 위해 코드 재사용을 과도하게 하려는 경우가 있는데요.
// 이를 테면 파라미터를 전부 Any 로 받는다거나.. 등 이런 경우에는 타입 안정성을 저하시킬 수가 있습니다.
// 제네릭은 이런 이슈에 적절한 균형을 맞춰줍니다. 제네릭을 사용하면 다양한 타입에서 사용 가능한 코드를 작성할 수 있습니다!

// 제네릭 타입 불변성에의해 타입이 정확히 일치해야지만 하지만 타입프로젝션(공변,반공변)을 통해 약간의 제약을 푸는 것
// <T> 무공변 : 오로지 자기 타입만 허용하는 것
// in 반공변:Write-Only : 자바에서는 super //추상적인 방향으로의 타입 변환을 허용하는 것
// out 공변:Read-Only : 자바에서는 extends //구체적인 방향으로 타입 변환을 허용하는 것 (자기 자신과 자식 객체만 허용)
/**
 * 참조 : https://readystory.tistory.com/201
 * 참조 : https://medium.com/@joongwon/java-java%EC%9D%98-generics-604b562530b3
 */
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
        val fruits: List<Apple> = listOf(Apple())   //List<out E> 공변타입으로 read 허용
        receiveFruits2(fruits)
    }

    fun receiveFruits2(fruits: List<Fruit>) {
        println("Number of fruits: ${fruits.size}")
    }
}