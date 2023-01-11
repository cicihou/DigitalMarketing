/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.ProductManagement;

import java.util.ArrayList;
import digitalmarketing.MarketModel.Market;
import digitalmarketing.OrderManagement.OrderItem;

/**
 *
 * @author kal bugrara
 */
public class SolutionOffer {
    ArrayList<Product> products;
    int price;
    Market market;
    String solutionName;
    ArrayList<OrderItem> orderItems;
    Ads ads;
    
    public SolutionOffer(Market m, String name, int price, Ads ads){
        market = m;
        market.addSolutionOffers(this);
        solutionName = name;
        products = new ArrayList<>();
        orderItems = new ArrayList<>();
        this.ads = ads;
        this.ads.addSolutionOffer(this);
        this.price = price;
    }
    
    public void addProduct(Product p){
        products.add(p);
    }
    
    public ArrayList<Product> getProducts(){
        return products;
    }
    
    public String getSolutionName() {
        return solutionName;
    }
    
    public void setPrice(int p){
        price = p; 
    }
    public int getPrice() {
        return price;
    }
    public Market getMarket() {
        return market;
    }
    public ArrayList<OrderItem> getOrderItems(){
        return orderItems;
    }
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }
    
    public Ads getAds() {
        return ads;
    }
    public int calculateSalesVolume() {
        int actualsalesvolume = 0;
        for (OrderItem item: orderItems) {
            actualsalesvolume += item.getOrderItemTotal();
        }
        return actualsalesvolume;
    }
    
    public int getPricePerformance() {
        int salesvalume = 0;
        for (OrderItem item: orderItems) {
            salesvalume += item.calculatePricePerformance();
        }
        return salesvalume;
    }
    
    public void printSolutionRevenue() {
        System.out.println(solutionName + " | " + calculateSalesVolume() + " | " + getPricePerformance());
    }
    
    public void printSolutionInfo() {
        System.out.println(solutionName + ": Special Offers for " + market.getMarketName() + 
                ", Solution Price: " + price + ", Ads: " + ads.getDescription() + ", " + ads.getPrice());
        
        for (Product product: products) {
            product.printDetails();
        }
    }
    
    public void printSolutionInfo(int index1, int index2) {
        System.out.println(solutionName + ": Special Offers for " + market.getMarketName() + 
                ", Solution Price: " + price + ", Ads: " + ads.getDescription() + ", " + ads.getPrice());
        int index = 0;
        for (Product product: products) {
            System.out.print(index1 + "-" + index2 + "-" + index + ": ");
            product.printDetails();
            index++;
        }
    }
}
