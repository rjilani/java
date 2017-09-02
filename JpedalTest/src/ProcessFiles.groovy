
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

def startTime = new Date().getTime();


if (args.length < 1 || args.length > 1) {
    println("Incorrect program usage");
    println("corect use: java -jar JpedalTest.jar [path to pdf folder]");
    System.exit(1);
}

def file = new File(args[0])

ExecutorService executor = Executors.newFixedThreadPool(2);





try {
    file.eachFileRecurse {
        if (it.isFile() ) {
         
            if (it.toString().endsWith(".pdf")) {
             
                Runnable worker = new PrintPDF(it.getCanonicalPath(),it.name);
                executor.execute(worker);
            
            }
   
        }
    }

    // This will make the executor accept no new threads
    // and finish all existing threads in the queue
    executor.shutdown();
    // Wait until all threads are finish
    executor.awaitTermination(100, TimeUnit.MINUTES);
    def endTime = new Date().getTime();
    println("Total time taken : " + ((endTime - startTime) / 1000) + " seconds");

} catch (Exception ex) {
    
    println ex.getMessage()
}

finally {
    System.exit(1)
}