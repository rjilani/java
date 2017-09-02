/*
 * Delete unnecessary dir from jboss profile
 * 
 */
try {
    
    def prop = new Properties()

    prop.load(new FileInputStream("./profile.properties"))

    def profDir = prop.getProperty("JBOSS_PROFILE_DIR")
    def foldToDelete = prop.getProperty("FOLDER_TO_DELETE")
    def bkupDir = prop.getProperty("BACKUP_DIR")
    def rollBack = prop.getProperty("ROLL_BACK")
       
    println("Profile dir=${profDir}");
    println("Folder to delete=${foldToDelete}");
    println("Backup folder=${bkupDir}");
    println("Roll Back=${rollBack}");
    
    println("                      ");
    
    if (profDir == null || foldToDelete == null || bkupDir == null || rollBack == null) {
        
        println """make sure the right path for Jboss profile, backup folder and folder to delete exist in profile.propertie. 
                 Please also check the role back attibutes is set to 0 or 1"""
        println("                      ");
        println("Profile dir=${profDir}");
        println("Folder to delete=${foldToDelete}");
        println("Backup folder=${bkupDir}");
        println("RollBack=${rollBack}");
        println("                      ");
        
        System.exit(1)
    }
    
    if (rollBack == "1") {
        rollBackFiles(bkupDir)
    } else if (rollBack == "0"){
        processFiles(profDir,foldToDelete,bkupDir)
    } else {
        println ("Please check the roll back property and set it 0 or 1")
    }
    
} catch (Exception ex) {
    println (ex.message)
}

def processFiles(profDir,foldToDelete,bkupDir) {
    
    def listOfFlders = []
    
    foldToDelete.split(",").each {
        listOfFlders << it
    }
    
    date = new Date()
    def tDate = date.getDateString().replace("/","")
    def folderCreated = new File("${bkupDir}/${tDate}").mkdir()
    
    println folderCreated
    def ant = new AntBuilder()
    def file = new File("${bkupDir}/${tDate}")
    
    def outFile = new File("${bkupDir}/${tDate}/ProcessedFiles.csv").withWriter {out ->
        out.writeLine("FileName" + "," + "Path")
        
        listOfFlders.each {
            def dirToDelete = new File("${profDir}/${it}")        
            if (new File("${profDir}").exists() && dirToDelete.exists()  && new File("${bkupDir}").exists() ) {
            
                if (dirToDelete.isFile()) {
                    println "...... Deleting ${it} ......."
                    ant.move(file:"${dirToDelete.getCanonicalPath()}" , todir:"${file.getCanonicalPath()}" )
                    println "File ${dirToDelete} is deleted"
                
                    println("                      ");
                    
                    out.writeLine(dirToDelete.name + "," + dirToDelete.getParent())
                
                } else {
                    
                    println "...... Deleting ${it} ......."
       
                    /*ant.copy(todir:"${file.getCanonicalPath()}" ) {
                        fileset(dir:"${dirToDelete}" )
                    }
                    ant.delete(dir:"${dirToDelete}")*/
                    
                    
                    ant.move(file:"${dirToDelete.getCanonicalPath()}" , todir:"${file.getCanonicalPath()}" )
                    println "Folder ${dirToDelete} is deleted"
            
                    println("                      ");
                    out.writeLine(dirToDelete.name + "," + dirToDelete.getParent())
                }
        
        
            } else {
                println "Folder to delete ${dirToDelete} does not exist"
            }
        }
    }
}

def rollBackFiles(bkupDir) {
    
    println "------------- Roling Back -------------"
   
    date = new Date()
    def tDate = date.getDateString().replace("/","")
    def inFile = new File("${bkupDir}/${tDate}/ProcessedFiles.csv")
    def ant = new AntBuilder()
    
    inFile.eachLine {line, index -> //println "index:${index} line:${line}"
        if (index > 1) {
            def folders = line.split(",")
            println "${folders[0]}   ${folders[1]}"
            
            file = new File("${bkupDir}/${tDate}/${folders[0]}")
            
            if (file.isFile()) { 
                ant.move(file:"${file.getCanonicalPath()}" , todir:"${folders[1]}" )
                println "File ${folders[0]} is copied back to the source folder"
               
            } else {
                
                ant.move(file:"${file.getCanonicalPath()}" , todir:"${folders[1]}" )
                println "Folder ${folders[0]} is copied back to the source folder"
            }
        }
    }
    
    inFile.delete()

}