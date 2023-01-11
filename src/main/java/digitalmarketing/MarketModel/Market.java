/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.MarketModel;
import digitalmarketing.ProductManagement.SolutionOffer;

import java.util.ArrayList;


/**
 *
 * @author kal bugrara
 */
public class Market {
    ArrayList<Channel> channels;
    ArrayList<String> characteristics;
//    int size;
    ArrayList<SolutionOffer> solutionOffers;
    String marketName;
    
    public Market(String name, String s){
        marketName = name;
        channels = new ArrayList<>();
        solutionOffers = new ArrayList<>();
        characteristics = new ArrayList();
        characteristics.add(s);
    }
    
    public String getMarketName() {
        return marketName;
    }
    
    public void addCharacteristic (String s) {
        characteristics.add(s);
    }
    
    public void deleteCharacteristic (String s) {
        characteristics.remove(s);
    }
    
    public ArrayList<String> getCharacteristics() {
        return characteristics;
    }
    
    public void addChannel(Channel channel) {
        if (!channels.contains(channel)){
            channels.add(channel);
            channel.addMarkets(this);
        }
    }
    
    public ArrayList<Channel> getChannels() {
        return channels;
    }
    
    public void deleteChannel(Channel channel) {
        if (channels.contains(channel)) {
            channels.remove(channel);
            channel.deleteMarket(this);
        }
    }
    
    public ArrayList<SolutionOffer> getSolutionOffers() {
        return solutionOffers;
    }
    
    public void addSolutionOffers(SolutionOffer solutionOffer) {
        this.solutionOffers.add(solutionOffer);
    }
    
    public void deleteSolutionOffers(SolutionOffer solutionOffer) {
        this.solutionOffers.remove(solutionOffer);
    }
    
    public void printMarketCharacteristics () {
        System.out.println("------------------------Characteristics------------------------");
        for (String c: characteristics) {
            System.out.print(c + ", ");
        }
    }
    
    public void printChannels () {
        for (Channel c: channels) {
            System.out.print(c.getChannelName() + ", ");
        }
    }
    
    public int calculateSalesVolume() {
        int actualsalesvolume = 0;
        for (SolutionOffer item: solutionOffers) {
            actualsalesvolume += item.calculateSalesVolume();
        }
        return actualsalesvolume;
    }
    
    public int getPricePerformance() {
        int salesvalume = 0;
        for (SolutionOffer item: solutionOffers) {
            salesvalume += item.getPricePerformance();
        }
        return salesvalume;
    }
    
    public void printSolutions() {
        System.out.print("Solution Offer in " + marketName + ": ");
        for (SolutionOffer solutionOffer: solutionOffers) {
            System.out.print(solutionOffer.getSolutionName());
            System.out.print(", ");
        }
        System.out.println();
    }
    
    public void printCharacteristics() {
        System.out.print("Customer in " + marketName + ": ");
        for (String type: characteristics) {
            System.out.print(type);
            System.out.print(", ");
        }
        System.out.println();
    }
    
    public void printMarketRevenue() {
        System.out.println(marketName + " | " + calculateSalesVolume() + " | " + getPricePerformance());
    }
}


