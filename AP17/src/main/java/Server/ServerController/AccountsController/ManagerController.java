package Server.ServerController.AccountsController;

import Client.Client;
import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Category;
import Models.DiscountCode;
import Models.Enums.RequestStateEnum;
import Models.Product;
import Models.Request.Request;
import View.RegisterManagerMenu;

import java.time.LocalDate;
import java.util.ArrayList;

public class ManagerController {
    public static String showManagerInfo() {
        if (RegisterManagerMenu.getCurrentManager() == null) {
            Client.sendObject(new Exception("manager should first login"));
        }
        //String username = Client.receiveMessage();
        //Client.sendMessage(Manager.getManagerByUserName(username).toString());
        return (RegisterManagerMenu.getCurrentManager().toString());
    }

    public static void editManagerInfo() throws Exception {
        Object[] receivedItems = (Object[]) Client.receiveObject();
        String field = (String) receivedItems[0];
        String newContentForThisField = (String) receivedItems[1];
        if (RegisterManagerMenu.getCurrentManager() == null) {
            throw new Exception("manager should login first!");
        } else {
            Manager manager = RegisterManagerMenu.getCurrentManager();
            if (field.equalsIgnoreCase("first name")) {
                manager.changeFirstName(manager, newContentForThisField);
            } else if (field.equalsIgnoreCase("last name")) {
                manager.changeLastName(manager, newContentForThisField);
            } else if (field.equalsIgnoreCase("email")) {
                manager.changeEmail(manager, newContentForThisField);
            } else if (field.equalsIgnoreCase("phone number")) {
                manager.changePhoneNumber(manager, newContentForThisField);
            } else if (field.equalsIgnoreCase("password")) {
                manager.changePassword(manager, newContentForThisField);
            }
            Client.sendMessage("Success!");
        }
    }

    public static void showManagerRequests() {
        ArrayList<Request> allRequests = new ArrayList<>();
        allRequests.addAll(Request.showRequestsNeedToBeAccepted());
        Client.sendObject(allRequests);
    }

    public static void showRequest() {
        Request request = Request.getRequestById(Client.receiveMessage());
        if (request != null) {
            if (request.getState().equals(RequestStateEnum.PENDING_TO_ACCEPT)) {
                Client.sendMessage(request.toString());
            }
        } else {
            Client.sendObject(new Exception("there isn't any request with this id"));
        }
    }

    public static void processRequest(String requestId, String requestState) throws Exception {
        Object[] toSend = new Object[2];
        toSend[0] = requestId;
        toSend[1] = requestState;
        Client.sendObject(toSend);
        Object response = Client.receiveObject();
        if (response instanceof Exception) {
            throw new Exception("there isn't request with this ID");
        }
    }

    public static void showDiscountCode() {
        ArrayList<DiscountCode> allDiscountCode = new ArrayList<>();
        allDiscountCode.addAll(DiscountCode.getAllDiscountCodes());
        Client.sendObject(allDiscountCode);
    }

    public static void editSaleInfo() {
        Object[] receivedData = (Object[]) Client.receiveObject();
        String code = (String) receivedData[0];
        String field = (String) receivedData[1];
        String newContentForThisField = (String) receivedData[2];
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        if (field.equals("starting date")) {
            discountCode.setStartDate(LocalDate.parse(newContentForThisField));
        } else if (field.equals("ending date")) {
            discountCode.setEndDate(LocalDate.parse(newContentForThisField));
        } else if (field.equals("discount percent")) {
            discountCode.setDiscountCode(newContentForThisField);
        } else if (field.equals("maximum discount amount")) {
            discountCode.setMaxDiscountAmount(Double.parseDouble(newContentForThisField));
        } else if (field.equals("count discount code")) {
            discountCode.setCountDiscountCode(Integer.parseInt(newContentForThisField));
        }
    }

    public static void addDiscountCode() {
        String info = Client.receiveMessage();
        String[] splitInfo;
        splitInfo = info.split("\\s");

        if (DiscountCode.getDiscountCodeWithCode(splitInfo[0]) == null) {
            DiscountCode discountCode = new DiscountCode(splitInfo[0], LocalDate.parse(splitInfo[1]), LocalDate.parse(splitInfo[2]),
                    Double.parseDouble(splitInfo[3]), Double.parseDouble(splitInfo[4]), Integer.parseInt(splitInfo[5]), Customer.getCustomerByName(splitInfo[6]));
            Customer.getCustomerByName(splitInfo[6]).addDiscountCode(discountCode);
        } else {
            Client.sendObject(new Exception("there is a discount code with this code"));
        }
    }

