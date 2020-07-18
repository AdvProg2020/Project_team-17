package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Request.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManagerAbilitiesManager {

    //handle in network
    public static ObservableList<String> showAllAccounts() {
        ArrayList<String> allAccounts = new ArrayList<>();
        for (Customer customer : Customer.getAllCustomers()) {
            allAccounts.add(customer.getUserName());
        }
        for (Seller seller : Seller.getAllSellers()) {
            allAccounts.add(seller.getUserName());
        }
        for (Manager manager : Manager.getAllManagers()) {
            allAccounts.add(manager.getUserName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(allAccounts);
        return data;
    }

    //handle in network
    public static String viewAccountByUsername(String username) {
        String s = "";
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer customer = Customer.getCustomerByName(username);
            s = customer.toString();
        } else if (Seller.isThereSellerWithUserName(username)) {
            Seller seller = Seller.getSellerByName(username);
            s = seller.toString();
        } else if (Manager.isThereManagerWithUserName(username)) {
            Manager manager = Manager.getManagerByUserName(username);
            s = manager.toString();
        }
        return s;
    }

    //handle in network
    public static void changeField(Manager manager, String field, String newContentForThisField) {
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
    }

    //handle in network
    public static void deleteUser(String username) {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer.deleteCustomer(username);
        } else if (Manager.isThereManagerWithUserName(username)) {
            Manager.deleteManager(username);
        } else if (Seller.isThereSellerWithUserName(username)) {
            Seller.deleteSeller(username);
        }
    }

    //handle in network
    public static void createAnotherManager(String username, String firstName, String lastName,
                                            String email, String phoneNumber, String password,String path) throws IOException {
        new Manager(username, firstName, lastName, email, phoneNumber, password,path);
    }

    //handle in network
    public static void removeProduct(String name) {
        Product product = Product.getProductByName(name);
        Product.removeProduct(product);
    }

    //handle in network
    public static void createDiscountCode(String code, String beginningDate, String endingDate, String discountPercent, String max, int repeat, String customers) {
        DiscountCode discountCode = new DiscountCode(code, LocalDate.parse(beginningDate), LocalDate.parse(endingDate),
                Double.parseDouble(discountPercent), Double.parseDouble(max), repeat, Customer.getCustomerByName(customers));
        Customer.getCustomerByName(customers).addDiscountCode(discountCode);

    }

    //handle in network
    public static ObservableList<String> viewDiscountCodes() {
        ArrayList<String> discountCodeInfo = new ArrayList<>();
        for (DiscountCode discountCode : DiscountCode.getAllDiscountCodes()) {
            discountCodeInfo.add(discountCode.getDiscountCode());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(discountCodeInfo);
        return data;
    }

    //handle in network
    public static String viewDiscountCode(String discountCode) {
        return DiscountCode.getDiscountCodeWithCode(discountCode).toString();
    }

    //handle in network
    public static void editDiscountCode(DiscountCode discountCode, String field, String newContentForThisField) {
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

    //handle in network
    public static void removeDiscountCode(String discountCode) {
        DiscountCode.removeDiscountCode(DiscountCode.getDiscountCodeWithCode(discountCode));
    }

    //handle in network
    public static ObservableList<String> showAllRequests() {
        ArrayList<String> requestInfo = new ArrayList<>();
        for (Request request : Request.getAllRequests()) {
            requestInfo.add(request.getId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(requestInfo);
        return data;
    }

    //handle in network
    public static String showDetailsOfRequest(String requestId) {
        return Request.getRequestById(requestId).toString();
    }

    //handle in network
    public static void acceptRequest(String requestId) {
        Request.getRequestById(requestId).accept();
        Request.deleteRequest(Request.getRequestById(requestId));
    }

    //handle in network
    public static void declineRequest(String requestId) {
        Request request = Request.getRequestById(requestId);
        Request.deleteRequest(request);
    }

    //handle in network
    public static ObservableList<String> showAllCategories() {
        ArrayList<String> showAllCategory = new ArrayList<>();
        for (Category category : Category.getAllCategories()) {
            showAllCategory.add(category.getCategoryName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(showAllCategory);
        return data;
    }

    //handle in network
    public static ObservableList<String> showAllProducts() {
        ArrayList<String> showAllProducts = new ArrayList<>();
        for (Product product : Product.getAllProducts()) {
            showAllProducts.add(product.getName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(showAllProducts);
        return data;
    }

    //handle in network
    public static void editCategory(Category category, String field, String newContentForThisField) {
        if (field.equals("name")) {
            category.changeCategoryName(category, newContentForThisField);
        } else if (field.equals("feature")) {
            category.changeSpecialFeatures(category, newContentForThisField);
        }
    }

    //handle in network
    public static void addCategory(String name, String feature) {
        new Category(name, feature);
    }

    //handle in network
    public static void removeCategory(String name) {
        Category.deleteCategory(Category.getCategoryByName(name));
    }
}