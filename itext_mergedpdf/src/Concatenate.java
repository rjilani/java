/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Concatenate {

    /**
     * The resulting PDF file.
     */
    public static String RESULT = "./concatenated.pdf";
    public final String dir = System.getProperty("user.dir");
    final static Logger logger = Logger.getLogger(Concatenate.class);

    public Concatenate() {

        PropertyConfigurator.configure(dir + "/config/log4j.properties");
    }

    /**
     * Main method.
     *
     * @param args no arguments needed
     * @throws DocumentException
     * @throws IOException
     * @throws SQLException
     */
    public void concatenate(List<String> listOfFiles, String destDir, boolean streaming)
            throws IOException, DocumentException, SQLException, InterruptedException {
        // using previous examples to create PDFs

        if (destDir != null) {
            RESULT = destDir + "/concatenated.pdf";

        }

        logger.info("Destination dir =" + destDir);

        logger.info("Merging " + listOfFiles.size() + " files");

        //Thread.sleep(10000);
        String[] files = new String[listOfFiles.size()];

        int j = 0;

        for (String item : listOfFiles) {

            files[j] = item;
            j++;
            logger.info(item);
        }
        // step 1
        Document document = new Document();
        // step 2
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(RESULT));
        // step 3
        document.open();
        // step 4
        PdfReader reader;
        int n;
        // loop over the documents you want to concatenate
        for (int i = 0; i < files.length; i++) {
            if (streaming) {
                logger.info("streaming");
                reader = new PdfReader(new RandomAccessFileOrArray(files[i]), null);

            } else {
                reader = new PdfReader(files[i]);
            }

            // loop over the pages in that document
            n = reader.getNumberOfPages();
            for (int page = 0; page < n;) {
                copy.addPage(copy.getImportedPage(reader, ++page));
            }
            copy.freeReader(reader);
        }
        // step 5
        document.close();

        logger.info("Files Merged");
    }
}
