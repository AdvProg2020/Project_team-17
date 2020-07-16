package Client.ClientController.AccountsController;

import Client.Client;
import Models.DiscountCode;
import Models.Logs.BuyLog;
import View.RegisterCustomerMenu;

import java.util.ArrayList;

public class CustomerController {

    public static String showCustomerInfo() throws Exception {
        String func = "Show Customer Info";
        Client.sendMessage(func);
        if (RegisterCustomerMenu.getCurrentCustomer() == null) {
            throw new Exception("a customer should login first!");
        } else {
            String customerName = RegisterCustomerMenu.getCurrentCustomer().getUserName();
            Client.sendMessage(customerName);
            Object data = Client.receiveObject();
            if (data instanceof Exception) {
                throw new Exception("account with this username doesn't found");
            } else {
                return String.valueOf(data);
            }
        }
    }

    public static void editCustomerInfo(String field, String newContent) throws Exception {
        String func = "Edit Customer Info";
        Client.sendMessage(func);
        if (RegisterCustomerMenu.getCurrentCustomer() == null) {
            throw new Exception("a customer should login first!");
        } else {
            Object[] toSend = new Object[2];
            toSend[0] = field;
            toSend[1] = newContent;
            Client.sendObject(toSend);
            Client.receiveObject();
        }
    }


    public static ArrayList<BuyLog> showCustomerLogs() {
        String func = "Show Customer Logs";
        Client.sendMessage(func);

        Client.sendObject(RegisterCustomerMenu.getCurrentCustomer());
        Object response = Client.receiveObject();
        return (ArrayList<BuyLog>) response;

    }

    public static String showLogDetails(String id) throws Exception {
        String func = "Show Log Details";
        Client.sendMessage(func);

        Client.sendMessage(id);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any log with this id");
        else {
            return (String) response;
        }
    }

    public static ArrayList<DiscountCode> showDiscountCodes() {
        String func = "Show Customer Discount Codes";
        Client.sendMessage(func);

        Client.sendObject(RegisterCustomerMenu.getCurrentCustomer());
        return (ArrayList<DiscountCode>) Client.receiveObject();
    }

    public static String showDiscountCodeDetails(String code) throws Exception {
        String func = "Show Customer Discount Code Details";
        Client.sendMessage(func);

        Client.sendMessage(code);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any discount code with this code");
        else {
            return (String) response;
        }
    }
}
