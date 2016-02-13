package com.sample.files;

/**
 * Created by uilian on 13/02/16.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class FileScanner {

    public static void main(String... aArgs) throws IOException {
        // modo 1
        Properties props = new Properties();
        InputStream in = new FileInputStream("resources/config.properties");
        props.load(in);
        in.close();
        String path1 = (String) props.get("dir.input");

        // modo2
        String path = System.getProperty("user.home");

        FileScanner parser = new FileScanner(path1+"sample-input.dat");
        parser.processLineByLine();
        log("Done.");
    }

    /**
         Constructor.
         @param pFileName existing file.
     */
    public FileScanner(String pFileName){
        fFilePath = Paths.get(pFileName);
    }

    public final void processLineByLine() throws IOException {
        try (Scanner scanner =  new Scanner(fFilePath, ENCODING.name())){
            while (scanner.hasNextLine()){
                processLine(scanner.nextLine());
            }
        }
    }

    /**
         Overridable method for processing lines in different ways.
     */
    protected void processLine(String line){
        //use a second Scanner to parse the content of each line
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter("ç");
        while (scanner.hasNext()){
            String value = scanner.next();
            log("Value: " + value.trim());
        }
        log("-------------------");
    }

    private final Path fFilePath;
    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static void log(Object aObject){
        System.out.println(String.valueOf(aObject));
    }
}