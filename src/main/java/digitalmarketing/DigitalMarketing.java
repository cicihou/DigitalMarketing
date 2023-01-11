/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing;

import com.github.javafaker.Faker;
import digitalmarketing.Business.Business;
import digitalmarketing.CustomerManagement.CustomerDirectory;
import digitalmarketing.CustomerManagement.CustomerProfile;
import digitalmarketing.OrderManagement.MasterOrderList;
import digitalmarketing.OrderManagement.Order;
import digitalmarketing.Personnel.Person;
import digitalmarketing.ProductManagement.Product;
import digitalmarketing.ProductManagement.ProductCatalog;
import digitalmarketing.ProductManagement.ProductSummary;
import digitalmarketing.ProductManagement.ProductsReport;
import digitalmarketing.Supplier.Supplier;
import digitalmarketing.Supplier.SupplierDirectory;
import java.util.Scanner;
import java.util.Random;


/**
 *
 * @author kal bugrara
 */
public class DigitalMarketing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println();
        System.out.println("------------------------Xi HOU------------------------");
        
        Business costco = new Business("Costco Inc.");
        InitiateData init = new InitiateData();
        init.populateBusiness(costco);
        
        DataLoader dataLoader = new DataLoader(costco);
        
    }

}
