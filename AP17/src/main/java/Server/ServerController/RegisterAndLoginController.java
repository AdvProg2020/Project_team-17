package Server.ServerController;

import Client.ClientController.AccountsController.CCustomerController;
import Client.ClientController.AccountsController.CManagerController;
import Client.ClientController.AccountsController.CSellerController;
import Client.ClientController.AccountsController.CSupporterController;
import Models.Accounts.*;
import Server.ClientHandler;
import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.AccountsController.SellerController;
import Server.ServerController.AccountsController.SupporterController;

import java.io.IOException;

public class RegisterAndLoginController {
    public static void registerManager() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split(",");

        if (DataBaseForServer.getManager(split[0]) != null && DataBaseForServer.getCustomer(split[0]) != null && DataBaseForServer.getSeller(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is an account with this username"));
        } else {
            ClientHandler.sendObject("Done");
            DataBaseForServer.addManager(new Manager(split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
        }
    }

    public static void loginManager() {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        Manager manager = DataBaseForServer.getManager(split[0]);
        if (manager != null) {
            if (manager.getPassword().equals(split[1])) {
                System.out.println(manager.toString());
                ClientHandler.sendObject(manager);
                ManagerController.setManager(manager);
                CManagerController.setManager(manager);
                ManagerController.addOnlineManager(manager);
                CManagerController.addOnlineManager(manager);
            } else {
                ClientHandler.sendObject(new Exception("wrong password"));
            }
        } else {
            ClientHandler.sendObject(new Exception("wrong username"));
        }
    }

    public static void registerSeller() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getManager(split[0]) != null && DataBaseForServer.getCustomer(split[0]) != null && DataBaseForServer.getSeller(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is an account with this username"));
        } else {
            ClientHandler.sendObject("Done");
            DataBaseForServer.addSeller(new Seller(split[0], split[1], split[2], split[3], split[4], split[5], 0, split[6], split[7]));
        }
    }

    public static void loginSeller() {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        Seller seller = DataBaseForServer.getSeller(split[0]);

        if (seller != null) {
            if (seller.getPassword().equals(split[1])) {
                ClientHandler.sendObject(seller);
                SellerController.setSeller(seller);
                CSellerController.setSeller(seller);
                SellerController.addOnlineSeller(seller);
                CSellerController.addOnlineSeller(seller);
            } else {
                ClientHandler.sendObject(new Exception("wrong password"));
            }
        } else {
            ClientHandler.sendObject(new Exception("wrong username"));
        }
    }

    public static void registerCustomer() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getManager(split[0]) != null && DataBaseForServer.getCustomer(split[0]) != null && DataBaseForServer.getSeller(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is an account with this username"));
        } else {
            ClientHandler.sendObject("Done");
            DataBaseForServer.addCustomer(new Customer(split[0], split[1], split[2], split[3], split[4], split[5], Double.parseDouble(split[6]), split[7], split[8]));
        }
    }

    public static void loginCustomer() {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        Customer customer = DataBaseForServer.getCustomer(split[0]);

        if (customer != null) {
            if (customer.getPassword().equals(split[1])) {
                ClientHandler.sendObject(customer);
                CustomerController.setCustomer(customer);
                CCustomerController.setCustomer(customer);
                CustomerController.addOnlineCustomer(customer);
                CCustomerController.addOnlineCustomer(customer);
            } else {
                ClientHandler.sendObject(new Exception("wrong password"));
            }
        } else {
            ClientHandler.sendObject(new Exception("wrong username"));
        }
    }

    public static void loginSupporter() {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        Supporter supporter = DataBaseForServer.getSupporter(split[0]);

        if (supporter != null) {
            if (supporter.getPassword().equals(split[1])) {
                ClientHandler.sendObject(supporter);
                SupporterController.setSupporter(supporter);
                CSupporterController.setSupporter(supporter);
                SupporterController.addOnlineSupporters(supporter);
                CSupporterController.addOnlineSupporters(supporter);
            } else {
                ClientHandler.sendObject(new Exception("wrong password"));
            }
        } else {
            ClientHandler.sendObject(new Exception("wrong username"));
        }
    }


}
