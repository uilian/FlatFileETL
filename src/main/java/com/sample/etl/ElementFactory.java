package com.sample.etl;

import com.sample.etl.exception.WrongLineFormatException;
import com.sample.etl.model.Customer;
import com.sample.etl.model.Item;
import com.sample.etl.model.Sale;
import com.sample.etl.model.Salesman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class ElementFactory {


    /**
     *
     * @param line
     * @param delimiter
     * @return
     * @throws WrongLineFormatException
     */
    protected Salesman createSalesman(String line, String delimiter) throws WrongLineFormatException {
        Salesman sm = new Salesman();
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(delimiter);
        try {
            while (scanner.hasNext()) {
                // skip type
                scanner.next();
                sm.setCpf(scanner.next());
                sm.setName(scanner.next());
                sm.setSalary(scanner.nextDouble());
            }
        } catch (Exception e){
            throw new WrongLineFormatException(e.getMessage());
        }
        return sm;
    }

    /**
     *
     * @param line
     * @param delimiter
     * @return
     * @throws WrongLineFormatException
     */
    protected Customer createCustomer(String line, String delimiter) throws WrongLineFormatException{
        Customer cm = new Customer();
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(delimiter);
        try {
            while (scanner.hasNext()) {
                // skip type
                scanner.next();
                cm.setCnpj(scanner.next());
                cm.setName(scanner.next());
                cm.setBusinessArea(scanner.next());
            }
        } catch (Exception e){
            throw new WrongLineFormatException(e.getMessage());
        }
        return cm;
    }

    /**
     *
     * @param line
     * @param fDel
     * @param iDel
     * @param isDel
     * @return
     * @throws WrongLineFormatException
     */
    protected Sale createSale(String line, String fDel, String iDel, String isDel) throws WrongLineFormatException {
        Sale cm = new Sale();
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(fDel);
        try {
            while (scanner.hasNext()) {
                // skip type
                scanner.next();
                cm.setId(scanner.nextInt());
                cm.setItems(createItems(scanner.next().replace("[","").replace("]",""), iDel, isDel));
                cm.setSalesmanName(scanner.next());
            }
        } catch (Exception e){
            throw new WrongLineFormatException(e.getMessage());
        }
        return cm;
    }

    /**
     *
     * @param itemLine
     * @param listDelimiter
     * @param itemDelimiter
     * @return
     * @throws WrongLineFormatException
     */
    protected List<Item> createItems(String itemLine, String listDelimiter, String itemDelimiter) throws WrongLineFormatException{
        List<Item> res = new ArrayList<>();
        Scanner scanner = new Scanner(itemLine);
        scanner.useDelimiter(listDelimiter);
        try {
            while (scanner.hasNext()) {
                Scanner sDash = new Scanner(scanner.next());
                sDash.useDelimiter(itemDelimiter);
                while (sDash.hasNext()) {
                    Item item = new Item();
                    item.setId(sDash.nextInt());
                    item.setQuantity(sDash.nextDouble());
                    item.setPrice(sDash.nextDouble());
                    res.add(item);
                }
            }
        } catch (Exception e){
            throw new WrongLineFormatException(e.getMessage());
        }
        return res;
    }

}
