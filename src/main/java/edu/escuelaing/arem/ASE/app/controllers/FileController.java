package edu.escuelaing.arem.ASE.app.controllers;

import java.io.IOException;

import edu.escuelaing.arem.ASE.app.Services.FileService;
import edu.escuelaing.arem.ASE.app.Services.FileServiceComp;

public class FileController {
    
    FileService fileService;
    public FileController(){
         fileService = new FileServiceComp();

    }
    public String getFile(String file) throws IOException {
        return fileService.makeHead(file);


    }

    public String searchFile(String file) throws IOException{

        return fileService.searchFile(file);
    }

    public byte[] getImage(String imageName) throws IOException{
        return  fileService.getImage(imageName);
    }

}
