package edu.escuelaing.arem.ASE.app.Clase;


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
