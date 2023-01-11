/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.Business;

import java.util.ArrayList;
import java.util.Comparator;

import java.util.ArrayList;
import digitalmarketing.CustomerManagement.CustomerDirectory;
import digitalmarketing.MarketModel.ChannelDirectory;
import digitalmarketing.MarketModel.MarketDirectory;
import digitalmarketing.OrderManagement.MasterOrderList;
import digitalmarketing.ProductManagement.ProductSummary;
import digitalmarketing.ProductManagement.ProductsReport;
import digitalmarketing.ProductManagement.SolutionOfferDirectory;
import digitalmarketing.ProductManagement.AdsDirectory;
import digitalmarketing.Supplier.Supplier;
import digitalmarketing.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    MasterOrderList masterorderlist;
    SupplierDirectory suppliers;
    CustomerDirectory customers;
    ChannelDirectory channels;
    MarketDirectory markets;
    SolutionOfferDirectory solutions;
    AdsDirectory adsDirectory;

    public Business(String n) {
        name = n;
        masterorderlist = new MasterOrderList(this);
        suppliers = new SupplierDirectory(this);
        customers = new CustomerDirectory(this);
        channels = new ChannelDirectory(this);
        markets = new MarketDirectory(this);
        solutions = new SolutionOfferDirectory(this);
        adsDirectory = new AdsDirectory(this);
    }

    public int getSalesVolume() {
        return masterorderlist.getSalesVolume();

    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();

    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n) {

        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); //return size of the arraylist
        return i;
    }
    
    private void printTitle(){
        System.out.println("Business : " + name);
    }
    
    
    public void printSupplierDetails(){
        printTitle();
        suppliers.printSupplierList();
    }

    public MasterOrderList getMasterorderlist() {
        return masterorderlist;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customers;
    }
    
    public ArrayList<ProductSummary> getNBestPerformingProductsReport (int rank) {
        ArrayList<ProductSummary> productSummaryArrayList = new ArrayList<>();
        ArrayList<Supplier> supplierList = suppliers.getSuppliers();
        for (Supplier s : supplierList) {
            ProductsReport productsReport = getSupplierPerformanceReport(s.getName());
            productSummaryArrayList.addAll(productsReport.getProductsummarylist());
        }
        productSummaryArrayList.sort(new Comparator<ProductSummary>() {
            @Override
            public int compare(ProductSummary productSummary, ProductSummary t1) {
                return t1.getAcutalsalesvolume() - productSummary.getAcutalsalesvolume();
            }
        });
        ArrayList<ProductSummary> res = new ArrayList<>();
        rank = Math.min(rank, productSummaryArrayList.size());
        for (int i = 0; i < rank; i++) {
            res.add(productSummaryArrayList.get(i));
        }
        return res;
    }

    public ArrayList<Supplier> getNBestPerformingSuppliersReport (int rank) {
        ArrayList<Supplier> list = suppliers.getSuppliers();
        list.sort(new Comparator<Supplier>() {
            @Override
            public int compare(Supplier supplier, Supplier t1) {
                return t1.getSalesValume() - supplier.getSalesValume();
            }
        });
        ArrayList<Supplier> res = res = new ArrayList<>();
        rank = Math.min(rank, list.size());
        for (int i = 0; i < rank; i++) {
            res.add(list.get(i));
        }
        return res;
    }
    
    public ChannelDirectory getChannels() {
        return channels;
    }
    
    public MarketDirectory getMarkets() {
        return markets;
    }
    
    public SolutionOfferDirectory getSolutions() {
        return solutions;
    }
    
    public AdsDirectory getAdsDirectory() {
        return adsDirectory;
    }
    
    public void printSalesRevenues() {
        
    }
    
    public void printAllOrders(){
        printTitle();
        masterorderlist.printOrders();
    }
    
}
