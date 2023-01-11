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
import digitalmarketing.OrderManagement.MasterOrderList;
import digitalmarketing.OrderManagement.Order;

import java.util.Scanner;

/**
 *
 * @author alelashvili
 */
public class DataLoader {

    private Business business;
    private SupplierDirectory supplierDirectory;
    private CustomerDirectory customerDirectory;
    private ChannelDirectory channelDirectory;
    private MarketDirectory marketDirectory;
    private SolutionOfferDirectory solutionOfferDirectory;
    private AdsDirectory adsDirectory;
    
    public DataLoader(Business b){
        business = b;
        supplierDirectory = b.getSupplierDirectory();
        customerDirectory = b.getCustomerDirectory();
        channelDirectory = b.getChannels();
        marketDirectory = b.getMarkets();
        solutionOfferDirectory = b.getSolutions();
        adsDirectory = b.getAdsDirectory();
        
        showMainMenu();
    }
    
    public void showMainMenu() {
        System.out.println("-------------------------------Digital Market Menu-------------------------");
        System.out.println("   1. Check the business info     2. Add a Supplier     3. Add a Product   ");
        System.out.println("   4. Add a Customer              5. Add a Channel      6. Add a Market    ");
        System.out.println("   7. Market Channel Assignment   8. Add a Ads          9. Add a Solution  ");
        System.out.println("   10. Add Product To Solution    11. Search products ");
        System.out.println("   12. Show All Orders            13. Show Reports ");
        System.out.println("Please enter options: ");
        
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        
        switch (message) {
            case "1": showAllInfo(); break;
            case "2": generateSupplier(); break;
            case "3": addProductToSupplier(); break;
            case "4": generateCustomer(); break;
            case "5": generateChannel(); break;
            case "6": generateMarket(); break;
            case "7": marketChannelAssignment(); break;
            case "8": generateAds(); break;
            case "9": generateSolution(); break;
            case "10": addProductToSolution(); break;
            case "11": showProductList(); break;
            case "12": showSalesOrder(); break;
            case "13": showReports(); break;
            default: showMainMenu();
        }
        
    }
    
    public void showAllInfo() {
        supplierDirectory.printSupplierList();
        customerDirectory.printCustomerNames();
        channelDirectory.printChannelInfo();
        marketDirectory.printMarketInfo();
        solutionOfferDirectory.printSolutionInfo();
        adsDirectory.printAdsInfo();
        System.out.println();
        System.out.println();
        showMainMenu();
    }
    
    public void showSalesOrder() {
        System.out.println("------------------------Sales Orders------------------------");
        business.printAllOrders();
        
        System.out.println();
        showMainMenu();
    }

    public void generateSupplier() {
        System.out.println("Please enter Name and Type");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String type = scanner.nextLine();
        System.out.println(name);
        System.out.println(type);
        supplierDirectory.newSupplier(name, type);
        supplierDirectory.printSupplierList();
        System.out.println();
        showMainMenu();
    }
    
    public void addProductToSupplier() {
        // when you wanna add product, you'll need to add them to an existed supplier
        System.out.println("Please enter SupplierName, ProductName, FloorPrice, CeilingPrice, TargetPrice: ");
        Scanner scanner = new Scanner(System.in);
        String supplierName = scanner.nextLine();
        String productName = scanner.nextLine();
        int floorPrice = scanner.nextInt();
        int ceilingPrice = scanner.nextInt();
        int targetPrice = scanner.nextInt();
        
        Supplier supplier = supplierDirectory.findSupplier(supplierName);
        
        // if the supplier not exist, not adding products and return to the menu
        if (supplier != null) {
            ProductCatalog pc = supplier.getProductcatalog();
            pc.newProduct(floorPrice, ceilingPrice, targetPrice, productName);
            supplierDirectory.printSupplierList();
        } else {
            System.out.println("Supplier" + supplierName + "does not exist");
        }
        System.out.println();
        showMainMenu();
    }
    
