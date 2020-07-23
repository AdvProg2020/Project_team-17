package Models;


import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.Accounts.Customer;
import View.PurchasingProcessMenus.DiscountCodePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Auction extends Thread {
    private ArrayList<Customer> allCustomer;
    private Product product;
    private long maxPrice;
    private Customer customerWithMaxPrice;
    private Date endDate;
    private Date currentDate;
    private static ArrayList<Auction> allAuctions = new ArrayList<>();

    public Auction(Product product, Date endDate) {
        this.product = product;
        this.endDate = endDate;
        allCustomer = new ArrayList<>();
        maxPrice = 0;
        currentDate = new Date();
        allAuctions.add(this);
    }

    public void joinAuction(Customer buyerAccount) {
        allCustomer.add(buyerAccount);
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

    private boolean canSetPrice(Customer customer) {
        return true;
    }

    @Override
    public void run() {
        while (isAuctionAvailable()) {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        Customer customer = customerWithMaxPrice;
        Cart cart = customer.getCart();
        cart.addProductToCart(customer, product);
        try {
            CustomerAbilitiesManager.pay(customer, DiscountCodePage.getDiscountCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Product getProduct() {
        return product;
    }
}