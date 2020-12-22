package com.example.lib.java.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * 멀티모듈에서 로그표시를 위한 유틸
 */
public class CustomLogger {

    private final static Logger LOG = Logger.getGlobal();
    private static final boolean isSaveLogger = true;
    private CustomLogger() {
        initLogger();
    }

    public static CustomLogger getInstance() {
        return SingleTonHolder.instance;
    }

    private static void initLogger() {
        //기본 로그 제거
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }

        CustomLogFormatter formatter = new CustomLogFormatter();
        if (isSaveLogger) {

            //로그 파일 저장
            String moduleName = "test";
            String filePath = "log/" + moduleName;
            File f = new File(filePath);
            f.mkdirs();

            String logPostfixFileName = new SimpleDateFormat("_yyyyMMdd").format(new Date());
            String logFileName = moduleName + logPostfixFileName + ".log";
            String logFilePath = filePath + "/" + logFileName;

            try {
                Handler fildHandler = new FileHandler(logFilePath, true);
                fildHandler.setFormatter(formatter);
                LOG.addHandler(fildHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Handler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        LOG.addHandler(handler);
    }

    public void info(String message) {
        LOG.info(message);
    }

    public void warning(String message) {
        LOG.warning(message);
    }

    public void severe(String message) {
        LOG.severe(message);
    }

    private static class SingleTonHolder {
        private static final CustomLogger instance = new CustomLogger();
    }
}