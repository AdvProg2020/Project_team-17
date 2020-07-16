package Client.ClientController.AccountsController;

import Client.Client;
import Models.Accounts.Account;
import Models.Category;
import Models.DiscountCode;
import Models.Product;
import Models.Request.Request;
import View.RegisterManagerMenu;

import java.util.ArrayList;

public class ManagerController {
    public static String showAdminInfo() throws Exception {
        String func = "Show Admin Info";
        Client.sendMessage(func);
        if (RegisterManagerMenu.getCurrentManager() == null) {
            throw new Exception("a manager should login first!");
        } else {
            String adminUsername = RegisterManagerMenu.getCurrentManager().getUserName();
            Client.sendMessage(adminUsername);
            Object data = Client.receiveObject();
            if (data instanceof Exception) {
                throw new Exception("account with this username doesn't found");
            } else {
                return String.valueOf(data);
            }
        }
    }

    public static void editAdminInfo(String field, String newContent) throws Exception {
        String func = "Edit Admin Info";
        Client.sendMessage(func);
        if (RegisterManagerMenu.getCurrentManager() == null) {
            throw new Exception("a manager should login first!");
        } else {
            Object[] toSend = new Object[2];
            toSend[0] = field;
            toSend[1] = newContent;
            Client.sendObject(toSend);
            Client.receiveObject();
        }
    }

    public static ArrayList<Request> showAdminRequests() {
        String func = "Show Admin Requests";
        Client.sendMessage(func);

        // TODO in khat payine ro motmaen nistam doroste ya na
        //Client.sendMessage(RegisterManagerMenu.getCurrentManager().getUserName());

        Object response = Client.receiveObject();
        return (ArrayList<Request>) response;
    }


    public static String showRequest(String requestId) throws Exception {
        String func = "Show Request";
        Client.sendMessage(func);

        Client.sendMessage(requestId);
        Request request = (Request) Client.receiveObject();

        if (request == null) {
            throw new Exception("there isn't any request with this id");
        } else {
//            if (request.getState().equals(RequestStateEnum.PENDING_TO_ACCEPT)) {
            return request.toString();
            // }
        }
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


    public static void editDiscountCode(String code, String field, String newContentForThisField) throws Exception {
        Client.sendMessage("Edit Sale Info");

        Object[] toSend = new Object[3];
        toSend[0] = String.valueOf(code);
        toSend[1] = field;
        toSend[2] = newContentForThisField;
        Client.sendObject(toSend);
        Object response = Client.receiveObject();

        if (response instanceof Exception) {
            throw new Exception("there isn't any discount code with this code");
        }
    }

    public static void addDiscountCode(String code, String beginningDate, String endingDate, String discountPercent, String max, int repeat, String customers) throws Exception {
        Client.sendMessage("Add Discount Code");
        String info;
        info = code + " " + beginningDate + " " + endingDate + " " + discountPercent + " " + max + " " + repeat + " " + customers;
        Client.sendMessage(info);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there is a discount code with this code");
    }

    public static String showDiscountCodeDetails(String code) throws Exception {
        String func = "Show Account Details";
        Client.sendMessage(func);

        Client.sendMessage(code);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any discount code with this code");
        else {
            return (String) response;
        }
    }

    public static ArrayList<Account> showAllAccounts() {
        String func = "Show All Accounts";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Account>) response;
    }

    public static String showAccountDetails(String username) throws Exception {
        String func = "Show Account Details";
        Client.sendMessage(func);

        Client.sendMessage(username);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any account with this username");
        else {
            return (String) response;
        }
    }

    public static void deleteAccount(String username) throws Exception {
        String func = "Delete User";
        Client.sendMessage(func);

        Client.sendMessage(username);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any account with this username");
    }

    public static void addManager(String username, String firstName, String lastName,
                                  String email, String phoneNumber, String password) throws Exception {
        String func = "Add Manager Account";
        Client.sendMessage(func);

        String info = username + " " + firstName + " " + lastName + " " + email + " " + phoneNumber + " " + password;

        Client.sendMessage(info);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there is an account with this username");
    }

    public static void deleteProduct(String productId) throws Exception {
        String func = "Delete Product";
        Client.sendMessage(func);

        Client.sendMessage(productId);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any product with this id");
    }

    public static ArrayList<Category> showCategories() throws Exception {
        String func = "Show Categories";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Category>) response;
    }

    public static void deleteCategory(String categoryName) throws Exception {
        String func = "Delete Category";
        Client.sendMessage(func);

        Client.sendMessage(categoryName);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any category with this name");
    }

    public static void addCategory(String name, String feature) throws Exception {
        String func = "Add Category";
        Client.sendMessage(func);

        String info = name + " " + feature;

        Client.sendMessage(info);
        Object response = Client.receiveObject();

        if (response instanceof Exception) {
            throw new Exception("there is a category with this name");
        }
    }

    public static void deleteDiscountCode(String discountCode) throws Exception {
        String func = "Delete Discount Code";
        Client.sendMessage(func);

        Client.sendMessage(discountCode);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any discount code with this code");
    }

    public static void editCategory(String categoryName, String field, String newContentForThisField) throws Exception {
        Client.sendMessage("Edit Category Info");

        Object[] toSend = new Object[3];
        toSend[0] = String.valueOf(categoryName);
        toSend[1] = field;
        toSend[2] = newContentForThisField;
        Client.sendObject(toSend);
        Object response = Client.receiveObject();

        if (response instanceof Exception) {
            throw new Exception("there isn't any category with this name");
        }
    }

    public static ArrayList<Product> showAllProducts() throws Exception {
        String func = "Show Products";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Product>) response;
    }

}

