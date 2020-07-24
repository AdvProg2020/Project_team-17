package Models;

import Models.Accounts.Customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Auction implements Serializable {
    private ArrayList<Customer> allCustomer;
    private int id;
    private Product product;
    private double maxPrice;
    private Customer customerWithMaxPrice;
    private Date endDate;
    private Date currentDate;
    private static ArrayList<Auction> allAuctions = new ArrayList<>();
    private HashMap<Customer, Double> customersSuggestionAmount = new HashMap<>();

    public Auction(Product product, Date endDate) {
        this.id = allAuctions.size() + 1000;
        this.product = product;
        this.endDate = endDate;
        allCustomer = new ArrayList<>();
        maxPrice = 0;
        currentDate = new Date();
        allAuctions.add(this);
    }

    public void joinAuction(Customer buyerAccount) {
        this.allCustomer.add(buyerAccount);
    }

    public boolean isAuctionAvailable() {
        currentDate = new Date();
        return endDate.compareTo(currentDate) > 0;
    }

    public int getId() {
        return id;
    }

    public void setPrice(Customer customer, double amount){
        customersSuggestionAmount.put(customer, amount);
        maxPrice = amount;
        customerWithMaxPrice = customer;
    }

    public Product getProduct() {
        return product;
    }

    public static ArrayList<Auction> getAllAuctions() {
        return allAuctions;
    }

    public double getMaxPrice() {
        return maxPrice;
    }
}