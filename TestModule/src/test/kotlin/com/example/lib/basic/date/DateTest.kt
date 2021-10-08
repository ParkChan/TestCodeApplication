package com.example.lib.basic.date

import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*

class DateTest {

    @Test
    fun dateTest(){
        val sdf = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        println(sdf.format(Date(0))) //1970-01-01-00:00:00
    }
}