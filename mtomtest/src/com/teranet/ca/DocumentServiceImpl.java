/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teranet.ca;

import com.teranet.util.TiffToPDF;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author rjilan01
 */
@MTOM
@WebService(endpointInterface = "com.teranet.ca.DocumentService")
public class DocumentServiceImpl implements DocumentService {
    
    TiffToPDF tiffToPDF = new TiffToPDF();

    @Override
    public String echoBinaryAsString(byte[] bytes) {
        return new String(bytes);

    }

    @Override
    public DataHandler getFile(String fileName) {
        DataHandler datahandler = null;
        String a[] = new String[] {"C:\\Users\\rjilan01\\Documents\\projects\\Incidents\\tif_images\\blank_images",
            "C:\\Users\\rjilan01\\Documents\\projects\\Incidents"};
        
        try {
            //tiffToPDF.processTiff(a);
            datahandler = new DataHandler(new FileDataSource("C:\\images\\" + fileName));
        } catch (Exception ex) {
            Logger.getLogger(DocumentServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datahandler;
    }
}
