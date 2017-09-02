
import java.text.SimpleDateFormat

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

def file = new File(/C:\Users\rjilan01\Documents\code_stats\time_info.csv/)

def outFile = new File(/date_info.csv/).withWriter {out ->
    out.writeLine("Date" + "," + "Canada_EST_DST" + "," + "US_EST_DST")

    file.eachLine {
        //println it
    
        def result = DSTTest.rangeDstTest(it);
    
        out.writeLine(it.replace(" ", "_") + "," + result[0] + "," + result[1])

    }

}

def start_date = new GregorianCalendar(1850,0,1)  
def end_date = new GregorianCalendar(2014,11,31)  

def outFileRange = new File(/date_info_range.csv/).withWriter {out ->
    out.writeLine("Date" + "," + "America_Toronto_DST" + "," + "America_New_York_DST")
    // loop trough the dates  
    
    TimeZone timeZone = TimeZone.getTimeZone("America/Toronto");

    TimeZone.setDefault(timeZone);
    
    (start_date.getTime()..end_date.getTime()).each() { date ->  
        //println date.toString() 
    
        def result = DSTTest.rangeDstTest(date.time);
        
        timeZone = TimeZone.getTimeZone("America/Toronto");
        TimeZone.setDefault(timeZone);
        
        if (result[0] != result[1]) {
            out.writeLine(date.toString() + "," + result[0] + "," + result[1])
        }
        
    }

}