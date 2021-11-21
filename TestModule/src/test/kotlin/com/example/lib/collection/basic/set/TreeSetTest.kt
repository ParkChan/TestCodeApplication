package com.example.lib.collection.basic.set

import org.junit.jupiter.api.Test
import java.util.*

class TreeSetTest {

    @Test
    fun `TreeSet 예제`() {
        //SortedSet을 구현한 TreeMap
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
        val sortTree = TreeSet(DescNumber())
        sortTree.addAll(treeSet)

        val array = sortTree.toArray()
        print("배열로 출력하기 >> ")
        for (i in array) {
            print("$i ")
        }
    }
    private class DescNumber : Comparator<Int>{
        override fun compare(o1: Int, o2: Int): Int {
            return o2.compareTo(o1)
        }
    }
}
