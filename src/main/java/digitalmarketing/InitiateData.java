/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package digitalmarketing;

import digitalmarketing.Business.Business;
import digitalmarketing.CustomerManagement.CustomerDirectory;
import digitalmarketing.CustomerManagement.CustomerProfile;
import digitalmarketing.MarketModel.Channel;
import digitalmarketing.MarketModel.ChannelDirectory;
import digitalmarketing.MarketModel.Market;
import digitalmarketing.MarketModel.MarketDirectory;
import digitalmarketing.Personnel.Person;
import digitalmarketing.ProductManagement.*;
import digitalmarketing.Supplier.Supplier;
import digitalmarketing.Supplier.SupplierDirectory;
import digitalmarketing.ProductManagement.ProductCatalog;
import digitalmarketing.ProductManagement.Product;
import digitalmarketing.ProductManagement.ProductSummary;
import digitalmarketing.ProductManagement.ProductsReport;
import digitalmarketing.OrderManagement.MasterOrderList;
import digitalmarketing.OrderManagement.Order;

import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author alelashvili
 */
class InitiateData {
    
    private Random random = new Random();
    private Business business;
    
    InitiateData(){}
//    private ArrayList<Channel> channelArrayList = new ArrayList<>();
//    private ArrayList<SolutionOffer> solutionOfferArrayList = new ArrayList<>();
    
    public void populateBusiness(Business b){
        
        business = b;
        
         // Add Supplier(2 suppliers)        
         SupplierDirectory sd = b.getSupplierDirectory();
         populateSupplierDirectory(sd, 2);
         
         // Add 3 product for every supplier
         for (int i=0; i < sd.getSuppliers().size(); i++){
             Supplier supplier = b.getSupplierDirectory().findSupplierByIndex(i);
             populateProductCatalog(supplier, 3);
         }
         
         // Add Customers with different type
         CustomerDirectory cd = b.getCustomerDirectory();
         populateCustomerDirectory(cd, 3);
         
         // Add 2 Channels
         ChannelDirectory channelDirectory = b.getChannels();
         initiateChannel(channelDirectory);
         
         // Add 3 Markets
         MarketDirectory marketDirectory = b.getMarkets();
         initiateMarket(marketDirectory, channelDirectory);
         
         // Add solution with default ads
         SolutionOfferDirectory solutionOfferDirectory = b.getSolutions();
         AdsDirectory adsDirectory = b.getAdsDirectory();
         initiateSolution(marketDirectory, solutionOfferDirectory, adsDirectory);
         
         // Generate Order
         CustomerProfile currentCustomer = cd.findCustomer("General Customer");
         showSearchResult(currentCustomer, channelDirectory.findChannelByIndex(0));
         
    }
    
    public void populateProductCatalog(Supplier s, int count){
        ProductCatalog pc = s.getProductcatalog();
        for (int i=0; i<count; i++){
            int floorPrice = getRandomPrice(70, 90);
            int ceilingPrice = getRandomPrice(90, 110);
            int targetPrice = getRandomPrice(110, 130);
            pc.newProduct(floorPrice, ceilingPrice, targetPrice, s.getName() + "-product" + i);
        }
    }
    
    public void populateSupplierDirectory(SupplierDirectory sd, int count){
        for (int i=0; i<count; i++){
            String supplierName = "supplier" + i;
            sd.newSupplier(supplierName, "General");
        }
    }
    
    public void populateCustomerDirectory(CustomerDirectory cd, int count){
        // generate customers with type
        String[] customerType = {"General", "Student", "VIP"};
        for (int i=0; i<count; i++){
            String customerName = customerType[i] + " Customer";
            cd.newCustomerProfile(new Person(customerName), customerType[i]);
        }
    }
    
    public int getRandomPrice(int lower, int upper){
        int result = 0;
        result = lower + random.nextInt(upper - lower);
        return result;
    }
    
    public void initiateChannel(ChannelDirectory channelDirectory) {
        for (int i = 0; i < 2; i++) {
            channelDirectory.newChannel("Channel-" + i);
        }
    }
    
    public void initiateMarket(MarketDirectory marketDirectory, ChannelDirectory channelDirectory) {
        
        // when market has specific characteristc, it means the specific group of people can see this market
        // e.g. Amazon is for general; Tmall is for student && general; Walmart is only for VIP
        Market market1 = marketDirectory.newMarket("Amazon", "General");
        Market market2 = marketDirectory.newMarket("Tmall ", "Student");
        market2.addCharacteristic("General");
        Market market3 = marketDirectory.newMarket("Market-3", "VIP");
        
        // channel and market is many-to-many relationship
        market1.addChannel(channelDirectory.findChannelByIndex(0));
        market2.addChannel(channelDirectory.findChannelByIndex(0));
        
        market1.addChannel(channelDirectory.findChannelByIndex(1));
        market3.addChannel(channelDirectory.findChannelByIndex(1));
    }
    
    public void initiateSolution(MarketDirectory marketDirectory, SolutionOfferDirectory solutionOfferDirectory, AdsDirectory adsDirectory) {
        // find the supplier in order to find & add the products to the solution offer
        SupplierDirectory sd = business.getSupplierDirectory();
        Supplier supplier0 = sd.findSupplierByIndex(0);
        Supplier supplier1 = business.getSupplierDirectory().findSupplierByIndex(1);
        String[] adsInfo = {"discount for Christmas", "discount for Thanksgiving", "discount for Valentine's Day"};
        
        // generate solution with market/solution_name/price/ads discount
        for (int i=0; i<3; i++){
            Ads ads = adsDirectory.newAds(adsInfo[i], 10);
            Market m = marketDirectory.getMarketArrayList().get(i);
            SolutionOffer solutionOffer = solutionOfferDirectory.newSolutionOffer(m, "Solution-"+i, 90 + 5 * i, ads);
            solutionOffer.addProduct(supplier0.getProductcatalog().findProductByIndex(i));
            solutionOffer.addProduct(supplier1.getProductcatalog().findProductByIndex(i));
        }
    }
    
    public void showSearchResult(CustomerProfile customerProfile, Channel channel) {
        if (customerProfile != null) {
            // order
            MasterOrderList orderList = business.getMasterorderlist();
            Order order = orderList.newOrder(customerProfile);
            
            String currentType = customerProfile.getCustomerType();
            ArrayList<Market> list = channel.getMarkets();
            
            // find according market via customer type
            for (Market market: list) {
                if (market.getCharacteristics().contains(currentType)) {
                    // find solutions via markets
                    for (SolutionOffer solutionOffer: market.getSolutionOffers()) {
                        // add solution products to the order
                        for (Product product: solutionOffer.getProducts()){
                            order.newOrderItem(product, 1 + random.nextInt(5), solutionOffer, channel);
                        }
                    }
                }
            }
        }
    }
    
}
