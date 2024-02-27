package edu.escuelaing.arem.ASE.app.Services;

import java.util.HashMap;
import java.util.Map;

public class HttpMethodComp {
    static Map<String, HttpMethod> endpoints = new HashMap<>();

    public static void addEndpoint(String endpoint, HttpMethod method){
        endpoints.put(endpoint,method);


    }

    public static HttpMethod getMethod(String endpoint){
        return endpoints.get(endpoint);


    }
}
