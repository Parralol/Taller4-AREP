package edu.escuelaing.arem.ASE.app.servers;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.escuelaing.arem.ASE.app.Clase.Peticion;
import edu.escuelaing.arem.ASE.app.Clase.Respuesta;
import edu.escuelaing.arem.ASE.app.POJO.reflections.Reflection;
import edu.escuelaing.arem.ASE.app.controllers.FileController;
import static edu.escuelaing.arem.ASE.app.Services.HttpMethodComp.getMethod;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public HttpServer() throws IOException, IllegalAccessException, InvocationTargetException{
        start();
    }

    public void start() throws IOException, IllegalAccessException, InvocationTargetException {
        ServerSocket serverSocket = null;
        FileController fileController = new FileController();
        Reflection refl = new Reflection();

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

        OutputStream outputStream = clientSocket.getOutputStream();
        boolean firstline= true;
        String method = "";
        String query = "";
        String file = "";
        String Host = "";
        List<String> response = new ArrayList<>();
        String header = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Recibí: " + inputLine);

            if(firstline){
                method = inputLine.split("/")[0].split(" ")[0];
                query = inputLine.split("/")[1].split(" ")[0];
                file = inputLine.split("/")[1].split("\\?")[0].split(" ")[0];
                firstline = false;

            } else if(inputLine.contains("Host")){
                Host = inputLine.split(" ")[1];
                System.out.println(Host);

            }
            if (!in.ready()) {
                break;
            }
        }
         String endpoint = file ;
            String URL = "http://"+ Host + "/" + query;
            System.out.println(endpoint);
            System.out.println(getMethod(endpoint));
            

            
            if(getMethod(endpoint) != null){
                header = fileController.getFile("rta.json");
                String jsonResponse = getMethod(endpoint).Action(new Peticion(new URL(URL)),new Respuesta());
                header += "Content-Length: " + jsonResponse.length() + "\r\n";
                response.add(jsonResponse);
            } else if (refl.getMethod(endpoint) != null) {
                Method aMethod = refl.getMethod(endpoint);
                header = fileController.getFile("rta.json");
                String jsonResponse = (String) aMethod.invoke(null);
                header += "Content-Length: " + jsonResponse.length() + "\r\n" + 
                                        "";
                response.add(jsonResponse);
                
            } else if(file.endsWith(".png")){
                header += fileController.getFile(file);
                System.out.println("Funciona" + header);
                out.println(header);
                out.println();
                byte[] data = fileController.getImage(file);
                out.print(data);
            }else if(!(file.isEmpty())){
                header = fileController.getFile(file);
            }
            else{
                System.out.println("val no funciona");
                //header += fileController.getFile("rta.root");
                String rootResponse = "<h1>THIS IS A ROOT PAGE OF SERVER</h1>";
                response.add(rootResponse);
            }
            
            //header
            if(!(file.endsWith(".jpg"))){
                out.println(header);
                //response
                for(String Answer : response){
                    System.out.println(Answer);
                    out.println(Answer);
                }
            }
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
