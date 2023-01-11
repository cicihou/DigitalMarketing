/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.MarketModel;

import digitalmarketing.OrderManagement.OrderItem;
import java.util.ArrayList;

/**
 *
 * @author kal bugrara
 */
public class Channel {
    private String channelName;
    private ArrayList<Market> markets;
    private ArrayList<OrderItem> orderItems;
    
    public Channel (String name) {
        markets = new ArrayList<>();
        channelName = name;
        orderItems = new ArrayList<>();
    }
    
    public Channel (String name, ArrayList<Market> marketList) {
        markets = marketList;
        for (Market c: marketList){
            c.addChannel(this);
        }
        channelName = name;
    }
    
    public String getChannelName() {
        return channelName;
    }
    
    public void addMarkets(Market market) {
        if (!markets.contains(market)) {
            markets.add(market);
            market.addChannel(this);
        }
    }
    
    public void deleteMarket(Market market) {
        if (markets.contains(market)){
            markets.remove(market);
            market.deleteChannel(this);
        }
    }
    
    public ArrayList<Market> getMarkets() {
        return markets;
    }
    
    public void addOrderItem (OrderItem item) {
        orderItems.add(item);
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
    
    public void printAllMarket() {
        System.out.print("Markets in " + channelName + ": ");
        for (Market market: markets) {
            System.out.print(market.getMarketName());
            System.out.print(", ");
        }
        System.out.println();
    }
    
    public void printChannelRevenue() {
        System.out.println(channelName + " | " + calculateSalesVolume() + " | " + getPricePerformance());
    }
}
