package com.teranet.ca.tifftopng;

import java.io.File;
import java.io.IOException;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.ProcessStarter;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException, IM4JavaException, InterruptedException {
        System.out.println("Hello World!");
        convertTifToPng(new File("C:/Users/rjilan01/Documents/test_tiff/NewSamples/OVERSIZED/Sketch105W.tif"),new File("C:/Users/rjilan01/Documents/test_tiff/NewSamples/OVERSIZED/Sketch105W.png"));
    }

    public static void convertTifToPng(File inputImage, File outputImage) throws IOException, IM4JavaException, InterruptedException {
        String myPath="C:\\Program Files (x86)\\ImageMagick-6.8.4-Q16";
        ProcessStarter.setGlobalSearchPath(myPath);
        
        IMOperation op = new IMOperation();
        op.addImage(); //place holder for input file
        op.addImage(); //place holder for output file

        ConvertCmd convert = new ConvertCmd();
        convert.run(op, new Object[]{inputImage.getAbsolutePath(), outputImage.getAbsolutePath()});
    }
}
