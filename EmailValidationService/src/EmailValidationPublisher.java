
import javax.xml.ws.Endpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class EmailValidationPublisher {

    public static void main(String[] args) {

        //Endpoint.publish("http://127.0.0.1:9999/ws/date", new DateServiceImpl());
        String serverName = args[0];
        String port = args[1];

        //"40368-NB" + ":" + "9999"
        String wsUrl = "http://" + serverName + ":" + port + "/ws/email";

        Endpoint.publish(wsUrl, new EmailValidationServiceImpl());

        System.out.println("Server is published!");

        System.out.println(wsUrl + "?wsdl");

    }

}
