
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class SerializationTest {

    /**
     * @param args the command line arguments
     */
    private static final String TIMEZONE_ID_AMERICA_TORONTO = "America/Toronto";

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_TORONTO));
    }
    private static final Timestamp TIME_STAMP = Timestamp.valueOf("1941-11-21 00:50:00.0");
    private static final long TIME = TIME_STAMP.getTime();

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        FileOutputStream f = new FileOutputStream("tmp");
        ObjectOutput s = new ObjectOutputStream(f);

        s.writeObject("Today");

        s.writeObject(new Date());

        long time = System.currentTimeMillis();
        s.writeObject(new java.sql.Date(time));

        java.sql.Time t = new java.sql.Time(time);
        s.writeObject(t);

        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
        s.writeObject(timestamp);

        Calendar calendar = new GregorianCalendar();
        Date calDate = calendar.getTime();

        s.writeObject(calDate);

        java.sql.Timestamp timestampC = java.sql.Timestamp.valueOf("1941-11-21 00:50:00.0");
        s.writeObject(timestampC);

        s.flush();

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
        System.out.println("java.sql.Date" + " " + sqlDate);
        System.out.println("java.sql.Time" + " " + tm);
        System.out.println("java.sql.Timestamp" + " " + sqlTimestamp);
//        System.out.println("Date from Calendar(java.util.Date)" + " " + cDate);
        System.out.println("Curated time stamp - Polaris Problem date" + " " + sqlTimestampC);

    }

}
