/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.ProductManagement;

import digitalmarketing.MarketModel.Market;
import digitalmarketing.Business.Business;
import java.util.ArrayList;

/**
 *
 * @author cici
 */
public class SolutionOfferDirectory {
    private ArrayList<SolutionOffer> solutionOfferArrayList;
    Business business;
    
    public SolutionOfferDirectory(Business b) {
        solutionOfferArrayList = new ArrayList<>();
        business = b;
    }
    
    public SolutionOffer newSolutionOffer(Market m, String name, int price, Ads ads) {
        SolutionOffer solutionOffer = new SolutionOffer(m, name, price, ads);
        solutionOfferArrayList.add(solutionOffer);
        return solutionOffer;
    }
    
    public SolutionOffer findSolutionById(String name) {
        for (SolutionOffer solutionOffer: solutionOfferArrayList) {
            if (solutionOffer.getSolutionName().equals(name)) {
                return solutionOffer;
            }
        }
        return null;
    }
    
    public void printSolutionRevenue() {
        for (SolutionOffer solutionOffer: solutionOfferArrayList) {
            solutionOffer.printSolutionRevenue();
        }
    }
    
    public void printSolutionInfo() {
        System.out.println("------------------Solution------------------");
        for (SolutionOffer solutionOffer: solutionOfferArrayList) {
            solutionOffer.printSolutionInfo();
        }
        System.out.println();
    }
}
