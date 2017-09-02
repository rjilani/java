
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import java.io.FileOutputStream;

public class TiffToPDF {

    public static void main(String args[]) throws DocumentException {

        if (args.length < 1 || args.length > 2) {
            System.out.println("Incorrect program usage");
            System.out.println("corect use: java -jar TiffToPDF [tiff file path] [pdf file path]");
            System.exit(1);
        }
        //for (int j = 0 ; j < 100000000; j++) {
            Document tifftoPDF = null;
            try {

                //Read the Tiff File
                RandomAccessFileOrArray myTiffFile = new RandomAccessFileOrArray(args[0]);

                //Find number of images in Tiff file
                int numberOfPages = TiffImage.getNumberOfPages(myTiffFile);

                System.out.println("Number of Images in Tiff File: " + numberOfPages);
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
                System.out.println("Tiff to PDF Conversion in Java Completed");
            } catch (Exception i1) {
                System.out.println("Bad tiff");
//            System.out.println(tifftoPDF.isOpen());
//            tifftoPDF.add(new Anchor("this is a link"));
//            tifftoPDF.close();
                System.out.println(i1.getMessage());
            }

        //}
    }
}
