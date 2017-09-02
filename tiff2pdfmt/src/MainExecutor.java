
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rjilan01
 */
public class MainExecutor {

    private static final int NTHREDS = 5;
    private static final String folder = "C:/Users/rjilan01/Documents/test_tiff/NewSamples/OVERSIZED-V2";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Sleeping for 10 seconds");
        
        Thread.sleep(10000);
        
        long t1 = System.currentTimeMillis();
        
        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
        
        System.out.println ("Execute the thread pool for :" + args[0]);
        
        for (int i = 0; i < Integer.valueOf(args[0]); i++) {
            for (final File f : (new File(folder)).listFiles()) {
                if (f.getAbsolutePath().endsWith(".tif")) {
                    String[] fileName = {f.getAbsolutePath(), f.getName()};
                    Runnable worker = new RunTiff2PDF(fileName,folder);
                    executor.execute(worker);
                }
            }
        }
        // This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
        // Wait until all threads are finish
        executor.awaitTermination(100, TimeUnit.MINUTES);
        System.out.println("Finished all threads");
         System.out.println("Conversion took : " + (System.currentTimeMillis() - t1) / 1000 + " seconds" );
    }
}
