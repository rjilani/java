
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.TimeZone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class ReadDate {

    /**
     * @param args the command line arguments
     */
    private static final String TIMEZONE_ID_AMERICA_NEWYORK = "America/New_York";
    private static final String TIMEZONE_ID_AMERICA_TORONTO = "America/Toronto";
    private static final String TIMEZONE_ID_AMERICA_CHICAGO = "America/Chicago";
    private static final String TIMEZONE_ID_CANADA_EASTERN = "Canada/Eastern";
    private static final String TIMEZONE_ID_US_EASTERN = "US/Eastern";
    private static final String TIMEZONE_ID_US_CENTRAL = "US/Central";
    private static final String TIMEZONE_ID_Canada_CENTRAL = "Canada/Central";
    private static final String TIMEZONE_ID_US_PACIFIC = "US/Pacific";
    private static final String TIMEZONE_ID_AMERICA_RAINYRIVER = "America/Rainy_River";

//    static {
//        TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_TORONTO));
//    }

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        String newline = System.getProperty("line.separator");
        String version = System.getProperty("java.version");
        String vendor = System.getProperty("java.vendor");
        String os_name = System.getProperty("os.name");
        String os_version = System.getProperty("os.version");

        File file = new File("./timezone_info.txt");

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

        bw.write(newline);

        //TimeZone timeZone = TimeZone.getTimeZone("America/NEW_YORK");
        //TimeZone.setDefault(timeZone);
        FileInputStream in = new FileInputStream("tmp");
        ObjectInputStream se = new ObjectInputStream(in);

        String today = (String) se.readObject();
        Date date = (Date) se.readObject();
        java.sql.Date sqlDate = (java.sql.Date) se.readObject();
        java.sql.Time tm = (java.sql.Time) se.readObject();
        java.sql.Timestamp sqlTimestamp = (java.sql.Timestamp) se.readObject();
        Date cDate = (Date) se.readObject();

        java.sql.Timestamp sqlTimestampC = (java.sql.Timestamp) se.readObject();

        System.out.println("java.util.Date" + " " + date);
        bw.write("java.util.Date" + " " + date);
        bw.write(newline);

        System.out.println("java.sql.Date" + " " + sqlDate);
        bw.write("java.sql.Date" + " " + sqlDate);
        bw.write(newline);

        System.out.println("java.sql.Time" + " " + tm);
        bw.write("java.sql.Time" + " " + tm);
        bw.write(newline);

        System.out.println("java.sql.Timestamp" + " " + sqlTimestamp);
        bw.write("java.sql.Timestamp" + " " + sqlTimestamp);
        bw.write(newline);

        //System.out.println("Date from Calendar(java.util.Date)" + " " + cDate);
        bw.write("Date from Calendar(java.util.Date)" + " " + cDate);
        bw.write(newline);

        System.out.println("Curated time stamp - Polaris Problem date" + " " + sqlTimestampC);
        bw.write("Curated time stamp" + " " + sqlTimestampC);
        bw.write(newline);

        bw.close();
    }

}
