/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teranet.ca;


import com.teranet.ca.DocumentServiceImpl;
import javax.xml.ws.Endpoint;

/**
 *
 * @author rjilan01
 */
public class ImagePublisher {
    
   public static void main(String[] args) {
	
                Endpoint.publish("http://localhost:9999/ws/echo", new DocumentServiceImpl());
		
		System.out.println("Server is published!");
	   
    }
    
}
