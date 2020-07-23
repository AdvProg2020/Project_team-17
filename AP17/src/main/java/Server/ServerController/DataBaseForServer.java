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

    public static void addBankAccount(BankAccount bankAccount) {
        allBankAccounts.add(bankAccount);
    }

    public static BankAccount getBankAccount(String userName) {
        for (BankAccount bankAccount : allBankAccounts) {
            if (bankAccount.getUsername().equals(userName)) {
                return bankAccount;
            }
        }
        return null;
    }

    public static void deleteBankAccount(BankAccount bankAccount) {
        allBankAccounts.remove(bankAccount);
    }

    public static void addReceipt(Receipt receipt) {
        allReceipts.add(receipt);
    }

    //tu if e in chio bayad bbinam yekie ya na
    public static Receipt getReceipt(String description) {
        for (Receipt receipt : allReceipts) {
            if (receipt.getDescription().equals(description)) {
                return receipt;
            }
        }
        return null;
    }

    public static void deleteReceipt(Receipt receipt) {
        allReceipts.remove(receipt);
    }

    public static void addLog(Log log) {
        allLogs.add(log);
    }

    //tu inam chio bayad tu if check konam?
    public static Log getLog(String Name) {
        for (Log log : allLogs) {
            if (log.getName().equals(Name)) {
                return log;
            }
        }
        return null;
    }

    public static void deleteLog(Log log) {
        allLogs.remove(log);
    }

    public static void addBuyLog(BuyLog buyLog) {
        allBuyLogs.add(buyLog);
    }

    // va tu in
    public static BuyLog getBuyLog(String Name) {
        for (BuyLog buyLog : allBuyLogs) {
            if (buyLog.getName().equals(Name)) {
                return buyLog;
            }
        }
        return null;
    }

    public static void deleteBuyLog(BuyLog buyLog) {
        allBuyLogs.remove(buyLog);
    }

    public static void addSellLog(SellLog sellLog) {
        allSellLogs.add(sellLog);
    }

    // va tu in
    public static SellLog getSellLog(String Name) {
        for (SellLog sellLog : allSellLogs) {
            if (sellLog.getName().equals(Name)) {
                return sellLog;
            }
        }
        return null;
    }

    public static void deleteSellLog(SellLog sellLog) {
        allSellLogs.remove(sellLog);
    }

    public static void addRequest(Request request) {
        allRequests.add(request);
    }

    // va tu in
    public static Request getRequest(String Id) {
        for (Request request : allRequests) {
            if (request.getId().equals(Id)) {
                return request;
            }
        }
        return null;
    }

    public static void deleteRequest(Request request) {
        allRequests.remove(request);
    }

    public static void addAuction(Auction auction) {
        allAuctions.add(auction);
    }

    // va tu in
    public static Auction getAuction(String Name) {
        for (Auction auction : allAuctions) {
            if (auction.getName().equals(Name)) {
                return auction;
            }
        }
        return null;
    }

    public static void deleteAuction(Auction auction) {
        allAuctions.remove(auction);
    }

    public static void addCart(Cart cart) {
        allCarts.add(cart);
    }

    //ino tuye for chi bezanam ba chi moghayese konam?
    public static Cart getCart(String Name) {
        for (Cart cart : allCarts) {
            //TODO
        }
        return null;
    }

    public static void deleteCart(Cart cart) {
        allCarts.remove(cart);
    }

    public static void addCategory(Category category) {
        allCategories.add(category);
    }


    public static Category getCategory(String Name) {
        for (Category category : allCategories) {
            if (category.getCategoryName().equals(Name)) {
                return category;
            }
        }
        return null;
    }

    public static void deleteCategory(Category category) {
        allCategories.remove(category);
    }

    public static void addDiscount(Discount discount) {
        allDiscounts.add(discount);
    }

    public static Discount getDiscount(String Id) {
        for (Discount discount : allDiscounts) {
            if (discount.getDiscountId().equals(Id)) {
                return discount;
            }
        }
        return null;
    }

    public static void deleteDiscount(Discount discount) {
        allDiscounts.remove(discount);
    }

}
