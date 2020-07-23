package Server.ServerController;

import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Product;
import Models.Request.Request;

import java.io.Serializable;
import java.util.ArrayList;

public class DataBaseForServer implements Serializable {
    private static ArrayList<Manager> allMangers = new ArrayList<>();
    private static ArrayList<Customer> allCustomers = new ArrayList<>();
    private static ArrayList<Seller> allSellers = new ArrayList<>();
    private static ArrayList<Product> allProducts = new ArrayList<>();
    private static ArrayList<Request> allRequests = new ArrayList<>();


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

    public static void addRequest(Request request) {
        allRequests.add(request);
    }

    public static Request getRequest(String id) {
        for (Request request : allRequests) {
            if (request.getId().equals(id)) {
                return request;
            }
        }
        return null;
    }

    public static ArrayList<Request> getAllRequests() {
        return allRequests;
    }
}
