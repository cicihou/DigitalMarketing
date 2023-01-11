/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.ProductManagement;

import digitalmarketing.Business.Business;
import java.util.ArrayList;

/**
 *
 * @author cici
 */
public class AdsDirectory {
    private ArrayList<Ads> adsArrayList;
    Business business;
    
    public AdsDirectory(Business b) {
        adsArrayList = new ArrayList<>();
        business = b;
    }
    
    public Ads newAds(String description, int price) {
        Ads ads = new Ads(description, price);
        adsArrayList.add(ads);
        return ads;
    }
    
    public Ads findAdsById(String text) {
        for (Ads ads: adsArrayList) {
            if (ads.getDescription().equals(text)) {
                return ads;
            }
        }
        return null;
    }
    
    public void printAdsRevenue() {
        for (Ads ads: adsArrayList) {
            ads.printAdsRevenue();
        }
    }
    
    public void printAdsInfo() {
        System.out.println("------------------Ads------------------");
        for (Ads ads: adsArrayList) {
            System.out.print(ads.getDescription());
            System.out.print(", ");
        }
        System.out.println();
    }
}
