

import com.itextpdf.text.pdf.parser.PdfTextExtractor
import com.itextpdf.text.pdf.PdfReader
import groovy.io.FileType



if (args.length < 1 || args.length > 1) {
    println("Incorrect program usage");
    println("corect use: java -jar PDFToText.jar [path to PDF folder]");
    System.exit(1);
}

def startTime = new Date().getTime();

//def dir = new File(/C:\Users\rjilan01\Documents\April-30th-StruckThreads/)
def dir = new File(args[0])
def counter = 0
dir.eachFileRecurse(FileType.FILES){
   
    def fileName = it.toString()
    
    if (fileName.endsWith(".pdf")) {
        println it
        counter++
        
        def pdf = new PdfReader(fileName)
        def maxPages = pdf.numberOfPages + 1
        def parser = new PdfTextExtractor()
        
        def txtFileName = fileName.replace(".pdf",".txt")
        def outFile = new File(txtFileName).withWriter{out ->
            
        
            (1..<maxPages).each { pageNumber ->
                out.writeLine(parser.getTextFromPage(pdf, pageNumber))
            }
        }
   
    }
}


def endTime = new Date().getTime();

println " "
println "Toal number of file ${counter}"
println " "
println "Total time taken : " + ((endTime - startTime) / 1000) + " seconds";
println " "