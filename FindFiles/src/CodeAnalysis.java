
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
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
public class CodeAnalysis {

    public static void main(String[] args) throws IOException {

        try (Stream<Path> walk = Files.walk(Paths.get("C:\\Users\\rjilani\\Documents\\SonarQubeCodeAnalysis"))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".java")).collect(Collectors.toList()); //use .js, .jsp, .xml etc for the correspoding files extensions

            long total = 0;
            for (String s : result) {
                System.out.println(s);
                long numOfLines = 0;
                Path path = Paths.get(s);
                 
                List<String> contents = Files.readAllLines(path, StandardCharsets.ISO_8859_1);
                for (String content : contents) {//for each line of content in contents

                if( path.toString().endsWith(".java") ) {
                    if (!isIgnoreLine(content)) {
//                        System.out.println(content);// print the line
                        numOfLines++;
                    }
                 } else {
                    if (content.length() > 0) {
                         numOfLines++;
                    }
                    
                }
                    

                }
                total = total + numOfLines;
                System.out.println(numOfLines);
            }

            System.out.println("---------------------------------------------");
            System.out.println("total number of lines: " + String.valueOf(total));
            System.out.println("total number of files: " + String.valueOf(result.size()));

        }

    }

    /**
     * This method checks for Java comments, and blank line and return false so main program can ignore blank lines and Java comments
     * @param line
     * @return 
     */
    public static boolean isIgnoreLine(String line) {
        boolean ignoreLine = false;
        if (line.length() == 0 || line.trim().startsWith("/**") || line.trim().startsWith("/*")
                || line.trim().startsWith("*") || line.trim().startsWith("//")) {
            ignoreLine = true;
        }

        return ignoreLine;

    }
}
