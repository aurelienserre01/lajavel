package org.lajavel;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

public final class Log {

    public org.slf4j.Logger logger;

    private static Log instance;

    private Log(){
        this.logger = LoggerFactory.getLogger("lajavel");
    }

    public static Log getInstance(){
        if(instance == null){
            instance = new Log();
        }
        return instance;
    }

    public static void info(String message) {
        print(Log.Level.INFO, message);
    }

    public static void error(String message) {
        print(Log.Level.ERROR, message);
    }

    public static void warn(String message) {
        print(Log.Level.WARN, message);
    }

    public static void debug(String message) {
        print(Log.Level.DEBUG, message);
    }

    private static void print(Level level, String message) {
        int applicationLevel = Application.getInstance().mode.level;
        switch (level) {
            case DEBUG:
                if (applicationLevel >= Log.Level.DEBUG.level) {
                    getInstance().logger.debug(message);
                }
                break;
            case INFO:
                if (applicationLevel >= Log.Level.INFO.level) {
                    getInstance().logger.info(message);
                }
                break;
            case ERROR:
                if (applicationLevel >= Log.Level.ERROR.level) {
                    getInstance().logger.error(message);
                }
                break;
            case WARN:
                if (applicationLevel >= Log.Level.WARN.level) {
                    getInstance().logger.warn(message);
                }
        }

    }
    private enum Level {
        DEBUG(3),
        INFO(2),
        WARN(1),
        ERROR(0);

        public final int level;

        private Level(int level) {
            this.level = level;
        }
    }
}
