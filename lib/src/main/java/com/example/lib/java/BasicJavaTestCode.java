package com.example.lib.java;

import com.example.lib.java.util.CustomLogFormatter;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicJavaTestCode {

    private final static Logger LOG = Logger.getGlobal();
    
    public static void initLogger(boolean isSaveFile){
        //기본 로그 제거
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if(handlers[0] instanceof ConsoleHandler){
            rootLogger.removeHandler(handlers[0]);
        }
        LOG.setLevel(Level.INFO);

        Handler handler = null;
        if(isSaveFile){
            try {
                handler = new FileHandler("message.log", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            handler = new ConsoleHandler();
        }
        CustomLogFormatter formatter = new CustomLogFormatter();
        handler.setFormatter(formatter);
        LOG.addHandler(handler);
    }
    
    public static void print(String message) {
        LOG.info(message);
//        LOG.warning(message);
//        LOG.severe(message);
    }

    public static long[] solution(String[] words) {
        long[] answer = {};
        return answer;
    }
}