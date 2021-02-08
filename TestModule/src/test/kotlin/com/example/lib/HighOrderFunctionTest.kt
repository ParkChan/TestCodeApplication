package com.example.lib

import org.junit.Test


class HighOrderFunctionTest {

    private fun print(body: (String) -> String) {
        println(body("이걸 더해서"))
    }

    @Test
    fun `리턴값을 확인`() {
        print { "$it 리턴되는 값을 정의" }
    }
}