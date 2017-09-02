/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class ListCharset {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SortedMap charsets = Charset.availableCharsets();
        
        //System.out.println(charsets.toString());
        Set names = charsets.keySet();
        for (Iterator e = names.iterator(); e.hasNext();) {
            String name = (String) e.next();
            Charset charset = (Charset) charsets.get(name);
            System.out.println(charset);
            Set aliases = charset.aliases();
            for (Iterator ee = aliases.iterator(); ee.hasNext();) {
                System.out.println("    " + ee.next());
            }
        }

    }
}
