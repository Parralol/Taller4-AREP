package edu.escuelaing.arem.ASE.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import edu.escuelaing.arem.ASE.app.servers.HttpServer;

public class WebServers {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        HttpServer server = HttpServer.getHttpServer();
        server.start();
    }
}
