
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import org.jpedal.PdfDecoder;
import org.jpedal.fonts.FontMappings;
import org.jpedal.objects.PrinterOptions;
import org.jpedal.utils.PdfBook;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class JpedalTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PdfDecoder decodePdf = new PdfDecoder(true);

        try {
            decodePdf.openPdfFile("C:/Users/rjilan01/Documents/test_tiff/NewSamples/OVERSIZED-V2/itext/Sketch202W-v2.pdf");
            FontMappings.setFontReplacements();
            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
            JobName jobName = new JobName("Example Print", null);
            attributeSet.add(jobName);
            decodePdf.setPrintAutoRotateAndCenter(true);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_FIT_TO_PRINTER_MARGINS);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_NONE);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_REDUCE_TO_PRINTER_MARGINS);
            decodePdf.setPagePrintRange(1, decodePdf.getPageCount());
            PrintService[] services = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE, attributeSet);

            for (PrintService s : services) {
                System.out.println(s.getName());
            }

            PrintService printingDevice = null;
            for (PrintService s : services) {
                if (s.getName().equals("\\\\mrkpsrprvwpr02\\New Canon UniFlow")) {
                    //if (s.getName().equals("Foxit Reader PDF Printer")) {
                    printingDevice = s;
                }
            }

            PdfBook pdfBook = new PdfBook(decodePdf, printingDevice, attributeSet);
            SimpleDoc doc = new SimpleDoc(pdfBook, DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
            DocPrintJob printJob = printingDevice.createPrintJob();
            printJob.print(doc, attributeSet);

        } catch (Exception e) {
        } finally {
            System.out.println("reached finally block");
            decodePdf.closePdfFile();
            System.exit(1);
        }
    }

    public void print(String path) {
        System.out.println(path);
        PdfDecoder decodePdf = new PdfDecoder(true);

        try {
            decodePdf.openPdfFile(path);
            FontMappings.setFontReplacements();
            PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
            JobName jobName = new JobName("Example Print", null);
            attributeSet.add(jobName);
            decodePdf.setPrintAutoRotateAndCenter(true);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_FIT_TO_PRINTER_MARGINS);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_NONE);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_REDUCE_TO_PRINTER_MARGINS);
            decodePdf.setPagePrintRange(1, decodePdf.getPageCount());
            PrintService[] services = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE, attributeSet);

            for (PrintService s : services) {
                System.out.println(s.getName());
            }

            PrintService printingDevice = null;
            for (PrintService s : services) {
                if (s.getName().equals("\\\\mrkpsrprvwpr02\\New Canon UniFlow")) {
                    //if (s.getName().equals("Foxit Reader PDF Printer")) {
                    printingDevice = s;
                }
            }

            PdfBook pdfBook = new PdfBook(decodePdf, printingDevice, attributeSet);
            SimpleDoc doc = new SimpleDoc(pdfBook, DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
            DocPrintJob printJob = printingDevice.createPrintJob();
            printJob.print(doc, attributeSet);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("reached finally block");
            decodePdf.closePdfFile();
            
        }
    }
}
