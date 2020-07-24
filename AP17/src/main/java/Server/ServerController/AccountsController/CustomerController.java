package Server.ServerController.AccountsController;

import Client.Client;
import Models.Accounts.Customer;
import Models.Accounts.Supporter;
import Models.DiscountCode;
import Models.Logs.BuyLog;
import Models.Product;
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

    public static void showManagerInfo() throws Exception {
        if (getCustomer() == null) {
            ClientHandler.sendObject(new Exception("there isn't any customer logged in"));
        } else {
            String username = ClientHandler.receiveMessage();
            Customer customer = DataBaseForServer.getCustomer(username);
            setCustomer(customer);
            ClientHandler.sendObject(customer.toString());
        }
    }

    public static void editManagerInfo() throws Exception {
        String receivedItems = (String) ClientHandler.receiveObject();

        Customer customer = DataBaseForServer.getCustomer(receivedItems);
        if (customer == null) {
            ClientHandler.sendObject(new Exception("there isn't any customer with this username"));
        } else {
            ClientHandler.sendObject("Success!");
        }
    }

    public static void showSellerLogs() {
        ArrayList<BuyLog> logs = new ArrayList<>(getCustomer().getBuyLog());
        Client.sendObject(logs);
    }

    public static void showLog() {
        BuyLog buyLog = (BuyLog) DataBaseForServer.getLog(Client.receiveMessage());
        if (buyLog != null) {
            Client.sendMessage(buyLog.toString());
        } else {
            Client.sendObject(new Exception("there isn't any log with this id"));
        }
    }

    public static void showDiscountCodes() {
        ArrayList<DiscountCode> discountCodes = new ArrayList<>(getCustomer().getDiscountCodes());
        Client.sendObject(discountCodes);
    }

    public static void showDiscountCode() {
        DiscountCode discountCode = (DiscountCode) DataBaseForServer.getDiscountCode(Client.receiveMessage());
        if (discountCode != null) {
            Client.sendMessage(discountCode.toString());
        } else {
            Client.sendObject(new Exception("there isn't any discount code with this code"));
        }
    }

    public static void showProductState() {
        ArrayList<Product> product = new ArrayList<>();
        for (BuyLog log : getCustomer().getBuyLog()) {
            product.addAll(log.getAllProducts());
        }
        Client.sendObject(product);
    }

    public static void showOnlineSupporters() {
        ArrayList<Supporter> supporters = new ArrayList<>(SupporterController.getOnlineSupporters());
        Client.sendObject(supporters);
    }

}
