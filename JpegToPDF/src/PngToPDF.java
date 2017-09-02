//FileOutputStream holds the Physical PDF file
import java.io.FileOutputStream;
//The image class which will hold the PNG image as an object
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
//This class is required to read PNG image into Image object
import com.itextpdf.text.pdf.codec.PngImage;

public class PngToPDF {

    public static void main(String[] args) {
        try {
            //Create Document Object
            Document convertPngToPdf = new Document();
            //Create PdfWriter for Document to hold physical file
            //Change the PDF file path to suit to your needs
            PdfWriter.getInstance(convertPngToPdf, new FileOutputStream("C:/Users/rjilan01/Documents/test_tiff/ConvertImagetoPDF.pdf"));
            convertPngToPdf.open();
            //Get the PNG image to Convert to PDF
            //getImage of PngImage class is a static method
            //Edit the file location to suit to your needs
            Image convertBmp = PngImage.getImage("C:/Users/rjilan01/Documents/test_tiff/Sketch101W-v2.png");
            //Add image to Document
            convertPngToPdf.add(convertBmp);
            //Close Document
            convertPngToPdf.close();
            System.out.println("Converted and stamped PNG Image in a PDF Document Using iText and Java");
        } catch (Exception i1) {
            i1.printStackTrace();
        }
    }
}
