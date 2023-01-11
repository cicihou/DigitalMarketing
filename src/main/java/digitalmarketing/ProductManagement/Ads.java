/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.ProductManagement;

import java.util.ArrayList;

/**
 *
 * @author cici
 */
public class Ads {
    private String description;
    private int price;
    private ArrayList<SolutionOffer> solutionOffers;
    
    public Ads(String description, int price) {
        this.description = description;
        this.price = price;
        this.solutionOffers = new ArrayList<>();
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public void addSolutionOffer (SolutionOffer solutionOffer) {
        this.solutionOffers.add(solutionOffer);
    }
    
    public ArrayList<SolutionOffer> getSolutionOffers() {
        return solutionOffers;
    }
    
    public void deleteSolutionOffer (SolutionOffer solutionOffer) {
        this.solutionOffers.remove(solutionOffer);
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
    
    public void printAdsRevenue() {
        System.out.println(description + " | " + calculateSalesVolume() + " | " + getPricePerformance());
    }
    
}
