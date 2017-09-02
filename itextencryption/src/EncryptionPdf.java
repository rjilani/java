/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */



import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.util.Date;


public class EncryptionPdf {
    /** User password. */
    public static byte[] USER = "hello".getBytes();
    /** Owner password. */
    public static byte[] OWNER = "world".getBytes();
    
    /** The resulting PDF file. */
    public static final String RESULT1
        = "C:\\Users\\rjilan01\\Documents\\projects\\CDS\\encryption\\encrypted.pdf";
    /** The resulting PDF file. */
    public static final String RESULT2
        = "C:\\Users\\rjilan01\\Documents\\projects\\CDS\\encryption\\decrypted.pdf";
    /** The resulting PDF file. */
    public static final String RESULT3
        = "C:\\Users\\rjilan01\\Documents\\projects\\CDS\\encryption\\encryptedFromDecrypted.pdf";
    
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws DocumentException 
     * @throws IOException 
     */
    public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        writer.setEncryption(USER, OWNER, PdfWriter.ALLOW_DEGRADED_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        writer.createXmpMetadata();
        writer.setPageEvent(new Watermark());
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph("This is a test from Teranet "));
        document.add(new Paragraph("Today's Date is: " + new Date() ));
        document.add(new Paragraph("Please Ignore it"));
        // step 5
        document.close();
    }

    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void decryptPdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src, OWNER);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
    }

    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void encryptPdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.setEncryption(USER, OWNER,
            PdfWriter.ALLOW_DEGRADED_PRINTING, PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
        stamper.close();
    }
    
    /**
     * Main method.
     *
     * @param    args    no arguments needed
     * @throws DocumentException 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, DocumentException {
        EncryptionPdf metadata = new EncryptionPdf();
        metadata.createPdf(RESULT1);
        //metadata.decryptPdf(RESULT1, RESULT2);
        //metadata.encryptPdf(RESULT2, RESULT3);
    }
    
    class Watermark extends PdfPageEventHelper {
        
        Font FONT = new Font(FontFamily.HELVETICA, 52, Font.BOLD, new GrayColor(0.75f));
        
        public void onEndPage(PdfWriter writer, Document document) {
            ColumnText.showTextAligned(writer.getDirectContentUnder(),
                    Element.ALIGN_CENTER, new Phrase("Printing Test", FONT),
                    297.5f, 421, writer.getPageNumber() % 2 == 1 ? 45 : -45);
        }
    }
}