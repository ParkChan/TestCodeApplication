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

    private final static Logger logger = Logger.getLogger("CHAN_LOGGER");
    private static final boolean isSaveLogger = true;
    private CustomLogger() {
        initLogger();
    }

    public static CustomLogger getInstance() {
        return SingleTonHolder.instance;
    }

    private static void initLogger() {

        logger.setUseParentHandlers(false);

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
                Handler fileHandler = new FileHandler(logFilePath, true);
                fileHandler.setFormatter(formatter);
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Handler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warning(String message) {
        logger.warning(message);
    }

    public void severe(String message) {
        logger.severe(message);
    }

    private static class SingleTonHolder {
        private static final CustomLogger instance = new CustomLogger();
    }
}