package com.teranet.util;



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import java.io.FileOutputStream;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class TiffToPDF {
    
    public final String dir = System.getProperty("user.dir");
    final static Logger logger = Logger.getLogger(TiffToPDF.class);

    public TiffToPDF() {
        PropertyConfigurator.configure(dir + "/config/log4j.properties");
    }
    
    
    
    public void processTiff(String args[]) throws DocumentException {

         

        Document tifftoPDF = null;
        try {

            //Read the Tiff File
            RandomAccessFileOrArray myTiffFile = new RandomAccessFileOrArray(args[0]);

            //Find number of images in Tiff file
            int numberOfPages = TiffImage.getNumberOfPages(myTiffFile);

            logger.debug("Number of Images in Tiff File: " + numberOfPages);
            tifftoPDF = new Document();
            PdfWriter.getInstance(tifftoPDF, new FileOutputStream(args[1]));
            tifftoPDF.open();

            //Run a for loop to extract images from Tiff file
            //into a Image object and add to PDF recursively
            for (int i = 1; i <= numberOfPages; i++) {
                Image tempImage = TiffImage.getTiffImage(myTiffFile, i);
                tifftoPDF.add(tempImage);
            }

            tifftoPDF.close();
            logger.debug("Tiff to PDF Conversion in Java Completed: " + args[0]);
        } catch (Exception ex) {
            logger.info("Bad tiff: " + args[0]);
            logger.info("Error message : " + ex.getMessage());
            
        }


    }
}
