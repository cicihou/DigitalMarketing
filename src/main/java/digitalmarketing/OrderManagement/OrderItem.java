/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.OrderManagement;

import digitalmarketing.ProductManagement.Product;
import digitalmarketing.ProductManagement.SolutionOffer;
import digitalmarketing.MarketModel.Channel;

/**
 *
 * @author kal bugrara
 */
public class OrderItem {

    Product selectedproduct;
    int actualPrice;
    int quantity;
    Channel channel;
    SolutionOffer solutionOffer;

    public OrderItem(Product p, int q, int ap) {
        selectedproduct = p;
        p.addOrderItem(this); //make sure product links back to the item
        quantity = q;
        actualPrice = ap;
    }
    
    public OrderItem(Product p, int q, SolutionOffer s, Channel c) {
        selectedproduct = p;
        p.addOrderItem(this);
        s.addOrderItem(this);
        c.addOrderItem(this);
        quantity = q;
        channel = c;
        solutionOffer = s;
        actualPrice = solutionOffer.getPrice() - solutionOffer.getAds().getPrice();
    }

    public int getOrderItemTotal() {
        return actualPrice * quantity;
    }

//The following calculates what the price gain would have been if products were sold at target price
    public int getOrderItemTargetTotal() {
        return selectedproduct.getTargetPrice() * quantity;
    }

    //returns positive if seller is making higher margin than target
    //returns negative if seller is making lower margin than target
    //otherwise zero meaning neutral
    public int calculatePricePerformance() {
        return actualPrice - selectedproduct.getTargetPrice();
    }

    public boolean isActualAboveTarget() {
        if (actualPrice > selectedproduct.getTargetPrice()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isActualBelowTarget() {
        if (actualPrice < selectedproduct.getTargetPrice()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isActualATTarget() {
        if (actualPrice == selectedproduct.getTargetPrice()) {
            return true;
        } else {
            return false;
        }

    }
    
    public Channel getChannel() {
        return channel;
    }

    public SolutionOffer getSolutionOffer() {
        return solutionOffer;
    }
    
    public void printItemDetails(){
        System.out.println(selectedproduct.getName() + " | price: " + actualPrice + "| q: " + quantity + 
                " | Channel: " + channel.getChannelName() + " | Market: " + 
                solutionOffer.getMarket().getMarketName() + " | Solution Offer: " + solutionOffer.getSolutionName());
    }
}
