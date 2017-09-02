package classfinder;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;
import java.util.StringTokenizer;

public class ClassSearch {

    private String m_baseDirectory;
    private String m_classToFind;
    private int m_resultsCount = 0;
    public List<String> listOfClass = new ArrayList<String>();

    public ClassSearch() {
    }

    public String getListOfClass() {
        
        
        String listOfClass = "";
            for (String classname : this.listOfClass) {
                
                listOfClass = listOfClass + classname;
                
            }
            
            return listOfClass;
        
    }
    
    private void searchJarFile(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ZipInputStream zis = new ZipInputStream(bis);
            ZipEntry ze = null;

            while ((ze = zis.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                    continue;
                }

                if (ze.getName().indexOf(m_classToFind) != -1) {
                    System.out.println("  " + ze.getName()
                            + "\n    (inside " + filePath + ")");
                    listOfClass.add("  " + ze.getName()
                            + "\n    (inside " + filePath + ")");
                    m_resultsCount++;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
    }

    private void findHelper(File dir, int level) {
        int i;
        File[] subFiles;

        subFiles = dir.listFiles();

        if (subFiles == null) {
            return;
        }

        for (i = 0; i < subFiles.length; i++) {
            if (subFiles[i].isFile()) {
                if (isValidArchive(subFiles[i].getName())) {
                    // found a jar file, process it
                    searchJarFile(subFiles[i].getAbsolutePath());
                }
            } else if (subFiles[i].isDirectory()) {
                // directory, so recur
                findHelper(subFiles[i], level + 1);
            }
        }
    }

    private void searchClassPath(String classToFind) {
        String classPath = System.getProperty("java.class.path");
        System.out.println("Searching classpath: " + classPath);
        StringTokenizer st = new StringTokenizer(classPath, ";");

        m_classToFind = classToFind;

        while (st.hasMoreTokens()) {
            String jarFileName = st.nextToken();
            if (jarFileName != null
                    && isValidArchive(jarFileName)) {
                searchJarFile(jarFileName);
            }
        }
    }

    public void findClass(String baseDir, String classToFind) {
        System.out.println("SEARCHING IN: " + baseDir);
        m_baseDirectory = baseDir;
        m_classToFind = classToFind;
        m_classToFind = m_classToFind.replaceAll("\\.", "/");

        File start = new File(m_baseDirectory);

        System.out.println("SEARCHING FOR: " + m_classToFind);
        System.out.println("\nSEARCH RESULTS:");

        findHelper(start, 1);

        if (m_resultsCount == 0) {
            System.out.println("No results.");
        }
    }

    private boolean isValidArchive(String archiveFileName) { 
        
        if (archiveFileName.indexOf(".jar") != -1
                || archiveFileName.indexOf(".war") != -1  
                ||  archiveFileName.indexOf(".ear") !=1){
            return true;
        } else {
            return false;
        }
     
        
    }

    public static void main(String args[]) {
        if (args.length < 1 || args.length > 2) {
            System.out.println("Incorrect program usage");
            System.out.println("  java ClassSearch <base directory>"
                    + " <class to find>\n");
            System.out.println("    searches all jar files beneath base"
                    + " directory for class\n");
            System.out.println("");
            System.out.println("  java ClassSearch <class to find>\n");
            System.out.println("    searches all jar files in classpath"
                    + " for class\n");
            System.exit(1);
        }

        ClassSearch cs = new ClassSearch();

        if (args.length == 1) {
            cs.searchClassPath(args[0]);
        } else if (args.length == 2) {
            cs.findClass(args[0], args[1]);
        }
    }
}
