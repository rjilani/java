import java.io.ByteArrayInputStream;
import javax.xml.ws.BindingProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
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
public class TSBNGTVClient {

    /**
     * @param args the command line arguments
     */
    static Properties prop = new Properties();
    static InputStream input = null;

    public static void main(String[] args) throws IOException, SAXException {
        // TODO code application logic here

        input = new FileInputStream("./config/config.properties");
        prop.load(input);

        String userId = prop.getProperty("userId");
        String password = prop.getProperty("password");
        String wsdlUrl = prop.getProperty("wsdl_url");

        System.out.println(userId);
        System.out.println(password);
        System.out.println(wsdlUrl);

        processRequest(userId, password, wsdlUrl);
    }

    private static void processRequest(String userId, String password, String wsdlUrl) throws IOException, SAXException {

        File listOfMethodsAndSchema = new File("./config/methodlist.txt");
        FileUtils.deleteQuietly(new File("./Data/response.xml"));
        FileUtils.deleteQuietly(new File("./Data/schemavalidation.csv"));
        File responseFile = new File("./Data/response.xml");
        File schemaValidationReponseFile = new File("./Data/schemavalidation.csv");
        List<String> methodsAndSchemas = FileUtils.readLines(listOfMethodsAndSchema, "UTF-8");

        for (String methodsAndSchema : methodsAndSchemas) {
//            System.out.println(methodsAndSchema);
            String[] arrOfMethodAndSchema = methodsAndSchema.split(",");
            String methodName = arrOfMethodAndSchema[0];
            String schemaName = arrOfMethodAndSchema[1];
            File file = new File("./Data/" + methodName);

            String message = FileUtils.readFileToString(file, "UTF-8");
            System.out.println(message);

            FileUtils.writeStringToFile(responseFile, methodName, true);
            String response = processService(message, userId, password, wsdlUrl);
            FileUtils.writeStringToFile(responseFile, response, true);
            System.out.println(response);
            //System.out.println(validateXMLSchema("./schema/response/" + schemaName, response));

            FileUtils.writeStringToFile(schemaValidationReponseFile, methodName + "," + schemaName + "," + validateXMLSchema("./schema/response/" + schemaName, response) + "\n", true);
        }
    }

    private static String processService(java.lang.String xmlToProcess, String userid, String password, String wsdlUrl) {
        ca.teranet.tsb.service.remote.TeranetServiceBean_Service service = new ca.teranet.tsb.service.remote.TeranetServiceBean_Service();
        ca.teranet.tsb.service.remote.TeranetServiceBean port = service.getTeranetServicePort();
        BindingProvider prov = (BindingProvider) port;
        if (wsdlUrl != null) {
            prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, wsdlUrl);
        }

        prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userid);
        prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        return port.processService(xmlToProcess);
    }

    private static boolean validateXMLSchema(String xsdPath, String response) throws SAXException {

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(response.getBytes())));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }
}
