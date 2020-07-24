package Models;

import Models.Accounts.Customer;

import java.util.ArrayList;
import java.util.Date;

public class Auction {
    private ArrayList<Customer> allCustomer;
    private int id;
    private Product product;
    private long maxPrice;
    private Customer customerWithMaxPrice;
    private Date endDate;
    private Date currentDate;
    private static ArrayList<Auction> allAuctions = new ArrayList<>();

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

    public boolean setMaxPrice(long maxPrice, Customer customer) {
        if (!canSetPrice(customer))
            return false;
        if (maxPrice <= this.maxPrice)
            return false;
        this.maxPrice = maxPrice;
        customerWithMaxPrice = customer;
        return true;
    }

    public int getId() {
        return id;
    }

    private boolean canSetPrice(Customer customer) {
        return true;
    }

    public Product getProduct() {
        return product;
    }

    public static ArrayList<Auction> getAllAuctions() {
        return allAuctions;
    }
}