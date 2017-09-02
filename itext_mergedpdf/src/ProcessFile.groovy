/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

def startTime = new Date().getTime();


if (args.length < 1 || args.length > 3) {
    println("Incorrect program usage");
    println("corect use: java -jar IText_MergedPdf.jar [path to pdf file] [optional path to PDF generated folder]");
    System.exit(1);
}

def file = new File(args[0])
def destDir = null
def streaming = false


if (args.length >= 2 ) {
    
    destDir = args[1]
    
    new File(args[1]).mkdir()
}


if (args.length == 3) {
    
    if (args[2] == "s") {
    
        streaming = true
        
    }
    
}

def listOfFiles = []

file.eachFileRecurse {
    if (it.isFile() ) {
         
        if (it.toString().contains(".pdf")) {
             
            listOfFiles.add(it.getCanonicalPath())
            
        }
   
    }
}


def mergePdf = new Concatenate()
mergePdf.concatenate(listOfFiles,destDir, streaming)

def endTime = new Date().getTime();
println("Total time taken : " + ((endTime - startTime) / 1000) + " seconds");