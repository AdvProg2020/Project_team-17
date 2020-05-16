package Models.Accounts;

import Models.Logs.BuyLog;
import Models.Cart;
import Models.DiscountCode;

import java.util.ArrayList;

public class Customer extends Account {
    private static ArrayList<Customer> allCustomers = new ArrayList<Customer>();
    private ArrayList<DiscountCode> discountCodes;
    private ArrayList<BuyLog> buyLog;
    private Cart cart;
    private String address;

    public Customer(String userName, String firstName, String lastName, String email,
                    String phoneNumber, String password, int credit, Cart cart) {
        super(userName, firstName, lastName, email, phoneNumber, password, credit);
        buyLog = new ArrayList<>();
        this.cart = cart;
        discountCodes = new ArrayList<>();
        allCustomers.add(this);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public static Customer getCustomerByName(String userName) {
        for (Customer allCustomer : allCustomers) {
            if (allCustomer.getUserName().equals(userName))
                return allCustomer;
        }
        return null;
    }

    public Cart getCart() {
        return cart;
    }

    public static void deleteCustomer(String name) {
        allCustomers.remove(getCustomerByName(name));
    }

    public static boolean isThereCustomerWithUserName(String username) {
        for (Customer customer : allCustomers) {
            if (customer.getUserName().equals(username))
                return true;
        }
        return false;
    }

    public void addLogToBuyLog(BuyLog buyLog) {
        this.buyLog.add(buyLog);
    }

    public void addDiscountCode(DiscountCode discountCode) {
        this.discountCodes.add(discountCode);
    }

    public ArrayList<DiscountCode> getDiscountCodes() {
        return discountCodes;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public static ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public void payMoney(Customer customer, Double amount) {
        customer.setCredit(customer.getCredit() - amount);
    }

    public ArrayList<BuyLog> getBuyLog() {
        return buyLog;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "username='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
