
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Jpeg;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.imgscalr.Scalr;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class Tiff2PDF {

    public final String dir = System.getProperty("user.dir");
    final static Logger logger = Logger.getLogger(Tiff2PDF.class);

    public Tiff2PDF() {
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

            Rectangle rectangle = new Rectangle(1400, 1100);

            logger.info("convert, Document Size Width:" + width
                    + ", Height:" + height);
            document = new Document(rectangle);
            pdf_stream = new FileOutputStream(args[1]);
            PdfWriter writer = PdfWriter.getInstance(document, pdf_stream);
            // writer.setCompressionLevel(9);
            //writer.setFullCompression();
            //writer.setUserunit(72.0f);
            document.open();
            PdfContentByte cb = writer.getDirectContent();

            //randomAccessFileOrArray.seek(0);

            for (int i = 0; i < numberOfImages; ++i) {
                //Image img = TiffImage.getTiffImage(randomAccessFileOrArray, i + 1);

                if (img[i] != null) {
                    logger.info(img[i].isJpeg());
                    base = img[i].getScaledHeight();
                    if (base != 0) {
                        logger.info("scale it!, OLD Values Width: " + img[i].getWidth()
                                + " Height:" + img[i].getHeight());
                        //img[i].setCompressionLevel(9);

                        //
                        
                        InputStream in = new ByteArrayInputStream(img[i].getRawData());
                        BufferedImage bImageFromConvert = ImageIO.read(in);

                        //BufferedImage image = resizeImage(bImageFromConvert, bImageFromConvert.getType());
                        
                        BufferedImage image = Scalr.resize(bImageFromConvert, 0000);

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(image, "jpg", baos);
                        baos.flush();
                        byte[] imageInByte = baos.toByteArray();
                        
                        Jpeg j = new Jpeg(imageInByte);
                        //
                        img[i].scaleToFit(width, height);
                        logger.info("scaled, new Values Width: " + width + " Height:"
                                + height);
                        j.scaleToFit(width, height);
                        j.setAbsolutePosition(0, 0);
                        cb.addImage(j);
                        document.newPage();
                        ++pages;
                    } else {
                        continue;
                    }
                }
            }

            logger.info("<<< convert(), Conversion took: "
                    + (System.currentTimeMillis() - t1));

        } catch (Exception ex) {
            logger.info("Bad tiff: " + args[0]);
            logger.info("Error message : " + ex.getMessage());
        } finally {
            close(randomAccessFileOrArray, document, pdf_stream);
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

    private static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(7200, 7200, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 7200, 7200, null);
        g.dispose();

        return resizedImage;
    }
}
