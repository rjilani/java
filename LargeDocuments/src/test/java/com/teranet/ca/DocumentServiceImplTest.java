/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teranet.ca;

import java.net.MalformedURLException;
import java.net.URL;
import javax.activation.DataHandler;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import junit.framework.TestCase;

/**
 *
 * @author rjilan01
 */
public class DocumentServiceImplTest extends TestCase {
    
    public DocumentServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of echoBinaryAsString method, of class DocumentServiceImpl.
     */
    public void testEchoBinaryAsString() throws MalformedURLException {
        System.out.println("echoBinaryAsString");
        byte[] bytes = "rashid".getBytes();
        URL url = new URL("http://localhost:8080/ws/echo?wsdl");
        QName qname = new QName("http://ca.teranet.com/","DocumentServiceImplService");

        Service service = Service.create(url, qname);
        DocumentService client = service.getPort(DocumentService.class);
        String expResult = "rashid";
        String result = client.echoBinaryAsString(bytes);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getFile method, of class DocumentServiceImpl.
     */
    public void testGetFile() throws MalformedURLException {
        System.out.println("getFile");
        String fileName = "samplechapter2.pdf";
        
        URL url = new URL("http://localhost:8080/ws/echo?wsdl");
        QName qname = new QName("http://ca.teranet.com/","DocumentServiceImplService");
        Service service = Service.create(url, qname);
        DocumentService client = service.getPort(DocumentService.class);
        DataHandler result = client.getFile(fileName);
        assertNotNull(result);
    }
}
