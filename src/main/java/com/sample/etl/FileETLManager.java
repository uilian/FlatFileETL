package com.sample.etl;

/**
 * Created by uilian on 13/02/16.
 */
import com.sample.etl.exception.WrongLineFormatException;
import com.sample.etl.model.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Scanner;

/**
 *  File reading and parsing.
 */
public class FileETLManager {
    static {
        Locale.setDefault(new Locale("en", "US"));
    }

    /**
     * @param fieldSeparator
     * @param itemSeparator
     * @param itemDataSeparator
     */
    public FileETLManager(String fieldSeparator, String itemSeparator, String itemDataSeparator){
        this.fieldSeparator = fieldSeparator;
        this.itemSeparator = itemSeparator;
        this.itemDataSeparator = itemDataSeparator;
    }


    /**
     * List of all files on a directory with a given file extension.
     *
     * @param inputDirectory
     * @param extension
     */
    public File[] listInputFiles(String inputDirectory, String extension){
        File dir = new File(inputDirectory);

        File [] files = dir.listFiles((dir1, name) -> {
            return name.endsWith(extension);
        });

        return files;
    }

    public final FileModel processLineByLine(File file) throws IOException, WrongLineFormatException {
        FileModel result = new FileModel();
        result.setFileName(file.getName());
        try (Scanner scanner =  new Scanner(file, ENCODING.name())){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                processLine(line, result);
            }
        }
        return result;
    }


    /**
     * Proccess a given line.
     *
     * @param line
     */
    protected void processLine(String line, FileModel fileModel) throws WrongLineFormatException {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(this.fieldSeparator);
        String type = "";

        if (scanner.hasNext()){
            type = scanner.next().trim();
            log(type);
        }

        ElementFactory ef = new ElementFactory();
        switch(type) {
            case SALESMAN:
                Salesman sm = ef.createSalesman(line, this.fieldSeparator);
                fileModel.getSalesmen().add(sm);
                log(sm);
                break;
            case CUSTOMER:
                Customer cs = ef.createCustomer(line, this.fieldSeparator);
                fileModel.getCustomers().add(cs);
                log(cs);
                break;
            case SALES:
                Sale s = ef.createSale(line, this.fieldSeparator,this.itemSeparator,this.itemDataSeparator);
                fileModel.getSales().add(s);
                log(s);
                break;
            default:
                break;
        }
    }

    public void createOutputFile(Report rep, String outPath) throws IOException {
        String fFileName = outPath + "/" + rep.getFileName().replace(".dat","") + ".done.dat";
        File tmp = new File(outPath);
        tmp.mkdirs();
        try (Writer out = new OutputStreamWriter(new FileOutputStream(fFileName), ENCODING.name())){
            out.write(
                String.join(
                    this.fieldSeparator,
                    rep.getNumberOfCustomers().toString(),
                    rep.getNumberOfSalesman().toString(),
                    rep.getIdMostExpensiveSale().toString(),
                    rep.getWorstSalesmanEver()
                )
            );
        }
    }


    private final static Charset ENCODING = StandardCharsets.UTF_8;

    private String fieldSeparator;
    private String itemSeparator;
    private String itemDataSeparator;
    private Report report;

    private static final String SALESMAN = "001", CUSTOMER = "002", SALES = "003";

    private static void log(Object aObject){
        System.out.println(String.valueOf(aObject));
    }
}