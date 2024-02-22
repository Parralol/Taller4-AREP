package edu.escuelaing.arem.ASE.app.controllers;

import edu.escuelaing.arem.ASE.app.POJO.annotations.Component;
import edu.escuelaing.arem.ASE.app.POJO.annotations.GetMapping;

@Component
public class HelloController {
    @GetMapping("/")
    public static String index(){
        return "xd";
    }

    @GetMapping("/as")
    public static String get(String name){
        return "yes" + name;
    }
    @GetMapping("/as")
    public static String gets(String val){
        return Double.toString(Double.valueOf(val));
    }
}
