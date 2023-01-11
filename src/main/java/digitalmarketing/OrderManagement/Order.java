/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.OrderManagement;

import java.util.ArrayList;
import digitalmarketing.CustomerManagement.CustomerProfile;
import digitalmarketing.Personnel.EmployeeProfile;
import digitalmarketing.ProductManagement.Product;
import digitalmarketing.ProductManagement.SolutionOffer;
import digitalmarketing.MarketModel.Channel;

/**
 *
 * @author kal bugrara
 */
public class Order {

    ArrayList<OrderItem> orderitems;
    CustomerProfile customer;
    EmployeeProfile salesperson;

    public Order() {
    }

    public Order(CustomerProfile cp) {
        orderitems = new ArrayList();
        customer = cp;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson = null;
    }

    public Order(CustomerProfile cp, EmployeeProfile ep) {
        orderitems = new ArrayList();
        customer = cp;
        customer.addCustomerOrder(this); //we link the order to the customer
        salesperson.addSalesOrder(this);
    }

    public OrderItem newOrderItem(Product p, int q, SolutionOffer s, Channel c) {
        OrderItem oi = new OrderItem(p, q, s, c);
        orderitems.add(oi);
        return oi;
    }
    
    public ArrayList<OrderItem> getOrderitems(){
        return orderitems;
    }

    public int getOrderTotal() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTotal();
        }
        return sum;
    }

    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.calculatePricePerformance();     //positive and negative values       
        }
        return sum;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            if (oi.isActualAboveTarget() == true) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    //sum all the item targets and compare to the total of the order 
    public boolean isOrderAboveTotalTarget() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTargetTotal(); //product targets are added
        }
        if (getOrderTotal() > sum) {
            return true;
        } else {
            return false;
        }

    }

    public void printOrderDetails(){
        System.out.println("Order for " + customer.getName());
        for (OrderItem oi : orderitems){
            oi.printItemDetails();
        }
    }
    
    
}
