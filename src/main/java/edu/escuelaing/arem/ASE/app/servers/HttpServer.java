package edu.escuelaing.arem.ASE.app.servers;

import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class HttpServer {
    //private static String key = "&apikey=b5ed8d05";
    //private static String url = "http://www.omdbapi.com/?t=";
    //private static Map<String, String> cache = new HashMap<String, String>();
    private static HttpServer instance;


    public static HttpServer getHttpServer() throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if(instance == null){
            instance = new HttpServer();

        }
        return instance;
    }

    public HttpServer() throws IOException{
        start();
    }

    public void start() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Recibí: " + inputLine);
            if (!in.ready()) {
                break;
            }
        }
        outputLine = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Title of the document</title>\n" +
                "</head>" +
                "<body>" +
                "<h1>Mi propio mensaje</h1>" +
                "</body>" +
                "</html>";
        out.println(outputLine);
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
    /**
     * Allows to split the GET or POST request just to get the query
     * @param text
     * @return
     */
    private static String getQuery(String text){
        String[] deco1 = text.split(" ");
        String[] deco2 = deco1[1].split("\\?");
        System.out.println(Arrays.toString(deco2));
        if(deco2.length >=2){
            String[] deco3 = deco2[1].split("\\#");
            return deco3[0];
        }else{
            return deco2[0];
        }
    }
}
