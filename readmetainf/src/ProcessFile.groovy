
if (args.length < 1 || args.length > 2) {
    println("Incorrect program usage");
    println("corect use: java -jar ReadMetaInf [path to jar dir] [Path to Report Folder]");
    System.exit(1);
}

def file = new File(args[0])


def outFile = new File(args[1]+ "\\ManifestReport.txt").withWriter {out ->


    file.eachFileRecurse {
        if (it.isFile() ) {

            if (it.toString().contains(".jar")) {
             
                println it.name
         
                out.writeLine("------------------------ ${it.name} ------------------------")
                out.writeLine(it.getCanonicalPath())
                try {
                    new java.util.jar.JarFile(it.getCanonicalPath()).manifest.mainAttributes.entrySet().each  
                    {  
                      
                        out.writeLine ( "${it.key}: ${it.value}" ) 
                       
                      
                
                    } 
                } catch (Exception ex) {
                    System.out.println (ex.getMessage())
                    out.writeLine("Jar doesn't have minifest or check the permission of jar it may be read only")
                }
            
            
                out.writeLine("---------------------------------------------------------------------")
                out.writeLine("")
            }
        }
    }

    println "  "
    println "The report has been created in ${args[1]}" + "\\ManifestReport.txt"
}
