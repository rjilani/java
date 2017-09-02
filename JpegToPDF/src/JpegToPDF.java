
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class JpegToPDF {

    public final String dir = System.getProperty("user.dir");
    final static Logger logger = Logger.getLogger(JpegToPDF.class);

    public JpegToPDF() {
        PropertyConfigurator.configure(dir + "/config/log4j.properties");
    }

    public void processTiff(String args[]) throws DocumentException, IOException {

        long t1 = System.currentTimeMillis();
        logger.info(">>> convert()");
        float ratio, base;
        RandomAccessFileOrArray randomAccessFileOrArray = null;
        Document document = null;
        FileOutputStream pdf_stream = null;

        int pages = 0;
        try {
            Image img = Image.getInstance(args[0]);

            float width = 0.0f;
            float height = 0.0f;

            
                float imgActualWidth = img.getWidth();
                float imgActualHeight = img.getHeight();

                logger.info("convert imgActualWIDTH: "
                        + imgActualWidth + "imgActualHeight: " + imgActualHeight);

                if (imgActualWidth > 14400) {
                    logger.info("convert Width is greater than Adobe allowable width of 14400 Width: "
                            + imgActualWidth + "Shrinking it to 14000");
                    imgActualWidth = 14400.0f;
                }
                if (imgActualWidth > width) {
                    width = imgActualWidth;
                }
                if (imgActualHeight > 14400) {
                    logger
                            .info("convert Height is greater than Adobe allowable width of 14400 Height: "
                            + imgActualHeight + "Shrinking it to 14000");
                    imgActualHeight = 14400.0f;
                }
                if (imgActualHeight > height) {
                    height = imgActualHeight;
                }
            

            Rectangle rectangle = new Rectangle(width, height);

            logger.info("convert, Document Size Width:" + width
                    + ", Height:" + height);
            document = new Document(rectangle);
            pdf_stream = new FileOutputStream(args[1]);
            PdfWriter writer = PdfWriter.getInstance(document, pdf_stream);
           
            document.open();
            PdfContentByte cb = writer.getDirectContent();

            //randomAccessFileOrArray.seek(0);

           
                //Image img = TiffImage.getTiffImage(randomAccessFileOrArray, i + 1);

                if (img!= null) {
                    logger.info(img.isJpeg());
                    base = img.getScaledHeight();
                    if (base != 0) {
                        logger.info("scale it!, OLD Values Width: " + img.getWidth() + " Height:" + img.getHeight());
                       //img[i].setCompressionLevel(9);
                        img.scaleToFit(width, height);
                        logger.info("scaled, new Values Width: " + width + " Height:"
                                + height);
                        img.setAbsolutePosition(0, 0);
                        cb.addImage(img);
                        document.newPage();
                        ++pages;
                    } 
                }
            
            
            logger.info("<<< convert(), Conversion took: "
                    + (System.currentTimeMillis() - t1));

        } catch (Exception ex) {
            logger.info("Bad tiff: " + args[0]);
            logger.info("Error message : " + ex.getMessage());
        }
        
        finally {
            close (randomAccessFileOrArray,document,pdf_stream);
        }

    }

    private void close(RandomAccessFileOrArray randomAccessFileOrArray, Document document, FileOutputStream pdf_stream) throws IOException {
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
