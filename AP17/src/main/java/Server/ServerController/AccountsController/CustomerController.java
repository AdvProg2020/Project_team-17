package Server.ServerController.AccountsController;

import Client.Client;
import Client.ClientController.AccountsController.CCustomerController;
import Models.Accounts.Customer;
import Models.DiscountCode;
import Models.Logs.BuyLog;
import Models.Logs.Log;
import View.RegisterCustomerMenu;

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
    public static String showCustomerInfo() {
        return RegisterCustomerMenu.getCurrentCustomer().toString();
    }

    public static void editCustomerInfo(String field, String newContentForThisField) throws Exception {
        if (RegisterCustomerMenu.getCurrentCustomer() == null) {
            throw new Exception("customer should login first!");
        } else {
            Customer customer = RegisterCustomerMenu.getCurrentCustomer();
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
            //Client.sendMessage("Success!");
        }
    }

    public static ArrayList<String> showCustomerLogs() {
        ArrayList<BuyLog> logs = RegisterCustomerMenu.getCurrentCustomer().getBuyLog();
        ArrayList<String> order = new ArrayList<String>();
        for (BuyLog log : logs) {
            order.add(log.getId());
        }
        return order;
    }

    public static void showLog() {
        BuyLog buyLog = (BuyLog) Log.getLogWithId(Client.receiveMessage());
        if (buyLog != null) {
            Client.sendMessage(buyLog.toString());
        } else {
            Client.sendObject(new Exception("there isn't any log with this id"));
        }
    }

    public static void showDiscountCodes() {
        ArrayList<DiscountCode> discounts = new ArrayList<>();
        discounts.addAll(RegisterCustomerMenu.getCurrentCustomer().getDiscountCodes());
        Client.sendObject(discounts);
    }

    public static void showDiscountCodeInfo() {
        String code = Client.receiveMessage();
        if (DiscountCode.getDiscountCodeWithCode(code) != null) {
            Client.sendMessage(DiscountCode.getDiscountCodeWithCode(code).toString());
        } else {
            Client.sendObject(new Exception("there isn't any account with this username"));
        }
    }

}
