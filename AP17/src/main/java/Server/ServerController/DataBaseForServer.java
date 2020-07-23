package Server.ServerController;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Accounts.Supporter;
import Models.Bank.BankAccount;
import Models.Bank.Receipt;
import Models.Logs.BuyLog;
import Models.Logs.Log;
import Models.Logs.SellLog;
import Models.Request.Request;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataBaseForServer implements Serializable {
    private static ArrayList<Manager> allMangers = new ArrayList<>();
    private static ArrayList<Customer> allCustomers = new ArrayList<>();
    private static ArrayList<Seller> allSellers = new ArrayList<>();
    private static ArrayList<Supporter> allSupporters = new ArrayList<>();
    private static ArrayList<BankAccount> allBankAccounts = new ArrayList<>();
    private static ArrayList<Receipt> allReceipts = new ArrayList<>();
    private static ArrayList<Log> allLogs = new ArrayList<>();
    private static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();
    private static ArrayList<SellLog> allSellLogs = new ArrayList<>();
    private static ArrayList<Request> allRequests = new ArrayList<>();
    private static ArrayList<Auction> allAuctions = new ArrayList<>();
    private static ArrayList<Cart> allCarts = new ArrayList<>();
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private static ArrayList<Discount> allDiscounts = new ArrayList<>();
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private static ArrayList<Product> allDiscountProducts = new ArrayList<>();


    public static void addManager(Manager manager) {
        allMangers.add(manager);
    }

    public static Manager getManager(String username) {
        for (Manager manger : allMangers) {
            if (manger.getUserName().equals(username)) {
                return manger;
            }
        }
        return null;
    }

    public static void deleteManager(Manager manager) {
        allMangers.remove(manager);
    }

    public static void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public static Customer getCustomer(String username) {
        for (Customer customer : allCustomers) {
            if (customer.getUserName().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    public static void deleteCustomer(Customer customer) {
        allCustomers.remove(customer);
    }

    public static void addSeller(Seller seller) {
        allSellers.add(seller);
    }

    public static Seller getSeller(String username) {
        for (Seller seller : allSellers) {
            if (seller.getUserName().equals(username)) {
                return seller;
            }
        }
        return null;
    }

    public static void deleteSeller(Seller seller) {
        allSellers.remove(seller);
    }

    public static void addSupporter(Supporter supporter) {
        allSupporters.add(supporter);
    }

    public static Supporter getSupporter(String userName) {
        for (Supporter supporter : allSupporters) {
            if (supporter.getUserName().equals(userName)) {
                return supporter;
            }
        }
        return null;
    }

    public static void deleteSupporter(Supporter supporter) {
        allSupporters.remove(supporter);
    }

}
