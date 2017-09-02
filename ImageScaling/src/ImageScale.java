
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.io.FileChannelRandomAccessSource;
import com.itextpdf.text.io.RandomAccessSource;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class ImageScale {

    private static final int PDF_DEFAULT_SCALE = 72;
    private static final int PERCENTAGE = 100;
    private static final float MAX_WIDTH = 14400f;
    private static final float MAX_HEIGHT = 14400f;

    public static void convertTiffToPdf(String inFile, String outFile) {

        Document document = new Document();

        try {
            String filename = inFile;

            Image[] image = getImageFromTiff(filename);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outFile));

            for (Image img : image) {

                System.out.println("Before Scaling to image DPI: " + img.getScaledWidth() + " X " + img.getScaledHeight());
                img.scalePercent((PERCENTAGE * PDF_DEFAULT_SCALE) / img.getDpiX());
                System.out.println("After Scaling to image DPI: " + img.getScaledWidth() + " X " + img.getScaledHeight());

                if (img.getScaledWidth() > MAX_WIDTH || img.getScaledHeight() > MAX_HEIGHT) {

                    System.out.println("Image is grater than defualt limit, scaling to fit in 200 x 200 inches");
                   
                    img.scaleToFit(MAX_WIDTH, MAX_HEIGHT);
                    
                     writer.setUserunit(2);
                    
                }

                float imgScaledWidth = img.getScaledWidth();
                float imgScaledHeight = img.getScaledHeight();
                Rectangle rectangle = new Rectangle(imgScaledWidth, imgScaledHeight);
                document.setPageSize(rectangle);

                document.open();

                document.add(img);
                document.newPage();

            }

        } catch (DocumentException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            document.close();
        }
    }

    private static Image[] getImageFromTiff(String filePath) throws IOException {

        RandomAccessFile aFile = new RandomAccessFile(filePath, "r");

        FileChannel inChannel = aFile.getChannel();

        RandomAccessSource ras = new FileChannelRandomAccessSource(inChannel);
        RandomAccessFileOrArray randomAccessFileOrArray = new RandomAccessFileOrArray(ras);

        int numberOfImages = TiffImage.getNumberOfPages(randomAccessFileOrArray);

        Image[] image = new Image[numberOfImages];

        for (int i = 0; i < numberOfImages; i++) {

            image[i] = TiffImage.getTiffImage(randomAccessFileOrArray, i + 1);
        }

        return image;

    }

}
