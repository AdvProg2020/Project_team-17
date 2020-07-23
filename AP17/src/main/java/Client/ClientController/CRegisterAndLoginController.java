package Client.ClientController;

import Client.Client;
import Client.ClientController.AccountsController.CManagerController;
import Client.ClientController.AccountsController.CSellerController;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.AccountsController.SellerController;

public class CRegisterAndLoginController {

    public static void registerManager(String dataToRegister) throws Exception {
        String func = "Register Manager";
        Client.sendMessage(func);

        Client.sendObject(dataToRegister);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = dataToRegister.split("\\s");
                new Manager(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void loginManager(String dataToLogin) throws Exception {
        String func = "Login Manager";
        Client.sendMessage(func);

        Client.sendObject(dataToLogin);
        try {
            Object response = Client.receiveObject();
            Manager manager = (Manager) response;
            ManagerController.setManager(manager);
            CManagerController.setManager(manager);
            ManagerController.addOnlineManager(manager);
            CManagerController.addOnlineManager(manager);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void registerSeller(String dataToRegister) throws Exception {
        String func = "Register Seller";
        Client.sendMessage(func);

        Client.sendObject(dataToRegister);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = dataToRegister.split("\\s");
                new Seller(split[0], split[1], split[2], split[3], split[4], split[5], 0, split[7], split[8]);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void loginSeller(String dataToLogin) throws Exception {
        String func = "Login Seller";
        Client.sendMessage(func);

        Client.sendObject(dataToLogin);
        try {
            Object response = Client.receiveObject();
            Seller seller = (Seller) response;
            SellerController.setSeller(seller);
            CSellerController.setSeller(seller);
            SellerController.addOnlineSeller(seller);
            CSellerController.addOnlineSeller(seller);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
