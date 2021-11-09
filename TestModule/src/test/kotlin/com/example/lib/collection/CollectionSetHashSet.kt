package com.example.lib.collection

import org.junit.jupiter.api.Test
import java.util.*

class CollectionSetHashSet {

    @Test
    fun `Hashset 예제`() {
        val testData = hashSetOf<String>("a", "b", "b", "c")
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

    @Test
    fun `TreeSet 예제`() {
        //Collection을 인자로 받음
        val treeSet: TreeSet<Int> = TreeSet(arrayListOf(2, 3, 2, 1, 4, 1))
        treeSet.add(5)

        val iterator = treeSet.iterator()
        //오름차순 정렬 및 중복제거
        while (iterator.hasNext()) {
            print("${iterator.next()} ")
        }
        println("")
        //지정된 객체보다 작은값의 객체들을 반환
        println("headSet ${treeSet.headSet(1)} ")
        //지정된 객체보다 큰값의 객체들을 반환
        println("tailSet ${treeSet.tailSet(4)} ")
        //범위 검색 사이의 결과를 반환(끝 범위인 toElement는 포함되지 않음
        println("subSet ${treeSet.subSet(1, 5)} ")


        val desc = treeSet.descendingSet()
        val descIterator = desc.iterator()
        println("descendingSet")
        while (descIterator.hasNext()) {
            print("${descIterator.next()} ")
        }
        println("")

        println("사용자 정의 Comparator를 사용")
        val sortTree = TreeSet(MyOderNumber())
        sortTree.addAll(treeSet)

        val array = sortTree.toArray()
        print("배열로 출력하기 >> ")
        for (i in array) {
            print("$i ")
        }
    }
    private class MyOderNumber : Comparator<Int>{
        override fun compare(o1: Int, o2: Int): Int {
            return o2.compareTo(o1)
        }
    }
}
