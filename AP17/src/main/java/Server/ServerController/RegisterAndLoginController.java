package Server.ServerController;

import Client.Client;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Accounts.Supporter;

import java.io.IOException;

public class RegisterAndLoginController {
    public static void registerSeller(String username, String firstName, String lastName,
                                      String email, String phoneNumber, String password, String extraInfo, String path) {
        if (Seller.getSellerByName(username) == null && Manager.getManagerByUserName(username) == null &&
                Customer.getCustomerByName(username) == null && Supporter.getSupporterByUserName(username) == null) {
            try {
                new Seller(username, firstName, lastName, email,
                        phoneNumber, password, 0, extraInfo, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }

    public static void registerManger(String username, String firstName, String lastName,
                                      String email, String phoneNumber, String password,String path) throws Exception {
        if (Seller.getSellerByName(username) == null && Manager.getManagerByUserName(username) == null &&
                Customer.getCustomerByName(username) == null && Supporter.getSupporterByUserName(username) == null) {
            new Manager(username, firstName, lastName, email, phoneNumber, password,path);
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }

    public static void registerCustomer(String username, String firstName, String lastName,
                                        String email, String phoneNumber, String password, String extraInfo, String address, String path) {
        if (Seller.getSellerByName(username) == null && Manager.getManagerByUserName(username) == null &&
                Customer.getCustomerByName(username) == null && Supporter.getSupporterByUserName(username) == null) {
            try {
                new Customer(username, firstName, lastName, email,
                        phoneNumber, password, Double.parseDouble(extraInfo), address, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }
    //TODO handle login and multi accounts login

}
