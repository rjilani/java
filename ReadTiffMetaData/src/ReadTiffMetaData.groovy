

def startTime = new Date().getTime();

if (args.length < 2 || args.length > 2) {
    println("Incorrect program usage");
    println("corect use: java -jar ReadTiffMetaData.jar [path to image dir e.g. - C:/user/imageDir] [path and file name of the csv file e.g - C:/user/imageDir/metadata.csv]");
    System.exit(1);
}


def imageWidth = ""

def imageLength  = ""

def compression  = ""

def resolution  = ""

def strips  = ""

def tiles  = ""

def dpi

def mpx

def fileRange

def file = new File(args[0])

def outFile = new File(args[1]).withWriter {out ->

    out.writeLine("FileName" + "," + "FileSize(KB)" + "," + "ImageWidth" + "," + "ImageLength" + "," + "SizeOfImage" + "," + "MPX" + "," + "Compression" + "," + "Resolution" + "," + "Strips" + "," + "Tiles"
        + "," + "FileSizeRange")

    file.eachFileRecurse {

        if (it.isFile() ) 
  
        { 
  
            if (it.name.toString().contains(".blob") || it.name.toString().contains(".TIF") || it.name.toString().contains(".tif")) {
   
                // println it
       
                filePath = it
                
                size = Math.round(it.length()/1024)
                
                sizeRange = Math.round(size/1024)
                
                println size
                
                if (sizeRange <= 5) {
                    
                    fileRange = "< 5 MB"
                } else if (sizeRange > 5 && sizeRange <= 10) {
                    
                    fileRange = "5 - 10 MB"
                } else if (sizeRange > 10 && sizeRange <= 15) {
                    
                    fileRange = "10 - 15 MB"
                    
                } else if (sizeRange > 15 && sizeRange <= 20) {
                    
                    fileRange = "15 - 20 MB"
                    
                } else if (sizeRange > 20 && sizeRange <= 25) {
                    
                    fileRange = "20 - 25 MB"
                } else {
                    
                    fileRange = "GT 25 MB"
                }
       
                def a = "tiffinfo -s " + "\"" +  filePath +  "\""
       
                println a
       
                def c = a.execute().text
       
                c.eachLine {line -> //println line
       
                    try {
                        if (line.contains("Image Width:")) {
       
                            def lin = line.split(" ")
                        
                            imageWidth = lin[4]
                            imageLength = lin[7]
                         
                        
       
                        }
                   
                   
                        if (line.contains("Resolution:")) {
       
                            def lin = line.split(":")
                        
                            resolution = lin[1].replace(",","")
                       
                            dpi = Integer.valueOf(resolution.split(" ")[1])
                        
                        }
                    
                        if (line.contains("Compression Scheme:")) {
       
                            def lin = line.split(":")
                        
                        
                            compression = lin[1]
                        
                        }
                    
                        if (line.contains("Strips:")) {
       
                            def lin = line.split(" ")
                      
                            strips = lin[2]
                           
                        }
                    
                        if (line.contains("Tiles:")) {
       
                            def lin = line.split(" ")
                        
                            tiles = lin[2]
                       
                        }
                        
                       
                        
                    } catch (Exception ex) {
                        println ex.getMessage()
                        
                    }
                }
                
                println imageWidth
                println imageLength
                println resolution
                println compression
                println strips
                println tiles
                println filePath
                
                def sizeOfImage = ""
                try {
                    
                    
                    if (imageWidth != "" && imageLength != "") {
                        def width = Math.round(Integer.valueOf(imageWidth) / dpi)
                        def height = Math.round (Integer.valueOf(imageLength) / dpi)
                    
                        mpx = Math.round((Integer.valueOf(imageWidth) * Integer.valueOf(imageLength) )/1000000)
                        sizeOfImage = width + " X " + height + " inch"
                    }
                
                        

                    out.writeLine(it.toString()+ "," + size + "," + imageWidth + "," + imageLength + "," + sizeOfImage + "," + mpx + "," 
                        + compression + "," + resolution + "," + strips + "," + tiles + "," + fileRange)
                    
                } catch (Exception ex) {
                    println ex.getMessage()
                    out.writeLine(it.toString()+ "," + size + "," + imageWidth + "," + imageLength + "," + sizeOfImage + "," + mpx + "," 
                        + compression + "," + resolution + "," + strips + "," + tiles + "," + fileRange)
                }
                
                        
                        
                imageWidth = ""
                imageLength = ""
                resolution = ""
                compression = ""
                strips = ""
                tiles = ""
                size = 0
       
       
                println "------------------------------------"
                
             
                       
    
            }
  
            
        }
        
        
    }
    
    
}

def endTime = new Date().getTime();

println("Total time taken : " + ((endTime - startTime) / 1000) + " seconds");