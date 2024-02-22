package edu.escuelaing.arem.ASE.app.Services;

import java.io.IOException;

public interface FileService {
    public String makeHead(String file)throws IOException;
    public String searchFile(String file) throws IOException;
    public byte[] getImage(String image)throws IOException;

}
