package edu.escuelaing.arem.ASE.app.Services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileServiceComp implements FileService {

    @Override
    public String makeHead(String file) {
        String res = header();
        String guard = file.split("\\.")[1];
        guard = "." + guard;
        System.out.println(guard + "<--- guarda");
        switch (guard) {
            case ".css":
                res += " text/css" + "\r\n";
                break;
            case ".js":
                res += " text/javascript"+ "\r\n";
                break;
            case ".json":
                res += " application/json"+ "\r\n";
                break;
            case ".png":
                res += " image/png";
                break;
            default:
                res += " text/html"+ "\r\n";
                break;
        }
        System.out.println(res+ "<---- RES");
        return res;
    }

    @Override
    public String searchFile(String file) throws IOException {
        String res = "";
        String path = "src/main/java/static/" + file;
        File archv = new File(path);
        if (archv.exists())
            res = new String(Files.readAllBytes(Paths.get(path)));
        else
            res = "<p> Archivo inexistente/File does not exist </p>";
        return res;
    }

    @Override
    public byte[] getImage(String image) throws IOException {
        String path = "src/main/java/static/" + image;
        File arch = new File(path);
        byte[] res = {};
        if (arch.exists()){
            System.out.println("exists");
            res = Files.readAllBytes(Paths.get(path));
        }else
            res = Files.readAllBytes(Paths.get(path + "eba"));
        return res;
    }

    private String header() {
        return "HTTP/1.1 200 OK"
                + "Content-Type:";
    }
}
