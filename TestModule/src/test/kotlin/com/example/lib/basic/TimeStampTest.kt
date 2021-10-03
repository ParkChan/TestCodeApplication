package com.example.lib.basic

import com.example.lib.java.util.CustomLogger
import org.junit.jupiter.api.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TimeStampTest {
    private fun getBeijingDatetime(): String? {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ")
        val tz = TimeZone.getTimeZone("Asia/Shanghai")
        df.timeZone = tz
        val date = Date()
        return df.format(date)
    }

    @Test
    fun `베이징 시간 확인하기`() {
        CustomLogger.getInstance().info("cn time " + getBeijingDatetime())
    }
}