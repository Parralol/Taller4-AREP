package edu.escuelaing.arem.ASE.app.controllers;

import edu.escuelaing.arem.ASE.app.POJO.annotations.RequestMapping;

public class AppController {

    @RequestMapping( value = "POJO")
    public static String index(){
        return "ROOT";
    }
}
