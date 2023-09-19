package com.example.lib.basic

import org.junit.jupiter.api.Test

/**
 * fun <T> function(argument: T)
 * Generics 코드를 컴파일할 때 컴파일러는 T가 어떤 타입인지 알고 있습니다.
 * 하지만 Compile하면서 타입 정보를 제거하여 Runtime에는 T가 어떤 타입인지 모릅니다.
 * 그냥 T로 정해진 객체가 존재할 뿐이죠.
 *
 * Reified 키워드를 사용하면 Generics function에서 Runtime에 타입 정보를 알 수 있습니다.
 * 하지만 inline function과 함께 사용할 때만 가능합니다.
 *
 */
class ReifiedTest {
    @Test
    fun `타입이 String으로 고정된 경우`() {
        fun printString(value: String) {
            when (value::class) {
                String::class -> {
                    println("===============")
                    println("String : $value")
                }
            }
        }

        printString("print string function")
    }
    @Test
    fun `위의 코드를 Generics로 만들어보기 시도`() {
//        fun <T> printGenerics(value: T) {
//            when (value::class) {  // 컴파일에러! Runtime에 타입 정보가 지워지기 때문
//                String::class.java -> {
//                    println("String : $value")
//                }
//                Int::class.java -> {
//                    println("Integer : $value")
//                }
//            }
//        }
    }
    @Test
    fun `타입정보가 있는 객체로 전달하여 해결하기`() {
        fun <T> printGenerics(value: T, classType: Class<T>) {
            when (classType) {
                String::class.java -> {
                    println("String : $value")
                }
                Int::class.java -> {
                    println("Int : $value")
                }
            }
        }

        printGenerics("print generics function", String::class.java)
        printGenerics(1000, Int::class.java)
    }

    private inline fun <reified T> printGenerics(value: T) {
        when (T::class) {
            String::class -> {
                println("String : $value")
            }
            Int::class -> {
                println("Int : $value")
            }
        }
    }
    @Test
    fun `Reified 키워드를 사용하여 타입 정보 얻기`() {
        printGenerics("print generics function")
        printGenerics(1000)
    }

    private inline fun<reified T> getMessage(number: T): T {
        return when (T::class) {
            String::class -> "The number is : $number" as T
            Int::class -> number
            else -> "Not string, Not Integer" as T
        }
    }
    @Test
    fun `reified를 사용하여 리턴 타입이 다른 함수를 오버로딩`() {
        // compile error!
        // 자바는 이름과 인자가 동일하면 오버로딩이 안됨
//        fun getMessage(number: Int): String {
//            return "The number is : $number"
//        }
//
//        fun getMessage(number: Int): Int {
//            return number
//        }
        //reified를 사용하면 Generics function으로 위에서 의도한 내용을 구현 가능

        val result : Int = getMessage(10)
        println("result: $result")

        val resultString : String = getMessage("100")
        println("result: $resultString")
    }

}