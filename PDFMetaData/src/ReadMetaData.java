



import com.datalogics.PDFL.Document;
import com.datalogics.PDFL.Library;
import com.datalogics.PDFL.PDFDict;
import com.datalogics.PDFL.PDFObject;
import com.datalogics.PDFL.SaveFlags;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.HashMap;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class ReadMetaData {

    public static void main(String[] args) throws IOException {
       
       
       
       System.out.println("ListInfo sample:");

    	Library lib = new Library();

		try {
	        
	       	Document doc = new Document("C:\\Users\\rjilan01\\Documents\\projects\\Incidents\\053023013\\pdf\\RM74883.pdf");
	       	
	       	System.out.println("Opened a document.");
	       	
	       	

	       	System.out.println("Document Title " + doc.getTitle());
	       	
	       	System.out.println("Document Subject " + doc.getSubject());
	       
	       	
	       	System.out.println("Document Author " + doc.getAuthor());
	       	
	       	
	       	System.out.println("Document Keywords " + doc.getCompressionLevel());
	       	
	       	
	       	System.out.println("Document Creator " + doc.getCreator());
	       
	       	
	       	System.out.println("Document Producer " + doc.getXMPMetadata());
	       
                java.util.List<PDFObject> list = doc.getInfoDict().getKeys();
                
                System.out.println(list.toString());


            System.out.println("Writing these changes to a new file named ListInfo-out.pdf");

            doc.save(EnumSet.of(SaveFlags.FULL, SaveFlags.LINEARIZED), "ListInfo-out.pdf");
		}
		finally {
			lib.delete();
		}
	}
       
    }

