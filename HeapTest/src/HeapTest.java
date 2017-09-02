
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */
public class HeapTest {

     List<String> alist = new ArrayList();
     static List<HeapTest> heapList = new ArrayList();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         for (int i = 0; i < Long.parseLong(args[0]); i++) {
            
           HeapTest aHepTest =  new HeapTest();
           aHepTest.addString(Long.parseLong(args[0]));
            heapList.add(aHepTest);
        }
        
        }
        
        public void addString(long range) {
            for (int i = 0; i < range; i++) {
            
            String a = new String("Rashid");
            String b = a + new String("Jilani");
            System.out.println(b);
            
            alist.add(b);
        }
    }
}
