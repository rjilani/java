import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.ws.BindingProvider;
import org.apache.commons.io.FileUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class EmailValidationServiceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File listOfEmails = new File("emails.txt");
        List<String> emails = FileUtils.readLines(listOfEmails, "UTF-8");

        FileUtils.deleteQuietly(new File("emails_results.txt"));
        File emailResults = new File("emails_results.txt");
        for (String email : emails) {
            //System.out.println(email);

            if (!(email.equals("[Valid email addresses]") || email.equals("[Invalid email addresses]"))) {

                String emailInfo = email + ":" + (isValidEmail(email) ? "Valid" : "Invalid");
                System.out.println(emailInfo);

                FileUtils.writeStringToFile(emailResults, emailInfo + "\n", true);

            }

        }

    }

    private static boolean isValidEmail(java.lang.String email) {
        org.my.ns.EmailValidationServiceImplService service = new org.my.ns.EmailValidationServiceImplService();
        org.my.ns.EmailValidationService port = service.getEmailValidationServiceImplPort();
        BindingProvider prov = (BindingProvider) port;
        prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://40368-NB:9000/ws/email");
        return port.isValidEmail(email);
    }

}