    public void generateCustomer() {
        System.out.println("Please enter Name and Type: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String type = scanner.nextLine();
        customerDirectory.newCustomerProfile(new Person(name), type);
        customerDirectory.printCustomerNames();
        System.out.println();
        showMainMenu();
    }
    
    public void generateChannel() {
        System.out.println("Please enter Channel Name: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        channelDirectory.newChannel(name);
        channelDirectory.printChannelInfo();
        System.out.println();
        showMainMenu();
    }
    
    public void generateMarket() {
        System.out.println("Please enter Name and Characteristic: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String characteristic = scanner.nextLine();
        marketDirectory.newMarket(name, characteristic);
        marketDirectory.printMarketInfo();
        System.out.println();
        showMainMenu();
    }
    
    public void marketChannelAssignment() {
        // how to make a relationship between market and channel
        System.out.println("Please enter Market Name and Channel Name: ");
        Scanner scanner = new Scanner(System.in);
        marketDirectory.printMarketInfo();
        System.out.println("Please enter Market Name: ");
        String marketName = scanner.nextLine();
        channelDirectory.printChannelInfo();
        System.out.println("Please enter Channel Name: ");
        String channelName = scanner.nextLine();
        Channel channel = channelDirectory.findChannelById(channelName);
        Market market = marketDirectory.findMarketById(marketName);
        if (channel != null && market != null) {
            market.addChannel(channel);
            channel.printAllMarket();
        } else {
            System.out.println("Market or Channel " + "does not exist");
        }
        System.out.println();
        showMainMenu();
    }
    
    public void generateAds() {
        // generate ads for promotion(apply for discount)
        System.out.println("Please enter Description and Price: ");
        Scanner scanner = new Scanner(System.in);
        String description = scanner.nextLine();
        int price = scanner.nextInt();
        adsDirectory.newAds(description, price);
        adsDirectory.printAdsInfo();
        System.out.println();
        showMainMenu();
    }
    
    public void generateSolution() {
        // generate a solution with name/market/ads/price
        System.out.println("Please enter SolutionName, MarketName, AdsDescription, Price: ");
        Scanner scanner = new Scanner(System.in);
        String solutionName = scanner.nextLine();
        marketDirectory.printMarketInfo();
        System.out.println("Please enter MarketName: ");
        String marketName = scanner.nextLine();
        adsDirectory.printAdsInfo();
        System.out.println("Please enter AdsDescription: ");
        String adsText = scanner.nextLine();
        System.out.println("Please enter Price: ");
        int price = scanner.nextInt();
        Ads ads = adsDirectory.findAdsById(adsText);
        Market market = marketDirectory.findMarketById(marketName);
        if (ads != null && market != null) {
            solutionOfferDirectory.newSolutionOffer(market, solutionName, price, ads);
            solutionOfferDirectory.printSolutionInfo();
        } else {
            System.out.println("Market or Ads " + "does not exist");
        }
        System.out.println();
        showMainMenu();
        
    }
    
    public void addProductToSolution() {
        System.out.println("Please enter SolutionName, SupplierName, ProductName: ");
        Scanner scanner = new Scanner(System.in);
        solutionOfferDirectory.printSolutionInfo();
        System.out.println("Please enter SolutionName: ");
        String solutionName = scanner.nextLine();
        supplierDirectory.printSupplierList();
        System.out.println("Please enter SupplierName: ");
        String supplierName = scanner.nextLine();
        System.out.println("Please enter ProductName: ");
        String productName = scanner.nextLine();
        SolutionOffer solutionOffer = solutionOfferDirectory.findSolutionById(solutionName);
        Supplier supplier = supplierDirectory.findSupplier(supplierName);
        if (solutionOffer != null && supplier != null) {
            Product product = supplier.getProductcatalog().findProductByName(productName);
            if (product != null) {
                solutionOffer.addProduct(product);
                solutionOffer.printSolutionInfo();
            } else {
                System.out.println("Product" + "does not exist");
            }
        } else {
            System.out.println("Solution or Supplier" + "does not exist");
        }
        System.out.println();
        showMainMenu();
    }
    
    public void showProductList() {
        System.out.println("Please enter Channel Name and Customer Name: ");
        Scanner scanner = new Scanner(System.in);
        channelDirectory.printChannelInfo();
        System.out.println("Please enter Channel Name: ");
        String channelName = scanner.nextLine();
        customerDirectory.printCustomerNames();
        System.out.println("Please enter Customer Name: ");
        String customerName = scanner.nextLine();
        CustomerProfile customerProfile = customerDirectory.findCustomer(customerName);
        Channel channel = channelDirectory.findChannelById(channelName);
        
        if (customerProfile != null && channel != null) {
            System.out.println();
            System.out.println("------------------Channel: " + channel.getChannelName()
                    + ", Customer: " + customerProfile.getName() + "------------------");
            System.out.println("Customer Type: " + customerProfile.getCustomerType());
            
            int index1 = 0;
            for (Market market: channel.getMarkets()) {
                // check whether the customer could see current market
                if (market.getCharacteristics().contains(customerProfile.getCustomerType())) {
                    int index2 = 0;
                    // check the solution that the customer can see under current market
                    for (SolutionOffer solutionOffer: market.getSolutionOffers()) {
                        solutionOffer.printSolutionInfo(index1, index2);
                        index2++;
                    }
                }
                index1++;
            }
            buyProduct(customerProfile, channel);
        } else {
            System.out.println("Customer or Channel" + " does not exist");
        }
        System.out.println();
        showMainMenu();
    }
    
    public void buyProduct(CustomerProfile customerProfile, Channel channel){
        System.out.println();
        System.out.println("Which product you wanna buy? Please enter Number: ");
        Scanner scanner = new Scanner(System.in);
        String productNum = scanner.nextLine();
        String[] num = productNum.split("-");

        if (num.length == 3) {
            try {
                SolutionOffer selectSolutionOffer = channel.getMarkets().get(Integer.parseInt(num[0])).getSolutionOffers().get(Integer.parseInt(num[1]));
                selectSolutionOffer.printSolutionInfo();
                Product selectProduct = selectSolutionOffer.getProducts().get(Integer.parseInt(num[2]));
                System.out.print(selectProduct);
                generateOrder(selectProduct, customerProfile, channel, selectSolutionOffer);
            } catch (Exception e) {
                System.out.println("Wrong Product Number1");
                System.out.println(e);
                showBuyPage(customerProfile, channel);
            }
        } else {
            System.out.println("Wrong Product Number");
            showBuyPage(customerProfile, channel);
        }
    }
    
    public void generateOrder(Product product, CustomerProfile customerProfile, Channel channel, SolutionOffer solutionOffer) {
        System.out.println("How many do you want to buy? ");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        MasterOrderList orderList = business.getMasterorderlist();
        Order order = orderList.newOrder(customerProfile);
        order.newOrderItem(product, count, solutionOffer, channel);
        showBuyPage(customerProfile, channel);
    }
    
    public void showBuyPage(CustomerProfile customerProfile, Channel channel) {
        System.out.println("*********Please Select*********");
        System.out.println("1. Continue to buy");
        System.out.println("2. Go back to Menu");
        Scanner scanner = new Scanner(System.in);
        String select = scanner.nextLine();
        
        if (select.equals("1")) {
            buyProduct(customerProfile, channel);
        } else {
            System.out.println();
            showMainMenu();
        }
    }
    
    public void showReports() {
        System.out.println();
        System.out.println("------------------------Sales revenues by channel------------------------");
        channelDirectory.printChannelsRevenue();
        
        System.out.println();
        System.out.println("------------------------Sales revenues by market------------------------");
        marketDirectory.printMarketsRevenue();
        
        System.out.println();
        System.out.println("------------------------Sales revenues by solution------------------------");
        solutionOfferDirectory.printSolutionRevenue();
        
        System.out.println();
        System.out.println("------------------------Sales revenues by ads------------------------");
        adsDirectory.printAdsRevenue();
        
        System.out.println();
        showMainMenu();
    }
}
