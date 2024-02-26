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
        switch (guard) {
            case ".css":
                res += "text/css";
                break;
            case ".js":
                res += "text/javascript";
                break;
            case ".json":
                res += "application/json";
                break;
            case ".png":
                res += "image/png";
                break;
            default:
                res += "text/html";
                break;
        }
        return res;
    }

    @Override
    public String searchFile(String file) throws IOException {
        String res = "";
        String path = "src/main/java/static" + file;
        File archv = new File(path);
        if (archv.exists())
            res = new String(Files.readAllBytes(Paths.get(path)));
        else
            res = "<p> Archivo inexistente/File does not exist </p>";
        return res;
    }

    @Override
    public byte[] getImage(String image) throws IOException {
        String path = "src/main/java/static";
        File arch = new File(path);
        byte[] res = {};
        if (arch.exists())
            res = Files.readAllBytes(Paths.get(path + image));
        else
            res = Files.readAllBytes(Paths.get(path + "eba"));
        return res;
    }

    private String header() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type:";
    }
}
