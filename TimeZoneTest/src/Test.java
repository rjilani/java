
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class Test {

    public static void main(String[] args) {

        String newline = System.getProperty("line.separator");
        String version = System.getProperty("java.version");
        String vendor = System.getProperty("java.vendor");
        String os_name = System.getProperty("os.name");
        String os_version = System.getProperty("os.version");

        File file = new File("./timezone_info.txt");

        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Java Version: " + version + " " + vendor + newline);
            bw.write(newline);
            bw.write("OS Version: " + os_name + " " + os_version + newline);
            bw.write(newline);
            bw.write("Defualt Time Zone: " + newline);
            bw.write(newline);
            bw.write(TimeZone.getDefault().toString() + newline);
            System.out.println(TimeZone.getDefault().toString());
            bw.write(newline);

            bw.write("Available Server Time Zones: " + newline);
            bw.write(newline);
            String[] timeZones = TimeZone.getAvailableIDs();

            for (String timeZone : timeZones) {

                bw.write(timeZone + newline);
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
