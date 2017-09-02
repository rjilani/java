/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

def startTime = new Date().getTime();

if (args.length < 1 || args.length > 2) {
    println("Incorrect program usage");
    println("corect use: java -jar JpegToPDF.jar [path to image dir] [optional path to PDF generated folder]");
    System.exit(1);
}

def file = new File(args[0])

def destDir = null

if (args.length >= 2 ) {
    
    destDir = args[1]
    
    new File(args[1]).mkdir()
}

def jpegToPdf = new JpegToPDF();



file.eachFileRecurse {
    if (it.isFile() ) {
         
        if (it.toString().contains(".jpg") || it.toString().contains(".jpeg")) {
            
            def pdfName
            
            if (destDir != null) {
                
                if (it.toString().contains(".jpg")) {
                    pdfName = args[1] + "/" + it.name.replace(".jpg", ".pdf")
                } else {
                    pdfName = args[1] + "/" + it.name.replace(".jpeg", ".pdf")
                }
                
                
            } else {
                if (it.toString().contains(".jpg")) {
                    pdfName = it.getCanonicalPath().replace(".jpg", ".pdf")
                } else {
                    pdfName = it.getCanonicalPath().replace(".jpeg", ".pdf")
                }
            }
            
            
            String[] x = [ it.getCanonicalPath(), pdfName]
            
            jpegToPdf.processTiff(x)
        }
         
        
    }
}

def endTime = new Date().getTime();

println("Total time taken : " + ((endTime - startTime) / 1000) + " seconds");