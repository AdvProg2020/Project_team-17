package Server.ServerController.AccountsController;

import Client.Client;
import Models.Accounts.Customer;
import Models.Accounts.Supporter;
import Models.Auction;
import Models.DiscountCode;
import Models.Logs.BuyLog;
import Models.Product;
import Models.Wallet;
import Server.ClientHandler;
import Server.ServerController.DataBaseForServer;

import java.util.ArrayList;

public class CustomerController {

    private static ArrayList<Customer> onlineCustomers = new ArrayList<>();
    private static Customer customer;

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        CustomerController.customer = customer;
    }

    public static ArrayList<Customer> getOnlineCustomers() {
        return onlineCustomers;
    }

    public static void addOnlineCustomer(Customer customer) {
        onlineCustomers.add(customer);
    }

    public static void removeOnlineCustomer(Customer customer) {
        onlineCustomers.remove(customer);
    }

    public static void showCustomerInfo() throws Exception {
        if (getCustomer() == null) {
            ClientHandler.sendObject(new Exception("there isn't any customer logged in"));
        } else {
            String username = ClientHandler.receiveMessage();
            Customer customer = DataBaseForServer.getCustomer(username);
            setCustomer(customer);
            ClientHandler.sendObject(customer.toString());
        }
    }

    public static void editCustomerInfo() throws Exception {
        String receivedItems = (String) ClientHandler.receiveObject();

        Customer customer = DataBaseForServer.getCustomer(receivedItems);
        if (customer == null) {
            ClientHandler.sendObject(new Exception("there isn't any customer with this username"));
        } else {
            ClientHandler.sendObject("Success!");
        }
    }

    public static void showCustomerLogs() {
        ArrayList<BuyLog> logs = new ArrayList<>(getCustomer().getBuyLog());
        ClientHandler.sendObject(logs);
    }

    public static void showLog() {
        BuyLog buyLog = (BuyLog) DataBaseForServer.getLog(Client.receiveMessage());
        if (buyLog != null) {
            ClientHandler.sendMessage(buyLog.toString());
        } else {
            ClientHandler.sendObject(new Exception("there isn't any log with this id"));
        }
    }

    public static void showDiscountCodes() {
        ArrayList<DiscountCode> discountCodes = new ArrayList<>(getCustomer().getDiscountCodes());
        ClientHandler.sendObject(discountCodes);
    }

    public static void showDiscountCode() {
        DiscountCode discountCode = (DiscountCode) DataBaseForServer.getDiscountCode(Client.receiveMessage());
        if (discountCode != null) {
            ClientHandler.sendMessage(discountCode.toString());
        } else {
            ClientHandler.sendObject(new Exception("there isn't any discount code with this code"));
        }
    }

    public static void showProductState() {
        ArrayList<Product> product = new ArrayList<>();
        for (BuyLog log : getCustomer().getBuyLog()) {
            product.addAll(log.getAllProducts());
        }
        ClientHandler.sendObject(product);
    }

    public static void showOnlineSupporters() {
        ArrayList<Supporter> supporters = new ArrayList<>(SupporterController.getOnlineSupporters());
        ClientHandler.sendObject(supporters);
    }

    public static void showAuctions() {
        ArrayList<Auction> auctions = new ArrayList<>(Auction.getAllAuctions());
        ClientHandler.sendObject(auctions);
    }

    public static void joinAuction() {
        Auction auction = (Auction) DataBaseForServer.getAuction(Integer.parseInt(Client.receiveMessage()));
        if (auction != null) {
            if (auction.isAuctionAvailable()) {
                ClientHandler.sendObject(auction);
            } else {
                ClientHandler.sendObject(new Exception("auction is expired"));
            }
        } else {
            ClientHandler.sendObject(new Exception("there isn't an auction with this id"));
        }
    }

    public static void bidAuction() {
        Object[] receive = (Object[]) ClientHandler.receiveObject();
        Auction auction = DataBaseForServer.getAuction(Integer.parseInt((String) receive[0]));
        double amount = Double.parseDouble((String) receive[1]);

        if (customer.getWallet().getBalance() - amount < Wallet.getLeastAmount())
            ClientHandler.sendObject(new Exception("You don't have enough money in your wallet"));
        if (amount <= auction.getMaxPrice())
            ClientHandler.sendObject(new Exception("You should place a higher bid"));
        if (!auction.isAuctionAvailable())
            ClientHandler.sendObject(new Exception("Auction has been expired"));
        else {
            ClientHandler.sendObject(auction);
        }
    }

}
