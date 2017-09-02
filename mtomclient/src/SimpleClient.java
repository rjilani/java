//
import com.teranet.ca.DocumentService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import javax.activation.DataHandler;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class SimpleClient {

    public static void main(String[] args) throws Exception {

        int loopTill = 10;
        String fileName = "MARBLES.pdf";

        if (args.length > 0) {
            loopTill = Integer.valueOf(args[0]);
            if (args.length == 2) {
                fileName = args[1];
            }
        }
        

        long startTime = new Date().getTime();
        //URL url = new URL("http://127.0.0.1:9999/ws/echo?wsdl");
        URL url = new URL("http://127.0.0.1:8080/ws/echo?wsdl"); //For MTOM
        QName qname = new QName("http://ca.teranet.com/", "DocumentServiceImplService");

        Service service = Service.create(url, qname);
        DocumentService client = service.getPort(DocumentService.class);

        String s = client.echoBinaryAsString("rashid".getBytes());
        //System.out.println("rashid".getBytes());
        System.out.println(s);
        for (int i = 0; i < loopTill; i++) {
            //String fileName = "anders_hejlsberg_linq_2005.wmv";
            
            DataHandler dataHandler = client.getFile(fileName);

            System.out.println(dataHandler.getContentType());
            OutputStream os = new FileOutputStream(new File("C:\\test\\" + fileName));

            dataHandler.writeTo(os);
        }
//        
//        fileName = "anders_hejlsberg_linq_2005.wmv";
//        dataHandler = client.getFile(fileName);
//        os = new FileOutputStream(new File("C:\\test\\" + fileName));
//        dataHandler.writeTo(os);
//        
//        fileName = "Apress.pdf";
//        dataHandler = client.getFile(fileName);
//        os = new FileOutputStream(new File("C:\\test\\" + fileName));
//        dataHandler.writeTo(os);


        long endTime = new Date().getTime();

        System.out.println("Total time taken : " + ((endTime - startTime) / 1000));
    }
}
