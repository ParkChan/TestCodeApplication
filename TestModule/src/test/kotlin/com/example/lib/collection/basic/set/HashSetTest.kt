package com.example.lib.collection.basic.set

import org.junit.jupiter.api.Test

class HashSetTest {

    @Test
    fun `HashSet 예제`() {
        val testData = hashSetOf("a", "b", "b", "c")
        //중복이 제거됨 a b c 출력
        println("최초 값 출력 $testData")

        println(testData.elementAt(2))  //중복제거 후 c 가 출력됨
        println("element 2번 항목 출력 ${testData.elementAt(2)}")

        testData.add("d")
        testData.add("e")
        testData.add("f")

        println("d e f 를 추가한 후 출력 $testData")

        testData.remove("b")
        println("b 를 삭제한 후 출력 $testData")
        println("b 가 포함되어 있는가? ${testData.contains("b")}")
        println("사이즈 출력 ${testData.size}")
//        testData.clear()
//        println("clear 후 출력 $testData")

        val array = testData.toArray()
        val iterator = array.iterator()
        println("array로 변환 후 출력")
        while (iterator.hasNext()) {
            println(iterator.next())
        }
    }
}
