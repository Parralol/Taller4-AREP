package edu.escuelaing.arem.ASE.app.POJO.reflections;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import edu.escuelaing.arem.ASE.app.POJO.annotations.Component;
import edu.escuelaing.arem.ASE.app.POJO.annotations.GetMapping;
import edu.escuelaing.arem.ASE.app.POJO.annotations.RequestMapping;

/**
 * CREAR @Component, @Interface
 * 
 * CREAR @GetMapping, @Interface
 * 
 * Implementar HelloController
 */

public class Reflection {
    static String ruta = "/HelloController";
    static Map<String, Method> methods = new HashMap<String, Method>();

    public  void getClasses() throws ClassNotFoundException {

        String classPath = System.getProperty("java.class.path");

        findControllerClasses(classPath);


    }

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
            Method method = null;
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
    
    public Method getMethod(String endpoint){
        return methods.get(endpoint);
    }

    private  void findControllerClasses(String basePath) throws ClassNotFoundException {
        File baseDirectory = new File(basePath);

        if (!baseDirectory.exists() || !baseDirectory.isDirectory()) {
            throw new IllegalArgumentException("Invalid base directory");
        }



        searchClasses(baseDirectory, baseDirectory.getName()+ ".");


    }

    private  void searchClasses(File directory, String packageName) throws ClassNotFoundException {
        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                String newPackage = null;
                if((packageName.contains("classes"))){
                    newPackage = file.getName() + ".";
                }
                else{
                    newPackage = packageName + file.getName() + ".";
                }
                searchClasses(file, newPackage);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + file.getName().replace(".class", "");
                Class<?> aClass = Class.forName(className);
                Method[] methodss = aClass.getDeclaredMethods();
                for(Method method : methodss){
                    if(method.isAnnotationPresent(RequestMapping.class)){
                        String value = method.getAnnotation(RequestMapping.class).value();
                        methods.put(value,method);

                    }
                }
            }
        }
    }
   
}