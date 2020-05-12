package Models.Logs;

import Models.Product;

import java.util.ArrayList;
import java.util.Date;

public abstract class Log {
    protected String id;
    protected Date date;
    protected double paymentAmount;
    protected String address;
    protected String phoneNumber;
    protected ArrayList<Product> allProducts;
    protected String customerName;
    protected boolean isReceived;


    public Log(String id, Date date, double paymentAmount, String address, String phoneNumber, String customerName,ArrayList<Product> products,boolean isReceived) {
        this.id = id;
        this.date = date;
        this.paymentAmount = paymentAmount;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.allProducts=products;
        this.customerName = customerName;
        this.isReceived=isReceived;
    }

    @Override
    public abstract String toString();

    public String getId() {
        return id;
    }
    public boolean doesLogHaveThisProduct(String id){
        for (Product product : allProducts) {
            if(product.getProductId().equals(id)){
                return true;
            }
        }return false;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }
}
