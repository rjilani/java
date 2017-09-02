
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.xml.datatype.XMLGregorianCalendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class WSDateClient {

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
    
    
    
    static {
        TimeZone.setDefault(TimeZone.getTimeZone(TIMEZONE_ID_AMERICA_TORONTO));
    }
    
    private static final Timestamp TIME_STAMP = Timestamp.valueOf("1941-11-21 00:50:00.0");
    private static final long TIME = TIME_STAMP.getTime();

    public static void main(String[] args) {
        // TODO code application logic here
        
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(TIME);

        XMLGregorianCalendar date = new XMLGregorianCalendarImpl(cal);
        
        System.out.println(cal.getTime());

        String response = sendDate(date);

        System.out.println(response);
    }

    private static String sendDate(javax.xml.datatype.XMLGregorianCalendar date) {
        String poundSign = "\u00C0";
        org.my.ns.DateServiceImplService service = new org.my.ns.DateServiceImplService();
        org.my.ns.DateService port = service.getDateServiceImplPort();
        return port.sendDate(date, poundSign);
    }
    
    

}
