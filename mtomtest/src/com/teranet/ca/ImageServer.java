/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teranet.ca;

import java.awt.Image;
 
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
/**
 * 
 *
 * @author rjilan01
 */

@WebService
@SOAPBinding(style = Style.RPC)
public interface ImageServer {
    
    //download a image from server
	@WebMethod Image downloadImage(String name);
 
	//update image to server
	@WebMethod String uploadImage(Image data);
}
