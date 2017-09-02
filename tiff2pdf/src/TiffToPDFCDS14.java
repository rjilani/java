

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RandomAccessFileOrArray;
import com.lowagie.text.pdf.codec.TiffImage;
import java.io.FileOutputStream;
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
public class TiffToPDFCDS14 {

    public final String dir = System.getProperty("user.dir");
    final static Logger logger = Logger.getLogger(TiffToPDFCDS14.class);

    public TiffToPDFCDS14() {
        PropertyConfigurator.configure(dir + "/config/log4j.properties");
    }

    public void processTiff(String args[]) throws DocumentException {

        long t1 = System.currentTimeMillis();
        logger.info(">>> convert()");
        float ratio, base;

        int pages = 0;
        try {
            RandomAccessFileOrArray randomAccessFileOrArray = new RandomAccessFileOrArray(
                    args[0]);

            int numberOfImages = TiffImage.getNumberOfPages(randomAccessFileOrArray);

            float width = 0.0f;
            float height = 0.0f;

            for (int i = 0; i < numberOfImages; ++i) {
                Image img = TiffImage.getTiffImage(randomAccessFileOrArray, i + 1);
                float imgActualWidth = img.width();
                float imgActualHeight = img.height();
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
            }

            Rectangle rectangle = new Rectangle(width, height);

            logger.info("convert, Document Size Width:" + width
                    + ", Height:" + height);
            Document document = new Document(rectangle);
            FileOutputStream pdf_stream = new FileOutputStream(args[1]);
            PdfWriter writer = PdfWriter.getInstance(document, pdf_stream);
            document.open();
            PdfContentByte cb = writer.getDirectContent();

            for (int i = 0; i < numberOfImages; ++i) {
                Image img = TiffImage.getTiffImage(randomAccessFileOrArray, i + 1);

                if (img != null) {
                    base = img.scaledHeight();
                    if (base != 0) {
                        logger.info("scale it!, OLD Values Width: " + img.width()
                                + " Height:" + img.height());
                        img.scaleToFit(width, height);
                        logger.info("scaled, new Values Width: " + width + " Height:"
                                + height);
                        img.setAbsolutePosition(0, 0);
                        cb.addImage(img);
                        document.newPage();
                        ++pages;
                    } else {
                        continue;
                    }
                }
            }
            randomAccessFileOrArray.close();
            document.close();
            pdf_stream.close();
            logger.info("<<< convert(), Conversion took: "
                    + (System.currentTimeMillis() - t1));

        } catch (Exception ex) {
            logger.info("Bad tiff: " + args[0]);
            logger.info("Error message : " + ex.getMessage());
        }

    }
}