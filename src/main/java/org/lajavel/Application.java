package org.lajavel;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Application {

    private static Application instance;
    public Javalin server;
    public int port;
    public final Mode mode;



    private Application(int port, Mode mode) {
        this.port = port;
        this.server = Javalin.create(config -> {
            config.addStaticFiles("/public", Location.CLASSPATH);
        }).start(this.port);
        this.mode = mode;
    }


    public static  Application start(int port, Mode mode){
        if(instance == null){
            instance = new Application(port, mode);
            Log.info("Application Started");
        } else {
            throw new RuntimeException("Application already started");
        }
        return instance;
    }

    public static Application getInstance(){
        if(instance == null){
            throw new RuntimeException("Application not started");
        }
        return instance;
    }
    public static enum Mode {
        DEVELOPMENT(3),
        TEST(1),
        PRODUCTION(0);

        public final int level;

        private Mode(int level) {
            this.level = level;
        }
    }

}
