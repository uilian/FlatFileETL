package com.sample.etl;

import com.sample.etl.exception.WrongLineFormatException;
import com.sample.etl.model.FileModel;
import com.sample.etl.model.Report;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


public class FileProcessor {

    private static Properties props;

    public static void main(String... aArgs) throws IOException, InterruptedException {
        Utils log = new Utils();

        String fSep = "ç";
        String iSep = ",";
        String iDataSep ="-";

        try {
            props = FileProcessor.loadConfig();
            fSep = (String) props.get("fieldSeparator");
            iSep = (String) props.get("itemSeparator");
            iDataSep = (String) props.get("itemDataSeparator");
        } catch (Exception e){
            log.log(e.getMessage());
        }
        //String inPath = (String) props.get("dir.input");
        //String outPath = (String) props.get("dir.output");
        String outPath = System.getProperty("user.home")+"/data/out";
        String inPath = System.getProperty("user.home")+"/data/in";
        String donePath = System.getProperty("user.home")+"/data/done";

        log.log(" Processing ... ");
        while(true){
            try {
                FileETLManager parser = new FileETLManager(fSep,iSep,iDataSep);
                GatherResults gr = new GatherResults();
                File[] fList = parser.listInputFiles(inPath, ".dat");

                for (File f : fList ){
                    FileModel fileModel = parser.processLineByLine(f);
                    Report rep = gr.processResults(fileModel);
                    parser.createOutputFile(rep,outPath);
                    moveDoneFile(rep.getFileName(), inPath, donePath);
                }

            } catch (WrongLineFormatException e) {
                log.log(e);
            }
            Thread.sleep(2000);
        }
    }

    private static void moveDoneFile(String fileName, String origin, String target) throws IOException {
        String fFileName = target;
        File tmp = new File(target);
        tmp.mkdirs();
        Files.move(Paths.get(origin+"/"+fileName), Paths.get(target+"/"+fileName));
    }

    private static Properties loadConfig() throws IOException {
        Properties props = new Properties();
        InputStream in = new FileInputStream("resources/config.properties");
        props.load(in);
        in.close();
        return props;
    }
}
