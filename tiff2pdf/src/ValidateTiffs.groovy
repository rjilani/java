

def fileName

def colorSpace

def compression

def fileSize

def file = new File(/Z:/)

def outFile = new File(/Z:\tiff_metadata.csv/).withWriter {out ->

    out.writeLine("FileName" + "," + "ColorSpace" + "," + "Compression" + "," +  "FileSize")

    file.eachFileRecurse {

        if (it.isFile() ) 
  
        { 
  
            if (it.name.toString().contains(".tif") || it.name.toString().contains(".TIF")) {
   
                println it
       
                filePath = it
       
                def a = "identify -verbose " + "\"" +  filePath +  "\""
       
                println a
       
                def c = a.execute().text
       
                c.eachLine {line -> //println line
       
                    if (line.contains("Image:")) {
       
                        def lin = line.split(":")
                        fileName = lin[2]
           
                        println fileName
       
                    }
       
                    if (line.contains("Colorspace:")) {
       
                        def lin = line.split(":")
                        colorSpace = lin[1]
           
                        println colorSpace
       
                    }
       
                    if (line.contains("Compression:")) {
       
                        def lin = line.split(":")
                        compression = lin[1]
           
                        println compression
       
                    }
       
                    if (line.contains("Filesize:")) {
       
                        def lin = line.split(":")
                        fileSize = lin[1]
           
                        println fileSize
       
                    }
       
                    if (fileSize != null && compression != null && colorSpace != null) {
                        out.writeLine(fileName + "," + colorSpace + "," + compression  + "," +  fileSize)
                         
                        fileSize = null
                        compression = null
                        colorSpace = null
                        
                        
                    }
                }
       
       
                println "------------------------------------"
                
                //out.writeLine(fileName + "," + colorSpace + "," + compression  + "," +  fileSize)
                
    
            }
  
            
        }
        
        
    }
    
    
}

