package Client.ClientController.AccountsController;

import Client.Client;
import Models.DiscountCode;
import Models.Logs.BuyLog;
import View.RegisterCustomerMenu;

import java.util.ArrayList;

public class CCustomerController {

    public static void showCustomerInfo()  {
        String func = "Show Customer Info";
        Client.sendMessage(func);
    }

    public static void editCustomerInfo() {
        String func = "Edit Customer Info";
        Client.sendMessage(func);
    }


    public static void showCustomerLogs() {
        String func = "Show Customer Logs";
        Client.sendMessage(func);

       /* Client.sendObject(RegisterCustomerMenu.getCurrentCustomer());
        Object response = Client.receiveObject();
        return (ArrayList<BuyLog>) response;*/

    }

    public static void showLogDetails() {
        String func = "Show Log Details";
        Client.sendMessage(func);
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
