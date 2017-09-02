/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teranet;

import com.teranet.ca.ImageServerImpl;
import com.teranet.ca.DocumentServiceImpl;
import javax.xml.ws.Endpoint;

/**
 *
 * @author rjilan01
 */
public class ImagePublisher {
    
   public static void main(String[] args) {
		
		//Endpoint.publish("http://40368-NB:9999/ws/image", new ImageServerImpl());
                //Endpoint.publish("http://40368-NB:9999/ws/echo", new DocumentServiceImpl());
                
                Endpoint.publish("http://127.0.0.1:9999/ws/image", new ImageServerImpl());
                Endpoint.publish("http://127.0.0.1:9999/ws/echo", new DocumentServiceImpl());
		
		System.out.println("Server is published!");
	   
    }
    
}
