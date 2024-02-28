package edu.escuelaing.arem.ASE.app;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import edu.escuelaing.arem.ASE.app.controllers.FileController;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest{

    @Test
    public void testGetFile() throws IOException  {
        FileController fileController = new FileController();
        String result = fileController.getFile("eba.png");
        assertNotNull(result);
    }

    @Test
    public void testSearchFile() throws IOException {
        FileController fileController = new FileController();
        String result = fileController.searchFile("sampleFile.txt");
        assertNotNull(result);
    }
    @Test
    public void testGetImage() throws IOException {
        FileController fileController = new FileController();
        byte[] result = fileController.getImage("eba.png");
        assertNotNull(result);
    }
}
