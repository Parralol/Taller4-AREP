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
        // Assuming "sampleFile.txt" exists for testing
        String result = fileController.getFile("eba.png");
        assertNotNull(result);
        // Add more assertions based on your actual implementation
    }

    @Test
    public void testSearchFile() throws IOException {
        FileController fileController = new FileController();
        // Assuming "sampleFile.txt" exists for testing
        String result = fileController.searchFile("sampleFile.txt");
        assertNotNull(result);
        // Add more assertions based on your actual implementation
    }
    @Test
    public void testGetImage() throws IOException {
        FileController fileController = new FileController();
        // Assuming "sampleImage.jpg" exists for testing
        byte[] result = fileController.getImage("eba.png");
        assertNotNull(result);
        // Add more assertions based on your actual implementation
    }
}
