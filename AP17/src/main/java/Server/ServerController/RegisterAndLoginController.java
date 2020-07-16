package Server.ServerController;

import Client.Client;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import sun.jvm.hotspot.opto.MachNode;

import java.io.IOException;

public class RegisterAndLoginController {
    public static void registerSeller() {
        String info = Client.receiveMessage();
        String[] splitInfo;
        splitInfo = info.split("\\s");
        if (Seller.getSellerByName(splitInfo[0]) == null && Manager.getManagerByUserName(splitInfo[0]) == null && Customer.getCustomerByName(splitInfo[0]) == null) {
            try {
                new Seller(splitInfo[0], splitInfo[1], splitInfo[2], splitInfo[3],
                        splitInfo[4], splitInfo[5], 0, splitInfo[6]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }

    public static void registerManger() throws Exception {
        String info = Client.receiveMessage();
        String[] splitInfo;
        splitInfo = info.split("\\s");

        if (Manager.getManagerByUserName(splitInfo[0]) == null && Customer.getCustomerByName(splitInfo[0]) == null && Seller.getSellerByName(splitInfo[0]) == null) {
            new Manager(splitInfo[0], splitInfo[1], splitInfo[2], splitInfo[3], splitInfo[4], splitInfo[5]);
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }

    public static void registerCustomer() {
        String info = Client.receiveMessage();
        String[] splitInfo;
        splitInfo = info.split("\\s");
        if (Seller.getSellerByName(splitInfo[0]) == null && Manager.getManagerByUserName(splitInfo[0]) == null && Customer.getCustomerByName(splitInfo[0]) == null) {
            try {
                new Customer(splitInfo[0], splitInfo[1], splitInfo[2], splitInfo[3],
                        splitInfo[4], splitInfo[5], Double.parseDouble(splitInfo[6]), splitInfo[7]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }
    //TODO handle login and multi accounts login

}
