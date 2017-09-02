/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTest {

    /**
     * @param args the command line arguments
     */
    public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm";

    public static void main(String[] args) throws ParseException, IOException {
        // TODO code application logic here

        String version = System.getProperty("java.version");
        String vendor = System.getProperty("java.vendor");
        String os_name = System.getProperty("os.name");
        String os_version = System.getProperty("os.version");

        String newline = System.getProperty("line.separator");


        File file = new File("./Date_Test.txt");

        file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("Java Version: " + version + " " + vendor + newline);
        bw.write(newline);
        bw.write("OS Version: " + os_name + " " + os_version + newline);
        bw.write(newline);

        Timestamp timeStamp = Timestamp.valueOf("1941-11-21 00:50:00.0");
        String locale = timeStamp.toLocaleString();
        int offset = timeStamp.getTimezoneOffset();
        //System.out.println("locale: " + locale + "  Offset: " + offset);
        bw.write("locale: " + locale + "  Offset: " + offset);
        TimeZone timeZone = TimeZone.getTimeZone("Canada/Eastern");
        //TimeZone timeZone = TimeZone.getTimeZone("Canada/Central");
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        dateFormat.setTimeZone(timeZone);
        String resultString = dateFormat.format(timeStamp);
        bw.write(newline);
        bw.write("Format:  In Date " + timeStamp.toString());
        bw.write("Format: Out Date " + resultString);

        bw.write(newline);
        bw.write(newline);
        bw.write("****Second part****");
        bw.write(newline);

        timeZone = TimeZone.getTimeZone("Canada/Eastern");
        //timeZone = TimeZone.getTimeZone("Canada/Central");
        dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        dateFormat.setTimeZone(timeZone);
        Date resultDate = dateFormat.parse("1941/11/21 00:50");
        bw.write("Parse:  In Date " + timeStamp.toString());
        bw.write("Parse: Out Date " + resultDate.toString());

        bw.close();
        System.out.println("Done");
    }

}
