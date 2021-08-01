package com.example.lib.kotlin.util

import com.example.lib.java.util.CustomLogger

fun main() {
    println("===========")
    val requestMap: Map<String, String> = mutableMapOf("id" to "user", "password" to "1234")
    val requestMap1: Map<String, String> = mapOf("id" to "user")

    CustomLogger.getInstance().info(mapToRequestString(requestMap))
    CustomLogger.getInstance().info(mapToRequestString(requestMap1))
}

fun mapToRequestStringBefore(map: Map<String, String?>): String {
    val mapToKeySize: Int
    val mapKeys = map.keys
    mapToKeySize = mapKeys.size
    var appendCnt = 0
    val result = StringBuilder()

    if (mapToKeySize > 0) {
        result.append("?")
        for (mapKey in mapKeys) {
            appendCnt++
            result.append(mapKey)
            result.append("=")
            result.append(map[mapKey])
            if (mapToKeySize != 1
                && appendCnt < mapToKeySize
            ) {
                result.append("&")
            }
        }
    }
    return result.toString()
}

//조금 더 코틀린스럽게 코드를 변경해보자.
fun mapToRequestString(map: Map<String, String>): String {
    val result = StringBuilder()
    if (map.isNotEmpty()) {
        val list = map.toList()
        result.append("?")
        for ((index, item) in list.withIndex()) {
            result.append("${item.first}=${item.second}")
            if (index < list.size - 1) {
                result.append("&")
            }
        }
    }
    return result.toString()
}