    public static void showDiscountCodeInfo() {
        String code = Client.receiveMessage();
        if (DiscountCode.getDiscountCodeWithCode(code) != null) {
            Client.sendMessage(DiscountCode.getDiscountCodeWithCode(code).toString());
        } else {
            Client.sendObject(new Exception("there isn't any account with this username"));
        }
    }

    public static void showAllAccounts() {
        ArrayList<Account> list = new ArrayList<>();
        for (Customer customer : Customer.getAllCustomers()) {
            list.add(customer);
        }
        for (Seller seller : Seller.getAllSellers()) {
            list.add(seller);
        }
        for (Manager manager : Manager.getAllManagers()) {
            list.add(manager);
        }
        Client.sendObject(list);
    }

    public static void showAccountInfo() {
        String username = Client.receiveMessage();
        Account account;
        if (Customer.getCustomerByName(username) != null) {
            account = Customer.getCustomerByName(username);
            Client.sendMessage(account.toString());
        } else if (Manager.getManagerByUserName(username) != null) {
            account = Manager.getManagerByUserName(username);
            Client.sendMessage(account.toString());
        } else if (Seller.getSellerByName(username) != null) {
            account = Seller.getSellerByName(username);
            Client.sendMessage(account.toString());
        } else {
            Client.sendObject(new Exception("there isn't any account with this username"));
        }
    }

    public static void deleteUser() throws Exception {
        String username = Client.receiveMessage();
        if (Customer.getCustomerByName(username) != null) {
            Customer.deleteCustomer(username);
        } else if (Manager.getManagerByUserName(username) != null) {
            Manager.deleteManager(username);
        } else if (Seller.getSellerByName(username) != null) {
            Seller.deleteSeller(username);
        } else {
            Client.sendObject(new Exception("there isn't any account with this username"));
        }
    }

    public static void addManager() throws Exception {
        String info = Client.receiveMessage();
        String[] splitInfo;
        splitInfo = info.split("\\s");

        if (Manager.getManagerByUserName(splitInfo[0]) == null && Customer.getCustomerByName(splitInfo[0]) == null && Seller.getSellerByName(splitInfo[0]) == null) {
            new Manager(splitInfo[0], splitInfo[1], splitInfo[2], splitInfo[3], splitInfo[4], splitInfo[5]);
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }

    public static void deleteProduct() {
        String productId = Client.receiveMessage();
        Product product = Product.getProductByName(productId);
        if (product != null) {
            Product.removeProduct(product);
        } else {
            Client.sendObject(new Exception("there isn't any product with this name"));
        }
    }

    public static void showCategories() {
        ArrayList<Category> allCategories = new ArrayList<>();
        allCategories.addAll(Category.getAllCategories());
        Client.sendObject(allCategories);
    }

    public static void deleteCategory() {
        String categoryName = Client.receiveMessage();
        Category category = Category.getCategoryByName(categoryName);
        if (category != null) {
            Category.deleteCategory(category);
        } else {
            Client.sendObject(new Exception("there isn't any category with this name"));
        }
    }

    public static void addCategory() throws Exception {
        String info = Client.receiveMessage();
        String[] splitInfo;
        splitInfo = info.split("\\s");
        if (Category.getCategoryByName(splitInfo[0]) == null) {
            new Category(splitInfo[0], splitInfo[1]);
        } else {
            Client.sendObject(new Exception("there is a category with this name"));
        }
    }

    public static void deleteDiscountCode() {
        String code = Client.receiveMessage();
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        if (discountCode != null) {
            DiscountCode.removeDiscountCode(discountCode);
        } else {
            Client.sendObject(new Exception("there isn't any discount code with this code"));
        }
    }

    public static void editCategoryName() {
        Object[] receivedData = (Object[]) Client.receiveObject();
        String categoryName = (String) receivedData[0];
        String field = (String) receivedData[1];
        String newContentForThisField = (String) receivedData[2];
        Category category = Category.getCategoryByName(categoryName);
        if (field.equals("name")) {
            category.changeCategoryName(category, newContentForThisField);
        } else if (field.equals("feature")) {
            category.changeSpecialFeatures(category, newContentForThisField);
        }
    }

    public static void showAllProducts() {
        ArrayList<Product> allProduct = new ArrayList<>();
        allProduct.addAll(Product.getAllProducts());
        Client.sendObject(allProduct);
    }
}

