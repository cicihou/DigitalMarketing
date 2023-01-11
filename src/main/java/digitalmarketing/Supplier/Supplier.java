/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.Supplier;

import java.util.ArrayList;
import digitalmarketing.ProductManagement.Product;
import digitalmarketing.ProductManagement.ProductCatalog;
import digitalmarketing.ProductManagement.ProductSummary;
import digitalmarketing.ProductManagement.ProductsReport;

/**
 *
 * @author kal bugrara
 */
public class Supplier {

    SupplierDirectory directory;
    String name;
    ProductCatalog productcatalog;
    ProductsReport productsreport;
    int totalActualSalesVolume;
    int totalSalesValume;

    public Supplier(String n, SupplierDirectory d, String type) {
        name = n;
        productcatalog = new ProductCatalog(type, this);
        directory = d;

    }

    public ProductsReport prepareProductsReport() {

        productsreport = productcatalog.generatProductPerformanceReport();
        return productsreport;
    }

    public ArrayList<ProductSummary> getProductsAlwaysAboveTarget() {

        if (productsreport == null) {
            productsreport = prepareProductsReport();
        }
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public ProductCatalog getProductcatalog() {
        return productcatalog;
    }

    public int getTotalActualSalesVolume() {
        totalActualSalesVolume = 0;
        if (productsreport == null) {
            productsreport = prepareProductsReport();
        }
        for (ProductSummary summary: productsreport.getProductsummarylist()) {
            totalActualSalesVolume += summary.getAcutalsalesvolume();
        }
        return totalActualSalesVolume;
    }
    
    public int getSalesValume() {
        totalSalesValume = 0;
        if (productsreport == null) {
            productsreport = prepareProductsReport();
        }
        for (ProductSummary summary: productsreport.getProductsummarylist()) {
            totalSalesValume += summary.getSalesValume();
        }
        return totalSalesValume;
    }    

    public String getName() {
        return name;
    }
    //add supplier product ..

    //update supplier product ...

    public void printSupplierDetails() {
        System.out.println("----------------------------------------");
        System.out.println("Supplier : " + name);
        productcatalog.printDetails();
        
    }

    public Product newProduct(int fp, int cp, int tp, String n) {
        return productcatalog.newProduct(fp, cp, tp, n);
    }

}

