
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class RunTiff2PDF implements Runnable {

    private final String[] args;
     private final String folder;
     

    public RunTiff2PDF(String args[], String folder) {

        this.args = args;
        this.folder = folder;
    }

    @Override
    public void run()  {
        long t1 = System.currentTimeMillis();
        System.out.println(">>> convert()");
        float ratio, base;
        RandomAccessFileOrArray randomAccessFileOrArray = null;
        Document document = null;
        FileOutputStream pdf_stream = null;

        int pages = 0;
        try {
            randomAccessFileOrArray = new RandomAccessFileOrArray(
                    args[0]);

            int numberOfImages = TiffImage.getNumberOfPages(randomAccessFileOrArray);

            float width = 0.0f;
            float height = 0.0f;

            Image[] img = new Image[numberOfImages];
            for (int i = 0; i < numberOfImages; ++i) {
                img[i] = TiffImage.getTiffImage(randomAccessFileOrArray, i + 1);
                float imgActualWidth = img[i].getWidth();
                float imgActualHeight = img[i].getHeight();

                System.out.println("convert imgActualWIDTH: "
                        + imgActualWidth + "imgActualHeight: " + imgActualHeight);

                if (imgActualWidth > 14400) {
                    System.out.println("convert Width is greater than Adobe allowable width of 14400 Width: "
                            + imgActualWidth + "Shrinking it to 14000");
                    imgActualWidth = 14400.0f;
                }
                if (imgActualWidth > width) {
                    width = imgActualWidth;
                }
                if (imgActualHeight > 14400) {
                    System.out.println("convert Height is greater than Adobe allowable width of 14400 Height: "
                            + imgActualHeight + "Shrinking it to 14000");
                    imgActualHeight = 14400.0f;
                }
                if (imgActualHeight > height) {
                    height = imgActualHeight;
                }
            }

            Rectangle rectangle = new Rectangle(width, height);

            System.out.println("convert, Document Size Width:" + width
                    + ", Height:" + height);
            document = new Document(rectangle);
            pdf_stream = new FileOutputStream(folder + "/" + args[1].replaceAll(".tif", ".pdf"));
            PdfWriter writer = PdfWriter.getInstance(document, pdf_stream);
            // writer.setCompressionLevel(9);
            //writer.setFullCompression();
            //writer.setUserunit(1.0f);
            document.open();
            PdfContentByte cb = writer.getDirectContent();

            //randomAccessFileOrArray.seek(0);

            for (int i = 0; i < numberOfImages; ++i) {
                //Image img = TiffImage.getTiffImage(randomAccessFileOrArray, i + 1);

                if (img[i] != null) {
                    System.out.println(img[i].isJpeg());
                    base = img[i].getScaledHeight();
                    if (base != 0) {
                        System.out.println("scale it!, OLD Values Width: " + img[i].getWidth()
                                + " Height:" + img[i].getHeight());
                        //img[i].setCompressionLevel(9);
                        System.out.println("DPI is :"
                                + img[i].getDpiY() + " user unit " + img[i].getDpiY() / 72.0f);

                        System.out.println("DPI is :"
                                + img[i].getDpiX() + " user unit " + img[i].getDpiX() / 72.0f);
                        //writer.setUserunit(img[i].getDpiY() / 72.0f);
                        img[i].scaleToFit(width, height);
                        //img[i].scalePercent(100 * 72/150);
                        System.out.println("scaled, new Values Width: " + width + " Height:"
                                + height);
                        img[i].setAbsolutePosition(0, 0);
                        cb.addImage(img[i]);
                        document.newPage();
                        ++pages;
                    } else {
                        continue;
                    }
                }
            }

            System.out.println("<<< convert(), Conversion took: "
                    + (System.currentTimeMillis() - t1));

        } catch (Exception ex) {
            System.out.println("Bad tiff: " + args[0]);
            System.out.println("Error message : " + ex.getMessage());
        } finally {
            try {
                close(randomAccessFileOrArray, document, pdf_stream);
            } catch (IOException ex) {
                Logger.getLogger(RunTiff2PDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void close(RandomAccessFileOrArray randomAccessFileOrArray, Document document, FileOutputStream pdf_stream) throws IOException {
        if (randomAccessFileOrArray != null) {
            randomAccessFileOrArray.close();
        }

        if (document != null) {
            document.close();
        }

        if (pdf_stream != null) {
            pdf_stream.close();
        }


    }
}