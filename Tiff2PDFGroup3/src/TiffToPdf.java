/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 **/
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TiffToPdf {

    private final String[] INPUT_FILES = {
            "C:\\Users\\rjilan01\\Desktop\\ptiff\\4_LT150067_23-lt.TIF"
    };

    private final String OUTPUT = "C:\\Users\\rjilan01\\Desktop\\ptiff\\365-output.pdf";

    public void createPdf() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(OUTPUT));
        document.open();

        for (String string : ImageIO.getReaderFormatNames()) {
            System.out.println(string);
        }

        for (String tifImage : INPUT_FILES) {

            FileInputStream fis = new FileInputStream(tifImage);
            BufferedImage img = ImageIO.read(fis);

            document.add(Image.getInstance(img, null));
        }

        document.close();
    }

    public static void main(String[] args) throws IOException, DocumentException {
        new TiffToPdf().createPdf();

    }
}
