import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class VlaidateXML {

    public static void main(String[] args) throws IOException, SAXException {
        // TODO code application logic here

        File requestFile = new File("./Data/SearchDocumentTest.xml");
        String message = FileUtils.readFileToString(requestFile, "UTF-8");
        System.out.println(message);
        System.out.println (TSB_UAT_Clinet.validateXMLSchema("./schema/Request/" + "DocumentserviceSearchDocumentRequest.xsd", message));
              
                
    }

}
