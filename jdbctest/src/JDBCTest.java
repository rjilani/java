/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCTest {

    /**
     * @param args the command line arguments
     */
    static String INSERT_RECORD = "insert into TEST_DATE(id, zone_info, "
            + "dt, ts3, ts6) values(?, ?, ?, ?, ?)";
    static String GET_RECORD_Test_Date = "select ZONE_INFO, DT, "
            + "TS3, TS6 from TEST_DATE where id = ?";

    static String GET_RECORD_TEST_DATE_TZ = "select ZONE_INFO, DT, "
            + "TS3, TS6, TSWTZ, TSWLTZ from TEST_DATE_TZ where id = ?";

    private static final String TIMEZONE_ID_AMERICA_NEWYORK = "America/New_York";
    private static final String TIMEZONE_ID_AMERICA_TORONTO = "America/Toronto";
    private static final String TIMEZONE_ID_AMERICA_CHICAGO = "America/Chicago";
    private static final String TIMEZONE_ID_CANADA_EASTERN = "Canada/Eastern";
    private static final String TIMEZONE_ID_US_EASTERN = "US/Eastern";
    private static final String TIMEZONE_ID_US_CENTRAL = "US/Central";
    private static final String TIMEZONE_ID_Canada_CENTRAL = "Canada/Central";
    private static final String TIMEZONE_ID_US_PACIFIC = "US/Pacific";

    public static void main(String[] args) throws SQLException, IOException, FileNotFoundException, ClassNotFoundException {

       java.util.Date date = new java.util.Date();

//        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_NEWYORK);
//        TimeZone.setDefault(timeZone);
//        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());
//        System.out.println(date.getTime());
//        insertRecord(10, TimeZone.getDefault().getID(), date);
//
//        timeZone = TimeZone.getTimeZone(TIMEZONE_ID_US_CENTRAL);
//        TimeZone.setDefault(timeZone);
//        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());
//        System.out.println(date.getTime());
//        insertRecord(12, TimeZone.getDefault().getID(), date);
//
//        timeZone = TimeZone.getTimeZone(TIMEZONE_ID_US_PACIFIC);
//        TimeZone.setDefault(timeZone);
//        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());
//        System.out.println(date.getTime());
//        insertRecord(14, TimeZone.getDefault().getID(), date);
//        selectRecord(14);
        selectRecordWithTimeZone(1);
//        readDate();
    }

    public static void insertRecord(int index, String timeZone, java.util.Date date) throws SQLException {

        System.out.println("---------------------------- Begin insertRecord ----------------------------");
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(INSERT_RECORD);
            pstmt.setInt(1, index);
            pstmt.setString(2, timeZone);

            long t = date.getTime();
            java.sql.Date sqlDate = new java.sql.Date(t);
            java.sql.Time sqlTime = new java.sql.Time(t);
            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(t);
            System.out.println("Date=" + date);
            System.out.println("sqlDate=" + sqlDate);
            System.out.println("sqlTime=" + sqlTime);
            System.out.println("sqlTimestamp=" + sqlTimestamp);
            pstmt.setDate(3, sqlDate);
            pstmt.setTimestamp(4, sqlTimestamp);
            pstmt.setTimestamp(5, sqlTimestamp);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert the record.");
        } finally {
            pstmt.close();
            conn.close();
        }

        System.out.println("---------------------------- End insertRecord ----------------------------");
    }

    public static Connection getConnection() throws Exception {
        String driver = "oracle.jdbc.driver.OracleDriver";
        //String url = "jdbc:oracle:thin:@//mrkpoldbuxut02:4035/ELR01P";
        String url = "jdbc:oracle:thin:@//mssctsdbuxdv01:3031/CSP01D";
        Class.forName(driver);
        return DriverManager.getConnection(url, "rjilan01", "present_2014");
        //return DriverManager.getConnection(url, "rjilan01", "Teranet_2014");
    }

    public static void selectRecord(int index) throws SQLException {

        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_ID_US_PACIFIC);
        TimeZone.setDefault(timeZone);

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(GET_RECORD_Test_Date);
            pstmt.setInt(1, index);
            rs = pstmt.executeQuery();

            FileOutputStream f = new FileOutputStream("tmp");
            ObjectOutput s = new ObjectOutputStream(f);

            while (rs.next()) {
                String iTimeZone = rs.getString(1);
                java.sql.Date date = rs.getDate(2);
                java.sql.Timestamp dbSqlTimestamp3 = rs.getTimestamp(3);
                java.sql.Timestamp dbSqlTimestamp4 = rs.getTimestamp(4);

                //System.out.println("TimeZone=" + iTimeZone);
                System.out.println("dbSqlDate=" + date);
                s.writeObject(date);
                System.out.println("dbSqlTimestamp3=" + dbSqlTimestamp3);
                System.out.println("dbSqlTimestamp4=" + dbSqlTimestamp4);
                s.writeObject(dbSqlTimestamp3);
                System.out.println("long ms=" + dbSqlTimestamp3.getTime());

                /*System.out.println("----------------------------  ----------------------------");

                 GregorianCalendar cal = new GregorianCalendar();
                 cal.setTimeZone(timeZone);
                 cal.setTimeInMillis(dbSqlTimestamp3.getTime());
                 System.out.println(cal.getTimeZone().getDisplayName() + ":" + cal.getTimeZone().getID());
                 System.out.println("Date= " + cal.getTime());
                 System.out.println("----------------------------  ----------------------------");

                 timeZone = TimeZone.getTimeZone(TIMEZONE_ID_US_EASTERN);
                 TimeZone.setDefault(timeZone);
                 System.out.println("Date= " + cal.getTime());  */
            }

            s.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert the record.");
        } finally {
            pstmt.close();
            conn.close();
        }
    }

    public static void selectRecordWithTimeZone(int index) throws SQLException {

        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_NEWYORK);
        TimeZone.setDefault(timeZone);

        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(GET_RECORD_TEST_DATE_TZ);
            pstmt.setInt(1, index);
            rs = pstmt.executeQuery();

            FileOutputStream f = new FileOutputStream("tmp");
            ObjectOutput s = new ObjectOutputStream(f);

            while (rs.next()) {
                String iTimeZone = rs.getString(1);
                java.sql.Date date = rs.getDate(2);
                java.sql.Timestamp dbSqlTimestamp3 = rs.getTimestamp(3);
                java.sql.Timestamp dbSqlTimestamp4 = rs.getTimestamp(4);

                java.sql.Timestamp dbSqlTimestampWTZ = rs.getTimestamp(5);
                java.sql.Timestamp dbSqlTimestampWLTZ = rs.getTimestamp(6);

                System.out.println("TimeZone=" + iTimeZone);
                System.out.println("dbSqlDate=" + date);
                s.writeObject(date);
                System.out.println("dbSqlTimestamp3=" + dbSqlTimestamp3);
                System.out.println("dbSqlTimestamp4=" + dbSqlTimestamp4);
                System.out.println("dbSqlTimestampWTZ=" + dbSqlTimestampWTZ);
                System.out.println("dbSqlTimestampWLTZ=" + dbSqlTimestampWLTZ);

                s.writeObject(dbSqlTimestamp3);
                System.out.println("long ms=" + dbSqlTimestamp3.getTime());

                /*System.out.println("----------------------------  ----------------------------");

                 GregorianCalendar cal = new GregorianCalendar();
                 cal.setTimeZone(timeZone);
                 cal.setTimeInMillis(dbSqlTimestamp3.getTime());
                 System.out.println(cal.getTimeZone().getDisplayName() + ":" + cal.getTimeZone().getID());
                 System.out.println("Date= " + cal.getTime());
                 System.out.println("----------------------------  ----------------------------");

                 timeZone = TimeZone.getTimeZone(TIMEZONE_ID_US_EASTERN);
                 TimeZone.setDefault(timeZone);
                 System.out.println("Date= " + cal.getTime());*/
            }

            s.flush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert the record.");
        } finally {
            pstmt.close();
            conn.close();
        }
    }

    public static void readDate() throws FileNotFoundException, IOException, ClassNotFoundException {
        TimeZone timeZone = TimeZone.getTimeZone(TIMEZONE_ID_US_PACIFIC);
        TimeZone.setDefault(timeZone);
        System.out.println(TimeZone.getDefault().getDisplayName() + ":" + TimeZone.getDefault().getID());
        FileInputStream in = new FileInputStream("tmp");
        ObjectInputStream se = new ObjectInputStream(in);
        Date date = (Date) se.readObject();
        java.sql.Timestamp sqlTimestamp = (java.sql.Timestamp) se.readObject();

        System.out.println("java.util.Date" + " " + date);
        System.out.println("java.sql.Timestamp" + " " + sqlTimestamp);
        System.out.println("long date" + " " + date.getTime());
        System.out.println("long timestamp" + " " + sqlTimestamp.getTime());

        date = (Date) se.readObject();
        sqlTimestamp = (java.sql.Timestamp) se.readObject();

        System.out.println("java.util.Date" + " " + date);
        System.out.println("java.sql.Timestamp" + " " + sqlTimestamp);
        System.out.println("long date" + " " + date.getTime());
        System.out.println("long timestamp" + " " + sqlTimestamp.getTime());

        date = (Date) se.readObject();
        sqlTimestamp = (java.sql.Timestamp) se.readObject();

        System.out.println("java.util.Date" + " " + date);
        System.out.println("java.sql.Timestamp" + " " + sqlTimestamp);
        System.out.println("long date" + " " + date.getTime());
        System.out.println("long timestamp" + " " + sqlTimestamp.getTime());
    }
}
