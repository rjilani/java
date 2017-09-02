
import com.itextpdf.text.DocumentException;
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
public class Test {
    
    public static void main(String[] args) {
        
        JpegToPDF a = new JpegToPDF();
        try {
            a.processTiff(args);
        } catch (DocumentException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
