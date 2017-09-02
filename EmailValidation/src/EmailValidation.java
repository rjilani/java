import jodd.mail.EmailAddress;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class EmailValidation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String emailAddress = "a@a.com";
        System.out.println(isValidEmailAddress(emailAddress));

        EmailAddress email = new EmailAddress(emailAddress);
        System.out.println(email.isValid());
    }

    public static boolean isValidEmailAddress(String email) {

        final String consPeriods = ".."; //Not allowed in an email
        final String atSign = "@"; //local and domain part seperator, only allowed once
        final char period = '.'; //Period to disallow before domain seperator

        if (!email.contains(atSign) || ((email.contains(consPeriods) || email.charAt(email.indexOf(atSign) - 1) == period))) {
            return false;
        }
        String emailRegex = "^([0-9a-zA-Z-_]([-!#$%&'*+./=?^_`{|}~0-9a-zA-Z])*@([0-9a-zA-Z][-\\w]*[0-9a-zA-Z]\\.)+[a-zA-Z]{2,9})$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
        java.util.regex.Matcher m = p.matcher(email);

        return m.matches();
    }

}
