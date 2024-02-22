package edu.escuelaing.arem.ASE.app.POJO.reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import edu.escuelaing.arem.ASE.app.POJO.annotations.Component;
import edu.escuelaing.arem.ASE.app.POJO.annotations.GetMapping;

/**
 * CREAR @Component, @Interface
 * 
 * CREAR @GetMapping, @Interface
 * 
 * Implementar HelloController
 */

public class Reflection {
    static String ruta = "/HelloController";
    static Method method = null;

    static Map<String, Method> methods = new HashMap<String, Method>();

    public Method getMethod(String name){return methods.get(name);}

    



    public static void mains(String[] args) {

        //Suponemos que llega algo por el estilo /sadads
        
        try {
            Class<?> c = Class.forName(args[0]);
            if (c.isAnnotationPresent(Component.class)) {
                for (Method m : c.getDeclaredMethods()) {
                    if (m.isAnnotationPresent(GetMapping.class)) {
                        methods.put(m.getAnnotation(GetMapping.class).value(), m);
                        ruta = m.getAnnotation(GetMapping.class).value();
                    }
                }
            }
            
            String simulado = "/component/hello";
            String queryValue = "Santiago";
            if (simulado.startsWith("/component")) {
                Method llamado = methods.get(simulado.substring(10));
                if (llamado != null) {
                    if (llamado.getParameterCount() == 1) {
                        String[] margs=new String[]{queryValue};
                        System.out.println("Resultado :" + method.invoke(null, (Object)margs));
                    }else{
                        System.out.println("Resultado :" + method.invoke(null));
                    }
                }

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}