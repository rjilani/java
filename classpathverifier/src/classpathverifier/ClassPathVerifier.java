/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classpathverifier;

import java.io.*;

public class ClassPathVerifier {
    public static void main(String args[])
    {
        try {
            BufferedReader br = new BufferedReader(
                                   new InputStreamReader(
                                      new FileInputStream(args[0])));
            String clsName="";

            while( (clsName = br.readLine()) != null) {
                try {
                    Class.forName(clsName);
                    System.out.print(".");
                } catch(Exception e) {
                    System.out.println("\nNOT FOUND: " + clsName);
                }
            }

            br.close();
        } catch(IOException ioe) {
            System.out.println("IOException: " + ioe);
            ioe.printStackTrace();
        }
    }
}

