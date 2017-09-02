
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
public class PrintPDF implements Runnable {

    private PdfDecoder decodePdf = new PdfDecoder(true);
    private PrintService printingDevice = null;
    private PrintRequestAttributeSet attributeSet;
    private String path;
    private String fileName;

    


    public PrintPDF(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
        
        attributeSet = new HashPrintRequestAttributeSet();
        PrintService[] services = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE, attributeSet);
        for (PrintService s : services) {
            System.out.println(s.getName());
        }

        for (PrintService s : services) {
            if (s.getName().equals("\\\\mrkpsrprvwpr02\\New Canon UniFlow")) {
            //if (s.getName().equals("Foxit Reader PDF Printer")) {
                printingDevice = s;
            }
        }
    }
     @Override
    public void run() {
        System.out.println("file Name: " + path);



        try {
            decodePdf.openPdfFile(path);
            FontMappings.setFontReplacements();

            JobName jobName = new JobName(fileName, null);
            attributeSet.add(jobName);
            decodePdf.setPrintAutoRotateAndCenter(true);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_FIT_TO_PRINTER_MARGINS);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_NONE);
            decodePdf.setPrintPageScalingMode(PrinterOptions.PAGE_SCALING_REDUCE_TO_PRINTER_MARGINS);
            decodePdf.setPagePrintRange(1, decodePdf.getPageCount());

            PdfBook pdfBook = new PdfBook(decodePdf, printingDevice, attributeSet);
            SimpleDoc doc = new SimpleDoc(pdfBook, DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
            DocPrintJob printJob = printingDevice.createPrintJob();
            printJob.print(doc, attributeSet);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            closeDecoder();
        }

    }

    private void closeDecoder() {
        decodePdf.closePdfFile();
        System.out.println("decoded closed for: " + fileName);
    }
}
