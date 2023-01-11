/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.MarketModel;

import digitalmarketing.Business.Business;
import java.util.ArrayList;

/**
 *
 * @author cici
 */
public class MarketDirectory {
    private ArrayList<Market> marketArrayList;
    Business business;
    
    public MarketDirectory(Business b) {
        marketArrayList = new ArrayList<>();
        business = b;
    }
    
    public Market newMarket(String name, String characteristic) {
        Market market = new Market(name, characteristic);
        marketArrayList.add(market);
        return market;
    }
    
    public ArrayList<Market> findMarketByCustomerCharacteristic(String characteristic) {
        ArrayList<Market> markets = new ArrayList<>();
        for (Market market: marketArrayList) {
            if (market.getCharacteristics().contains(characteristic)){
                markets.add(market);
            }
        }
        return markets.size() > 0 ? markets: null;
    }
    
    public ArrayList<Market> getMarketArrayList() {
        return marketArrayList;
    }
    
    public Market findMarketById(String name) {
        for (Market market: marketArrayList) {
            if (market.getMarketName().equals(name)) {
                return market;
            }
        }
        return null;
    }
    
    public void printMarketsRevenue() {
        for (Market market: marketArrayList) {
            market.printMarketRevenue();
        }
    }
    
    public void printMarketInfo() {
        System.out.println("------------------Market------------------");
        for (Market market: marketArrayList) {
            System.out.print(market.getMarketName() + ": ");
            market.printSolutions();
            market.printCharacteristics();
        }
        System.out.println();
    }
}
