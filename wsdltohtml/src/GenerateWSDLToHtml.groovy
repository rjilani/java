/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

def file = new File(/C:\Users\rjilan01\Documents\Teranet_WSDLS\CDS\services_wsdl.txt/)

file.eachLine {println it
    def trasnform = new WSDLToHtml(it, 
    /C:\Users\rjilan01\Documents\Teranet_WSDLS\GWH_wsdl\wsdl-viewer.xsl/, it.replace(".xml",".html"))
}

