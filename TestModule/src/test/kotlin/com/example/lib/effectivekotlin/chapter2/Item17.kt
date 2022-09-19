package com.example.lib.effectivekotlin.chapter2

import org.junit.jupiter.api.Test

class Item17 {

    @Test
    fun `코드에서 아규먼트의 의미가 명확하지 않은 경우`() {
        val text1 = (1..10).joinToString("|")

        //이름 있는 파라미터를 사용하면 좀 더 신뢰 할 수 있지만, 실제로 코드에서 제대로 사용되고 있는지는 알 수 없다.
        val separator = "|"
        val text2 = (1..10).joinToString(separator)
    }

    @Test
    fun `이름 있는 아규먼트를 사용하라`() {
        val separator = "|"
        val text3 = (1..10).joinToString(separator = separator)

        // [얻을 수 있는 장점]
        // 이름을 기반으로 값이 무엇을 나타내는지 알 수 있다.(가독성)
        // 입력 순서와 상관 없으므로 안전하다.

        // [이름 있는 아규먼트를 사용하기 좋은 예시]
        // 디폴트 아규먼트의 경우
        // 같은 타입의 파라미터가 많은 경우
        // 함수 타입의 파라미터가 있는 경우(마지막 경우 제외)
    }

}