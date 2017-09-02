/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

import com.itextpdf.text.pdf.PdfName;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MyImageRenderListener implements RenderListener {

    protected String path = "";

    public MyImageRenderListener(String path) {
        this.path = path;
    }

    public void beginTextBlock() {
    }

    public void endTextBlock() {
    }

    public void renderImage(ImageRenderInfo renderInfo) {
        try {
            String filename;
            FileOutputStream os;
            PdfImageObject image = renderInfo.getImage();
            System.out.println (image.getFileType());
            PdfName filter = (PdfName) image.get(PdfName.FILTER);
            
            System.out.println("------------ Filters: " + filter);
            
            if (PdfName.DCTDECODE.equals(filter)) {
                System.out.println("jpg");
                filename = String.format(path,
                        renderInfo.getRef().getNumber(), "jpg");
                os = new FileOutputStream(filename);
                os.write(image.getStreamBytes());
                //os.write(image.getImageAsBytes()); //use for iText 5.4.4
                os.flush();
                os.close();
            } else if (PdfName.JPXDECODE.equals(filter)) {
                System.out.println("jpg 2000");
                filename = String.format(path,
                        renderInfo.getRef().getNumber(), "jp2");
                os = new FileOutputStream(filename);
                os.write(image.getStreamBytes());
                 //os.write(image.getImageAsBytes()); //use for iText 5.4.4
                os.flush();
                os.close();
            } else if (PdfName.JBIG2DECODE.equals(filter)) {
                System.out.println("jbig 2");
                filename = String.format(path,
                        renderInfo.getRef().getNumber(), "jbig2");
                os = new FileOutputStream(filename);
                os.write(image.getStreamBytes());
                 //os.write(image.getImageAsBytes()); //use for iText 5.4.4
                os.flush();
                os.close();
            }
            
            else {
                System.out.println("other");
                System.out.println(filter.toString());
                BufferedImage awtimage =
                        renderInfo.getImage().getBufferedImage();
                if (awtimage != null) {
                    filename = String.format(path,
                            renderInfo.getRef().getNumber(), "png");
                    ImageIO.write(awtimage, "png",
                            new FileOutputStream(filename));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void renderText(TextRenderInfo renderInfo) {
    }
}
