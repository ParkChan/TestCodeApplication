package com.example.lib.basic

import org.junit.jupiter.api.Test

class HighOrderFunction {

    fun performRequest(
        url: String,
        callback: (code: Int, content: String) -> Unit
    ) {
        /* .. */
    }

    @Test
    fun `콜백을 받아 처리하는 간단한 예제`() {
        val url = "http://www.naver.com"
        performRequest(url) { code, content -> /*..*/ } // api 에서 정의한 파라미터 명을 사용
        performRequest(url) { code, page -> /*..*/ }    // 원하는 이름으로 변경해서 사용 가능
    }

    fun twoAndThree(operation: (Int, Int) -> Int) {
        val result = operation(2, 3)
        println("The Result is $result")
    }

    var sum = fun(a: Int, b: Int): Int = a + b
    var sum2 = { a: Int, b: Int -> a + b }

    @Test
    fun `operation 함수를 인자로 받아 해당 함수를 실행하는 간단한 예제`() {
        twoAndThree { a, b -> a + b }
        twoAndThree { a, b -> a * b }
        twoAndThree(sum)
    }

    @Test
    fun `함수참조 테스트`(){
        fun printNext(text: String): String {
            return text.plus("문자열 합치기 입니다")
        }
        fun performRequest(
            callback: (content: String) -> String
        ) {
            val result = callback("결과는")
            println(result)
        }
        performRequest(::printNext)
    }
}