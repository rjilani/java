
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilani
 */
public class TraveseFiles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        final File folder = new File("C:\\Users\\rjilani\\Documents\\IdeaProjects\\DirectoryReader");
        List<String> result = new ArrayList<>();

        search(".*\\.java", folder, result);

        long total = 0;
        for (String s : result) {
            System.out.println(s);

            Stream<String> lines = Files.lines(Paths.get(s), Charset.defaultCharset());
            long numOfLines = lines.count();
            System.out.println(numOfLines);
            total = total + numOfLines;
        }

        System.out.println("total number of lines: " + String.valueOf(total));
        System.out.println("total number of files: " + String.valueOf(result.size()));
    }

    public static void search(final String pattern, final File folder, List<String> result) {
        for (final File f : folder.listFiles()) {

            if (f.isDirectory()) {
                search(pattern, f, result);
            }

            if (f.isFile()) {
                if (f.getName().matches(pattern)) {
                    result.add(f.getAbsolutePath());
                }
            }

        }
    }

}
