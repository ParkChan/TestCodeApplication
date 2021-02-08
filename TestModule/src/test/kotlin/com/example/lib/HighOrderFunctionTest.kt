package com.example.lib

import org.junit.Test


class HighOrderFunctionTest {

    private fun print(body: (String) -> String) {
        println(body("이걸 더해서"))
    }

    @Test
    fun `고차함수 기본 테스트`() {
        print { "$it 리턴되는 값을 정의" }
    }
}