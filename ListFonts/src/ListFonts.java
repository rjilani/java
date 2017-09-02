/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */
import java.awt.GraphicsEnvironment;


public class ListFonts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("***************************");
 
        System.out.println("****************************");
        System.out.println("**Listing Fonts Using Method From GraphicsEnvironment Class**");
        listUsingGraphicsEnvironment ();
    }

    public static void listUsingGraphicsEnvironment() {
        GraphicsEnvironment ge = null;

        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        String[] fontNames = ge.getAvailableFontFamilyNames();

        for (int i = 0; i < fontNames.length; i++) {
            System.out.println(fontNames[i]);
        }
    }
}
