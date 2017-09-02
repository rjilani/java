
import java.io.FileOutputStream;
//Image class to extract frames from GIF image
import com.itextpdf.text.Image;
//PdfWriter object to write the PDF document
import com.itextpdf.text.pdf.PdfWriter;
//Document object to add logical image frames to PDF
import com.itextpdf.text.Document;
//we need the class below to read input GIF file
import com.itextpdf.text.pdf.codec.GifImage;

public class GifToPDF {

    public static void main(String[] args) {
        try {
            //Create Document Object
            Document convertGif2Pdf = new Document();
            //Create PdfWriter for Document to hold physical file
            PdfWriter.getInstance(convertGif2Pdf, new FileOutputStream("C:\\Users\\rjilan01\\Documents\\projects\\cycle.pdf"));
            convertGif2Pdf.open();
            GifImage myGif = new GifImage("C:\\Users\\rjilan01\\Documents\\the_100th_tour_de_france-1540005.3-hp.gif");
            //Get the number of frames in Gif Image
            System.out.println("Number of Frames to be converted" + myGif.getFrameCount());
            for (int i = 1; i <= myGif.getFrameCount(); i++) {
                convertGif2Pdf.add(myGif.getImage(i));
            }
            //Close Document
            convertGif2Pdf.close();
            System.out.println("Successfully Converted GIF to PDF in iText");
        } catch (Exception i1) {
            i1.printStackTrace();
        }
    }
}
