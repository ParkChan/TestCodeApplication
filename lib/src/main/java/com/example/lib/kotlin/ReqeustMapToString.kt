package com.example.lib.kotlin

import com.example.lib.java.util.CustomLogger

fun main() {
    println("===========")
    val requestMap = mapOf("id" to "user", "password" to "1234")
    val requestMap1 = mapOf("id" to "user")

    CustomLogger.getInstance().info(mapToRequestString(requestMap))
    CustomLogger.getInstance().info(mapToRequestString(requestMap1))
}

fun mapToRequestString(map: Map<String, String?>): String {
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