package com.example.lib.collection.basic.map

import org.junit.jupiter.api.Test
import java.util.*

class TreeMapTest {

    @Test
    fun `TreeMap 기본 오름차순으로 출력 합니다`() {
        val treeMap = TreeMap<Int, String>()
        treeMap[1] = "하나"
        treeMap[3] = "셋"
        treeMap[2] = "둘"

        treeMap.forEach {
            println("${it.key}  ${it.value}")
        }
    }
    
    @Test
    fun `TreeMap Comparator를 사용하여 내림차순으로 정렬 합니다`() {
        //SortedMap을 구현한 TreeMap
        val treeMap = Collections.synchronizedMap(TreeMap<Int, String>(DescNumber()))   //동기화 지원
        treeMap[1] = "하나"
        treeMap[3] = "셋"
        treeMap[2] = "둘"

        treeMap.forEach {
            println("${it.key}  ${it.value}")
        }
    }
    private class DescNumber : Comparator<Int>{
        override fun compare(o1: Int, o2: Int): Int {
            return o2.compareTo(o1)
        }
    }

    @Test
    fun `TreeMap funtion을 사용하여 내림차순으로 정렬 합니다`() {
        val treeMap = TreeMap<Int, String>()
        treeMap[1] = "하나"
        treeMap[3] = "셋"
        treeMap[2] = "둘"

        val descMap = treeMap.descendingMap()

        descMap.forEach {
            println("${it.key}  ${it.value}")
        }
    }
}