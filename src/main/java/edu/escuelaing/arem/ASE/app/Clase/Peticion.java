package edu.escuelaing.arem.ASE.app.Clase;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Peticion {
        private URL url;

    private Map<String,String> params;


    public Peticion(URL url){
        this.url = url;
        this.params = parseURLParameters(this.url);


    }


    public String getParam(String parameter){
        return params.get(parameter);
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    private HashMap<String, String> parseURLParameters(URL url) {
        HashMap<String, String> paramMap = new HashMap<>();
        String query = url.getQuery();
        if (query != null) {
            String[] queryParams = query.split("&");

            for (String param : queryParams) {
                String[] parts = param.split("=");
                if (parts.length == 2) {
                    String paramName = parts[0];
                    String paramValue = parts[1];
                    paramMap.put(paramName, paramValue);
                }
            }
        }
        return paramMap;
    }


    public void printParams(){
        for (String key : params.keySet()) {
            System.out.println("Clave: " + key + ", Valor: " + params.get(key));
        }
    }
}
