package Models;

import java.util.ArrayList;

public class Customer extends Account {
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    //private ArrayList<BuyLog> buyLogs;
    private BuyLog buyLog;
    private Cart cart;
    private String address;

    public Customer(String userName, String firstName, String lastName, String email,
                    String phoneNumber, String password, BuyLog buyLog, Cart cart) {
        super(userName, firstName, lastName, email, phoneNumber, password);
        allCustomers.add(this);
        this.buyLog = buyLog;
        this.cart = cart;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public static Customer getCustomerWithName(String userName){
        for (Customer allCustomer : allCustomers) {
            if (allCustomer.getUserName().equals(userName))
                return allCustomer;
        }
        return null;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setBuyLog(BuyLog buyLog) {
        this.buyLog = buyLog;
    }

    public static ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public BuyLog getBuyLog() {
        return buyLog;
    }
}
