/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digitalmarketing.ProductManagement;

/**
 *
 * @author kal bugrara
 */
//this class will extract summary data from the product
public class ProductSummary {

    Product subjectproduct;
    int numberofsalesabovetarget;
    int numberofsalesbelowtarget;
    int salesvalume; //total profit above target --could be negative too
    int acutalsalesvolume;
    int rank; // will be done later

    public ProductSummary(Product p) {

        numberofsalesabovetarget = p.getNumberOfProductSalesAboveTarget();
        salesvalume = p.getOrderPricePerformance();
        subjectproduct = p; //keeps track of the product itself not as well;
        acutalsalesvolume = p.getSalesVolume();
    }

    public int getNumberAboveTarget() {
        return numberofsalesabovetarget;
    }

    public boolean isProductAlwaysAboveTarget() {
        return false; // to be implemented
    }
    
    public int getAcutalsalesvolume() {
        return acutalsalesvolume;
    }
    
    public int getSalesValume() {
        return salesvalume;
    }
    
    public void print(){
        
        System.out.println(subjectproduct.getName() + " | " +  acutalsalesvolume + "|" + salesvalume);
        
    }

}
