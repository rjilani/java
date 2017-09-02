
import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import java.io.IOException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */
public class Util {
    
    public static void main(String args[]) throws IOException {
        
        PdfReader reader = new PdfReader("C:\\Users\\rjilan01\\Documents\\projects\\CDS\\encryption\\encrypted.pdf");   
        
        reader.getPermissions();
        System.out.println(PdfEncryptor.getPermissionsVerbose(reader.getPermissions())); 
        System.out.println(PdfEncryptor.isDegradedPrintingAllowed(reader.getPermissions())); 
    }
    
}
