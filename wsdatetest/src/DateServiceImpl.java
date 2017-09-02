
import java.util.Date;
import javax.jws.WebService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */

@WebService(endpointInterface = "DateService", targetNamespace = "http://my.org/ns/")
public class DateServiceImpl implements DateService {

    @Override
    public String sendDate(Date date, String s) {
        System.out.println(date);
        System.out.println(s);
        return "\u00C0" + "\u00C8" + "\u00C2" + "\u00CA" +  "\u00D2" + "\u00D6" + "-ÀÈÂÊÒÖ" + "< > & ' \"";
    }
    
}
