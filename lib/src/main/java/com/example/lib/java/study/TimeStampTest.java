package com.example.lib.java.study;

import com.example.lib.java.util.LoggingLogger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeStampTest{


    public static void main(String[] args){
        LoggingLogger.getInstance().print("cn time " + getBeijingDatetime());
    }

    private static String getBeijingDatetime() {
        @SuppressWarnings("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        df.setTimeZone(tz);
        Date date = new Date();
        return df.format(date);
    }
}
