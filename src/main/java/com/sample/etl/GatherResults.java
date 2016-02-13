package com.sample.etl;

import com.sample.etl.model.*;

import java.util.Comparator;
import java.util.List;

/**
 * Created by uilian.souza on 13/02/2016.
 */
public class GatherResults {


    /**
     *
     * @param file
     * @return
     */
    protected Report processResults(FileModel file){
        Report result = new Report();
        result.setFileName(file.fileName);
        result.setNumberOfCustomers(file.getCustomers().stream().distinct().count());
        result.setNumberOfSalesman(file.getSalesmen().stream().distinct().count());
        result.setIdMostExpensiveSale(findMostExpensiveSale(file.getSales()));
        result.setWorstSalesmanEver(findWorstSalesman(file.getSales()));

        return  result;
    }


    /**
     * Find out worst salesman based on the less valuable sale (price * amount) criteria.
     * @param sales
     * @return
     */
    private String findWorstSalesman(List<Sale> sales) {
        String result = "";
        Double value = 0.0;
        for (Sale s: sales) {
            for (Item item : s.getItems()) {
                if (result.isEmpty() || value > (item.getPrice() * item.getQuantity())) {
                    value = (item.getPrice() * item.getQuantity());
                    result = s.getSalesmanName();
                }
            }
        }
        return result;
    }

    /**
     * The criteria adopted to find out which is the most expensive sale is
     * which one generated the most value considering (price * amount).
     *
     * @param sales
     * @return
     */
    private Integer findMostExpensiveSale(List<Sale> sales) {
        Integer result = 0;
        final Comparator<Item> comp = (i1, i2) -> Double.compare( (i1.getPrice()*i1.getQuantity()), (i2.getPrice()*i2.getQuantity()));
        Double value = 0.0;
        for (Sale s: sales) {
            Item i = s.getItems().stream().max(comp).get();
            if ((i.getQuantity()*i.getPrice()) > value){
                result = s.getId();
            }
        }
        return result;
    }
}
