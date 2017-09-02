/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

def startTime = new Date().getTime();

if (args.length < 1 || args.length > 3) {
    println("Incorrect program usage");
    println("corect use: java -jar Tiff2PDF.jar [path to image dir] [optional path to PDF generated folder] [optional path to switch to iText 1.4 library]");
    System.exit(1);
}

Thread.sleep (10000);
def file = new File(args[0])

def destDir = null

if (args.length >= 2 ) {
    
    destDir = args[1]
    
    new File(args[1]).mkdir()
}

def tiffProcessor 

if (args.length == 3 && args[2].equals("1.4")) {
    tiffProcessor = new TiffToPDFCDS14();
    println "----------------------- using iText 1.4 -----------------------------"
    println ("      ")
} else {
    tiffProcessor = new Tiff2PDFCDS();
    println "----------------------- using iText 5.x -----------------------------"
    println ("      ")
}

file.eachFileRecurse {
    if (it.isFile() ) {
         
        if (it.toString().contains(".tif") || it.toString().contains(".TIF")) {
            
            def pdfName
            
            if (destDir != null) {
                
                if (it.toString().contains(".tif")) {
                    pdfName = args[1] + "/" + it.name.replace(".tif", ".pdf")
                } else {
                    pdfName = args[1] + "/" + it.name.replace(".TIF", ".pdf")
                }
                
                
            } else {
                if (it.toString().contains(".tif")) {
                    pdfName = it.getCanonicalPath().replace(".tif", ".pdf")
                } else {
                    pdfName = it.getCanonicalPath().replace(".TIF", ".pdf")
                }
            }
            
            
            String[] x = [ it.getCanonicalPath(), pdfName]
            
            tiffProcessor.processTiff(x)
        }
         
        
    }
}

def endTime = new Date().getTime();

println("Total time taken : " + ((endTime - startTime) / 1000) + " seconds");