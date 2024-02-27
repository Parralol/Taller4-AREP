package edu.escuelaing.arem.ASE.app.Services;

import java.io.IOException;

import edu.escuelaing.arem.ASE.app.Clase.Peticion;
import edu.escuelaing.arem.ASE.app.Clase.Respuesta;

public interface HttpMethod {
    public String Action(Peticion request, Respuesta response) throws IOException;
}
