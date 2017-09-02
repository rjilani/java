
import java.util.Date;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rjilan01
 */

@WebService(targetNamespace = "http://teranet.ca/ns/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DateService {
    
    String sendDate(Date date, String s);
    
}
