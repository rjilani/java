/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

import java.io.IOException;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import java.io.File;

/**
 * Extracts images from a PDF file.
 */
public class ExtractImages {

    /**
     * The new document to which we've added a border rectangle.
     */
    //public static final String RESULT = "C:\\Users\\rjilan01\\Documents\\test_tiff\\reduce_image\\Img%s.%s";
    //public static final String RESULT = "\\Img%s.%s";
    /**
     * Parses a PDF and extracts all the images.
     *
     * @param src the source PDF
     * @param dest the resulting PDF
     */
    public void extractImages(String filename, String path)
            throws IOException, DocumentException {
        PdfReader reader = new PdfReader(filename);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        MyImageRenderListener listener = new MyImageRenderListener(path + "\\Img%s.%s");
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {

            System.out.println(i);
            parser.processContent(i, listener);
        }
    }

    /**
     * Main method.
     *
     * @param args no arguments needed
     * @throws DocumentException
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, DocumentException {

        //new ExtractImages().extractImages("C:\\Users\\rjilan01\\Documents\\test_tiff\\reduce_image\\j2000_2_2pgs-From JPEG2000.pdf");
        File file = new File(args[0].replace(".pdf",""));
        file.mkdir();
        new ExtractImages().extractImages(args[0], file.getAbsolutePath());

    }
}