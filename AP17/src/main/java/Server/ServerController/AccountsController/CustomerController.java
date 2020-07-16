package Server.ServerController.AccountsController;

import Client.Client;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Discount;
import Models.DiscountCode;
import Models.Logs.BuyLog;
import Models.Logs.Log;
import Models.Logs.SellLog;
import View.RegisterCustomerMenu;
import View.RegisterManagerMenu;
import View.RegisterSellerMenu;

import java.util.ArrayList;

public class CustomerController {

    public static void showCustomerInfo() {
        if (RegisterCustomerMenu.getCurrentCustomer() == null) {
            Client.sendObject(new Exception("customer should first login"));
        }
        String username = Client.receiveMessage();
        Client.sendMessage(Customer.getCustomerByName(username).toString());
    }

    public static void editManagerInfo() throws Exception {
        Object[] receivedItems = (Object[]) Client.receiveObject();
        String field = (String) receivedItems[0];
        String newContentForThisField = (String) receivedItems[1];
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
            Client.sendMessage("Success!");
        }
    }

    public static void showCustomerLogs() {
        ArrayList<BuyLog> logs = RegisterCustomerMenu.getCurrentCustomer().getBuyLog();
        ArrayList<String> order = new ArrayList<String>();
        for (BuyLog log : logs) {
            order.add(log.getId());
        }
        Client.sendObject(order);
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
