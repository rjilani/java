/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */
public class Main {
    
    public static void main(String[] args) {
        
        String inFile = "C:\\Users\\rjilan01\\Documents\\test_tiff\\ScalingTest\\Sketch017R.tif";
        String outFile = inFile.replace(".tif", ".pdf");
        
        ImageScale.convertTiffToPdf(inFile, outFile);
        
    }
    
}
