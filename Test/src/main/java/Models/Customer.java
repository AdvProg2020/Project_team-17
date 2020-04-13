package Models;

import java.util.ArrayList;

public class Customer extends Account {
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private ArrayList<BuyLog> buyLogs;
    public Customer(String userName, String firstName, String lastName, String email, String phoneNumber, String password) {
        super(userName, firstName, lastName, email, phoneNumber, password);
        buyLogs= new ArrayList<BuyLog>();
        allCustomers.add(this);
    }

    public static ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }
}
