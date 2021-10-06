package com.example.lib.exam

import org.junit.jupiter.api.Test

class ExamTest {
    @Test
    fun main(){
        val data = intArrayOf(1, 3, 6, 4, 1, 2).toList()

        printData(data)

        val tempData = sortData(data).distinct()
        printData(tempData)


        solution(data.toIntArray())
    }

    fun solution(data: IntArray): Int {
        return 0
    }

    fun printData(data: List<Int>){
        data.forEach {
            print(it.toString().plus(" "))
        }
        println("")
    }

    fun sortData(data: List<Int>): List<Int> {
        return data.sorted()
    }

}