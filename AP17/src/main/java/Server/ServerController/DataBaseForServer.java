package Server.ServerController;

import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;

import java.io.Serializable;
import java.util.ArrayList;

public class DataBaseForServer implements Serializable {
    private static ArrayList<Manager> allMangers = new ArrayList<>();
    private static ArrayList<Customer> allCustomers = new ArrayList<>();
    private static ArrayList<Seller> allSellers = new ArrayList<>();

    public static void addManager(Manager manager) {
        allMangers.add(manager);
    }

    public static Manager getManager(String username) {
        for (Manager manger : allMangers) {
            if (manger.getUserName().equals(username)) {
                return manger;
            }
        }
        return null;
    }

    public static void addSeller(Seller seller) {
        allSellers.add(seller);
    }

    public static Seller getSeller(String username) {
        for (Seller seller : allSellers) {
            if (seller.getUserName().equals(username)) {
                return seller;
            }
        }
        return null;
    }

    public static void addCustomer(Customer customer) {
        allCustomers.add(customer);
    }

    public static Customer getCustomer(String username) {
        for (Customer customer : allCustomers) {
            if (customer.getUserName().equals(username)) {
                return customer;
            }
        }
        return null;
    }


}
