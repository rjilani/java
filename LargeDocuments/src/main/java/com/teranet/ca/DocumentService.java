/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teranet.ca;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author rjilan01
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DocumentService {
   
    String echoBinaryAsString(byte[] bytes);
    
    DataHandler getFile(String fileName);
}
