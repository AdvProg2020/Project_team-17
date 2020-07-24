package Client.ClientController;

import Client.Client;
import Client.ClientController.AccountsController.CCustomerController;
import Client.ClientController.AccountsController.CManagerController;
import Client.ClientController.AccountsController.CSellerController;
import Client.ClientController.AccountsController.CSupporterController;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Accounts.Supporter;
import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.AccountsController.SellerController;
import Server.ServerController.AccountsController.SupporterController;

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
                new Seller(split[0], split[1], split[2], split[3], split[4], split[5], 0, split[6], split[7]);
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


    public static void loginCustomer(String dataToLogin) throws Exception {
        String func = "Login Customer";
        Client.sendMessage(func);

        Client.sendObject(dataToLogin);
        try {
            Object response = Client.receiveObject();
            Customer customer = (Customer) response;
            CustomerController.setCustomer(customer);
            CCustomerController.setCustomer(customer);
            CustomerController.addOnlineCustomer(customer);
            CCustomerController.addOnlineCustomer(customer);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void registerCustomer(String dataToRegister) throws Exception {
        String func = "Register Customer";
        Client.sendMessage(func);

        Client.sendObject(dataToRegister);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = dataToRegister.split("\\s");
                new Customer(split[0], split[1], split[2], split[3], split[4], split[5], Double.parseDouble(split[6]), split[7], split[8]);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void loginSupporter(String dataToLogin) throws Exception {
        String func = "Login Supporter";
        Client.sendMessage(func);

        Client.sendObject(dataToLogin);
        try {
            Object response = Client.receiveObject();
            Supporter supporter = (Supporter) response;
            SupporterController.setSupporter(supporter);
            CSupporterController.setSupporter(supporter);
            SupporterController.addOnlineSupporters(supporter);
            CSupporterController.addOnlineSupporters(supporter);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void registerSupporter(String dataToRegister) throws Exception {
        String func = "Register Supporter";
        Client.sendMessage(func);

        Client.sendObject(dataToRegister);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = dataToRegister.split("\\s");
                new Supporter(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
