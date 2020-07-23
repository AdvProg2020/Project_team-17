package Client.ClientController.AccountsController;

import Client.Client;
import Models.Accounts.Account;
import Models.Accounts.Manager;
import Models.Category;
import Models.DiscountCode;
import Models.Product;
import Models.Request.Request;

import java.util.ArrayList;

public class CManagerController {

    public static void addAdminAccount(String newAdminDetails) throws Exception {

        String func = "Add Admin Account";
        Client.sendMessage(func);

        Client.sendMessage(newAdminDetails);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("username already exists");
        else {
            return;
        }

    }

    public static void showManagerInfo() {
        String func = "Show Manager Info";
        Client.sendMessage(func);
    }

    public static void editManagerInfo(String field, String newContentForThisField) {
        String func = "Edit Manager Info " + field + " " + newContentForThisField;
        Client.sendMessage(func);
    }

    public static void loginManager(String username) {
        String func = "Login Manager " + username;
        Client.sendMessage(func);
    }

    public static void registerManager(String username, String firstName, String lastName, String email, String phoneNumber, String password, String path) {
        String func = "Register Manager " + username + "+" + firstName + "+" + lastName + "+" + email + "+" + phoneNumber + "+" + password + "+" + path;
        Client.sendMessage(func);
    }

    public static ArrayList<Request> showManagerRequests() {
        String func = "Show manager Requests";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Request>) response;
    }


    public static void showRequest() {
        String func = "Show Request";
        Client.sendMessage(func);
    }

    public static void processRequest() {
        Object[] receivedArray = (Object[]) Client.receiveObject();
        String requestId = (String) receivedArray[0];
        String acceptState = (String) receivedArray[1];
        Request request = Request.getRequestById(requestId);
        if (acceptState.equals("accepted")) {
            Request.getRequestById(requestId).accept();
            Request.deleteRequest(Request.getRequestById(requestId));
        } else if (acceptState.equals("rejected")) {
            Request.deleteRequest(request);
        }
    }

    public static ArrayList<DiscountCode> showDiscountCodes() {
        String func = "Show Discount Code";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<DiscountCode>) response;
    }


    public static void editDiscountCode() {
        String func = "Edit Sale Info";
        Client.sendMessage(func);
    }

    public static void addDiscountCode() {
        String func = "Add Discount Code";
        Client.sendMessage(func);
    }

    public static void addAuction() {
        String func = "Add Auction";
        Client.sendMessage(func);
    }

    public static void showDiscountCodeDetails() throws Exception {
        String func = "Show Discount Code Details";
        Client.sendMessage(func);
    }

    public static ArrayList<Account> showAllAccounts() {
        String func = "Show All Accounts";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Account>) response;
    }

    public static void showAccountDetails() {
        String func = "Show Account Details";
        Client.sendMessage(func);
    }

    public static void showAccountStatus() {
        String func = "Show Account Status";
        Client.sendMessage(func);
    }

    public static void defineLeastAmount() {
        String func = "Define Least Amount";
        Client.sendMessage(func);
    }

    public static void showLogDetails() {
        String func = "Show Log Details";
        Client.sendMessage(func);
    }

    public static void deleteAccount() {
        String func = "Delete User";
        Client.sendMessage(func);
    }

    public static void addManager() {
        String func = "Add Manager Account";
        Client.sendMessage(func);
    }

    public static void addSupporter() {
        String func = "Add Supporter Account";
        Client.sendMessage(func);
    }

    public static void deleteProduct() {
        String func = "Delete Product";
        Client.sendMessage(func);
    }

    public static ArrayList<Category> showCategories() throws Exception {
        String func = "Show Categories";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Category>) response;
    }

    public static void deleteCategory() {
        String func = "Delete Category";
        Client.sendMessage(func);
    }

    public static void addCategory() {
        String func = "Add Category";
        Client.sendMessage(func);
    }

    public static void deleteDiscountCode() {
        String func = "Delete Discount Code";
        Client.sendMessage(func);
    }

    public static void editCategory() {
        String func = "Edit Category Info";
        Client.sendMessage(func);
    }

    public static ArrayList<Product> showAllProducts() throws Exception {
        String func = "Show Products";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Product>) response;
    }

}