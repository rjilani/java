/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teranet.ca;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

/**
 *
 * @author rjilan01
 */
@MTOM
@WebService(endpointInterface = "com.teranet.ca.ImageServer")
public class ImageServerImpl implements ImageServer {

    @Override
    public Image downloadImage(String name) {

        try {

            File image = new File("c:\\images\\" + name);
            return ImageIO.read(image);

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }
    }

    @Override
    public String uploadImage(Image data) {

        if (data != null) {
            //store somewhere
            return "Upload Successful";
        }

        throw new WebServiceException("Upload Failed!");

    }
}
