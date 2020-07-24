package Client.ClientController.AccountsController;

import Client.Client;
import Models.Accounts.Customer;
import Models.Accounts.Supporter;
import Models.Auction;
import Models.DiscountCode;
import Models.Logs.BuyLog;
import Models.Product;
import Server.ServerController.DataBaseForServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CCustomerController {
    private static ArrayList<Customer> onlineCustomers = new ArrayList<>();
    private static Customer customer;

    public static Customer getCustomer() {
        return customer;
    }

    public static void setCustomer(Customer customer) {
        CCustomerController.customer = customer;
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

    public static String showCustomerInfo() throws Exception {
        if (getCustomer() == null) {
            throw new Exception("there isn't any customer logged in");
        }
        String func = "Show Customer Info";
        Client.sendMessage(func);

        String userName = getCustomer().getUserName();
        Client.sendMessage(userName);

        try {
            Object data = Client.receiveObject();
            return String.valueOf(data);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void editCustomerInfo(String dataToEdit) throws Exception {
        String func = "Edit Customer Info";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = getCustomer().getUserName();
        toSend[1] = dataToEdit;
        Client.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Success")) {
                String[] split = dataToEdit.split("\\s");
                Customer customer = DataBaseForServer.getCustomer(getCustomer().getUserName());
                String field = split[0];
                String newContentForThisField = split[1];
                if (field.equalsIgnoreCase("first name")) {
                    customer.changeFirstName(customer, newContentForThisField);
                } else if (field.equalsIgnoreCase("last name")) {
                    customer.changeLastName(customer, newContentForThisField);
                } else if (field.equalsIgnoreCase("email")) {
                    customer.changeEmail(customer, newContentForThisField);
                } else if (field.equalsIgnoreCase("phone number")) {
                    customer.changePhoneNumber(customer, newContentForThisField);
                } else if (field.equalsIgnoreCase("password")) {
                    customer.changePassword(customer, newContentForThisField);
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObservableList<String> showBuyLogs() {
        ArrayList<BuyLog> allLogs;
        String func = "Show Customer Buy Logs";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allLogs = (ArrayList<BuyLog>) response;
        ArrayList<String> showAllLogs = new ArrayList<>();
        for (BuyLog buyLog : allLogs) {
            showAllLogs.add(buyLog.getId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(showAllLogs);
        return data;

    }

    public static String showBuyLog(String id) throws Exception {
        String func = "Show Customer Buy Log";
        Client.sendMessage(func);

        Client.sendMessage(String.valueOf(id));
        BuyLog buyLog = (BuyLog) Client.receiveObject();

        if (buyLog != null) {
            String requestData = buyLog.toString();
            return requestData;
        } else {
            throw new Exception("there isn't any log with this id");
        }
    }


    public static ObservableList<String> showDiscountCodes() {
        ArrayList<DiscountCode> discountCodes;
        String func = "Show Customer Discount Codes";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        discountCodes = (ArrayList<DiscountCode>) response;
        ArrayList<String> discountInfo = new ArrayList<>();
        for (DiscountCode discountCode : discountCodes) {
            discountInfo.add(discountCode.getDiscountCode());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(discountInfo);
        return data;

    }

    public static String showDiscountCode(String id) throws Exception {
        String func = "Show Customer Discount Code";
        Client.sendMessage(func);

        Client.sendMessage(String.valueOf(id));
        DiscountCode discountCode = (DiscountCode) Client.receiveObject();

        if (discountCode != null) {
            String requestData = discountCode.toString();
            return requestData;
        } else {
            throw new Exception("there isn't any discount code with this code");
        }
    }

    public static ObservableList<String> showProductAndState() {
        ArrayList<Product> products;
        String func = "Show Product And State";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        products = (ArrayList<Product>) response;
        ArrayList<String> info = new ArrayList<>();
        for (Product product : products) {
            info.add(product.getName() + " " + product.getProductStateEnum());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;

    }

    public static ObservableList<String> showOnlineSupporter() {
        ArrayList<Supporter> supporters;
        String func = "Show Online Supporter";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        supporters = (ArrayList<Supporter>) response;
        ArrayList<String> info = new ArrayList<>();
        for (Supporter supporter : supporters) {
            info.add(supporter.getUserName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;

    }

    public static ObservableList<String> showAllAuction() {
        ArrayList<Auction> allAuction;
        String func = "Show All Auction";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allAuction = (ArrayList<Auction>) response;
        ArrayList<String> showAllLogs = new ArrayList<>();
        for (Auction auction : allAuction) {
            showAllLogs.add(String.valueOf(auction.getId()));
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(showAllLogs);
        return data;
    }

    public static void joinAuction(String id) throws Exception {
        String func = "Join Auction";
        Client.sendMessage(func);

        Client.sendMessage(String.valueOf(id));

        try {
            Object response = Client.receiveObject();
            Auction auction = (Auction) response;
            auction.joinAuction(getCustomer());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void bidAuction(String id, double amount) throws Exception {
        String func = "Bid Auction";
        Client.sendMessage(func);
        Object[] toSend = new Object[2];
        toSend[0] = id;
        toSend[1] = amount;
        Client.sendObject(toSend);

        try {
            Object response = Client.receiveObject();
            Auction auction = (Auction) response;
            auction.setPrice(getCustomer(), amount);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
