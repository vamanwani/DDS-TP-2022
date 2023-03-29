package domain.server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {

    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer puerto;
        if(process.environment().get("PORT")!= null)
        {
            puerto = Integer.parseInt(process.environment().get("PORT"));
        }else {
            puerto = 9000;
        }
        Spark.port(puerto);
        Router.init();
        DebugScreen.enableDebugScreen();
    }
}
