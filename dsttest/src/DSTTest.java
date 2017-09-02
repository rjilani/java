
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.DST_OFFSET;
import static java.util.Calendar.ZONE_OFFSET;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
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
public class DSTTest {

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
    private static final String TIMEZONE_ID_UTC = "UTC";
    private static final String TIMEZONE_ID_US_PACIFIC = "US/Pacific";
    private static final String TIMEZONE_ID_AMERICA_RAINYRIVER = "America/Rainy_River";
    private static final long MILLS_TO_HOURS = 60 * 60 * 1000;

    private static final String NEW_LINE = System.getProperty("line.separator");

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_TORONTO));
    }

    //private static final Timestamp TIME_STAMP = Timestamp.valueOf("1945-04-21 00:50:00.0");
    //private static final Timestamp TIME_STAMP = Timestamp.valueOf("2014-09-25 01:00:00.0");
    //private static final Timestamp TIME_STAMP = Timestamp.valueOf("1943-01-30 01:00:00.0");
    //private static final Timestamp TIME_STAMP = Timestamp.valueOf("1940-05-19 01:00:00.0");
    private static final Timestamp TIME_STAMP = Timestamp.valueOf("1941-11-21 00:50:00.0");
    private static final long TIME = TIME_STAMP.getTime();

    public static void main(String[] args) throws ParseException {

        //simpleDateTest(TIMEZONE_ID_AMERICA_CHICAGO);
        dstTest(TIMEZONE_ID_AMERICA_TORONTO);
        //dstTestCalendar(TIMEZONE_ID_AMERICA_NEWYORK);
        //curatedDateTest(TIMEZONE_ID_AMERICA_NEWYORK);
    }

    private static void simpleDateTest(String timeZoneId) {

        System.out.println("******************** Begin simpleDateTest *************************");

        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);

        TimeZone.setDefault(timeZone);

        long time = System.currentTimeMillis();

        java.util.Date date = new java.util.Date(time);

        java.sql.Date sqlDate = new java.sql.Date(time);

        java.sql.Time sqlTime = new java.sql.Time(time);

        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(time);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("java.util.Date " + date);

        System.out.println("java.sql.Date " + sqlDate);

        System.out.println("java.sql.Time " + sqlTime);

        System.out.println("java.sql.Timestamp " + sqlTimestamp);

        System.out.println("******************** End simpleDateTest *************************");
    }

    private static void dstTest(String timeZoneId) {

        System.out.println("******************** Begin dstTest *************************");

        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);

        TimeZone.setDefault(timeZone);

        System.out.println("long value " + TIME);

        java.util.Date date = new java.util.Date(TIME);

        java.sql.Date sqlDate = new java.sql.Date(TIME);

        java.sql.Time time = new java.sql.Time(TIME);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("Time Zone offset " + timeZone.getOffset(Calendar.ZONE_OFFSET) / MILLS_TO_HOURS);
        //System.out.println("DST offset " + timeZone.getDSTSavings());

        System.out.println("java.util.Date " + date);

        System.out.println("java.sql.Date " + sqlDate);

        System.out.println("java.sql.Time " + time);

        System.out.println("java.sql.Timestamp " + TIME_STAMP);

        System.out.println("Is day light saving " + timeZone.inDaylightTime(date));

        System.out.println("***********************************************");

        timeZone = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_NEWYORK);

        TimeZone.setDefault(timeZone);
        System.out.println("long value " + TIME);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("Time Zone offset " + timeZone.getOffset(Calendar.ZONE_OFFSET) / MILLS_TO_HOURS);

        //System.out.println("DST offset " + timeZone.getDSTSavings());
        System.out.println("java.util.Date " + date);

        System.out.println("java.sql.Date " + sqlDate);

        System.out.println("java.sql.Time " + time);

        System.out.println("java.sql.Timestamp " + TIME_STAMP);

        System.out.println("Is day light saving " + timeZone.inDaylightTime(date));

        System.out.println("***********************************************");

        timeZone = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_RAINYRIVER);

        TimeZone.setDefault(timeZone);

        System.out.println("long value " + TIME);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("Time Zone offset " + timeZone.getOffset(Calendar.ZONE_OFFSET) / MILLS_TO_HOURS);
        //System.out.println("DST offset " + timeZone.getDSTSavings());

        System.out.println("java.util.Date " + date);

        System.out.println("java.sql.Date " + sqlDate);

        System.out.println("java.sql.Time " + time);

        System.out.println("java.sql.Timestamp " + TIME_STAMP);

        System.out.println("Is day light saving " + timeZone.inDaylightTime(date));

        System.out.println("******************** End dstTest *************************");

    }

    private static void dstTestCalendar(String timeZoneId) {

        System.out.println("******************** Begin dstTestCalendar *************************");

        System.out.println("long value " + TIME);
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(TIME);

        System.out.println(cal.getTimeZone().getDefault().getDisplayName() + ":" + cal.getTimeZone().getDefault().getID());
        System.out.println("DST:" + cal.getTimeZone().getDSTSavings());
        System.out.println("offset:" + cal.getTimeZone().getOffset(TIME));

        TimeZone timeZoneNew = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_TORONTO);

        cal.setTimeZone(timeZoneNew);
        System.out.println(cal.getTimeZone().getDisplayName() + ":" + cal.getTimeZone().getID());
        System.out.println("DST:" + cal.getTimeZone().getDSTSavings());
        System.out.println("offset:" + cal.getTimeZone().getOffset(TIME));

        timeZoneNew = TimeZone.getTimeZone(TIMEZONE_ID_US_PACIFIC);

        cal.setTimeZone(timeZoneNew);
        System.out.println(cal.getTimeZone().getDisplayName() + ":" + cal.getTimeZone().getID());
        System.out.println("DST:" + cal.getTimeZone().getDSTSavings());
        System.out.println("offset:" + cal.getTimeZone().getOffset(TIME));

        System.out.println("******************** Begin dstTestCalendar *************************");

    }

    private static void curatedDateTest(String timeZoneId) throws ParseException {

        System.out.println("******************** Begin curatedDateTest *************************");

        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);

        TimeZone.setDefault(timeZone);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        String dateValue = "1980-01-11 00:00:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        Date cdate = df.parse(dateValue);

        System.out.println("curate java date [YYYY-MM-DD]" + cdate);

        System.out.println("***********************************************");

        timeZone = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_TORONTO);

        TimeZone.setDefault(timeZone);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("curate java date [YYYY-MM-DD]" + cdate);

        System.out.println("***********************************************");

        timeZone = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_CHICAGO);

        TimeZone.setDefault(timeZone);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("curate java date [YYYY-MM-DD]" + cdate);

        System.out.println("******************** End curatedDateTest *************************");

    }

    private static boolean[] rangeDstTest(String inTime) {

        System.out.println("******************** Begin rangeDstTest *************************");

        boolean[] result = new boolean[2];

        Timestamp timestamp = Timestamp.valueOf(inTime);

        long longTime = timestamp.getTime();

        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_ID_CANADA_EASTERN);

        TimeZone.setDefault(timeZone);

        System.out.println("long value " + longTime);

        java.util.Date date = new java.util.Date(longTime);

        java.sql.Date sqlDate = new java.sql.Date(longTime);

        java.sql.Time time = new java.sql.Time(longTime);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("java.util.Date " + date);

        System.out.println("java.sql.Date " + sqlDate);

        System.out.println("java.sql.Time " + time);

        System.out.println("java.sql.Timestamp " + timestamp);

        result[0] = timeZone.inDaylightTime(date);

        System.out.println("java.sql.Timestamp " + timeZone.inDaylightTime(date));

        System.out.println("***********************************************");

        timeZone = TimeZone.getTimeZone(TIMEZONE_ID_US_EASTERN);

        TimeZone.setDefault(timeZone);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("java.util.Date " + date);

        System.out.println("java.sql.Date " + sqlDate);

        System.out.println("java.sql.Time " + time);

        System.out.println("java.sql.Timestamp " + timestamp);

        result[1] = timeZone.inDaylightTime(date);
        System.out.println("java.sql.Timestamp " + timeZone.inDaylightTime(date));

        System.out.println("******************** End rangeDstTest *************************");

        return result;
    }

    private static boolean[] rangeDstTest(long longTime) {

        System.out.println("******************** Begin rangeDstTest long *************************");

        boolean[] result = new boolean[2];

        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_TORONTO);

        TimeZone.setDefault(timeZone);

        System.out.println("long value " + longTime);

        java.util.Date date = new java.util.Date(longTime);

        java.sql.Date sqlDate = new java.sql.Date(longTime);

        java.sql.Time time = new java.sql.Time(longTime);

        java.sql.Timestamp timeStamp = new java.sql.Timestamp(longTime);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("java.util.Date " + date);

        System.out.println("java.sql.Date " + sqlDate);

        System.out.println("java.sql.Time " + time);

        System.out.println("java.sql.Timestamp " + timeStamp);

        result[0] = timeZone.inDaylightTime(date);

        System.out.println("java.sql.Timestamp " + timeZone.inDaylightTime(date));

        System.out.println("***********************************************");

        timeZone = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_NEWYORK);

        TimeZone.setDefault(timeZone);

        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());

        System.out.println("java.util.Date " + date);

        System.out.println("java.sql.Date " + sqlDate);

        System.out.println("java.sql.Time " + time);

        System.out.println("java.sql.Timestamp " + timeStamp);

        result[1] = timeZone.inDaylightTime(date);
        System.out.println("java.sql.Timestamp " + timeZone.inDaylightTime(date));

        System.out.println("******************** End rangeDstTest long*************************");

        return result;
    }
}
