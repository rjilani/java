/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

def startTime = new Date().getTime();

def file = new File(/C:\Users\rjilan01\Documents\awstat_test/)
def ant = new AntBuilder()
/*file.eachFileRecurse {
    if (it.isFile() && it.toString().endsWith(".gz"))
    {
        //println it
        //println it.parent
        
        def src = it.toString()
        def dest = it.parent.toString()
        
        //println src
        //println dest
        ant.gunzip(src:"${src}" , dest:"${dest}")
    }
}*/

def outFile = new File(/C:\Users\rjilan01\Documents\awstat_test\info.txt/).withWriter {out ->
    file.eachFileRecurse {
        if (it.isFile() && it.toString().endsWith(".log"))
        {
            def logFile = new File(it.toString())
            logFile.eachLine {line ->
                if (/*line.contains("POST ") &&*/
                line.contains("https://www.teranetexpress.ca/csp/userRegistration/displayTransactions?transactionId") || 
                line.contains("https://www.teranetexpress.ca/csp/userRegistration/retrieveUserProfile")) {
                    println line  
                     out.writeLine(line)
                }
             
            }
        }
    }
}


def endTime = new Date().getTime();

println " "
println "Total time taken : " + ((endTime - startTime) / 1000) + " seconds";
println " "