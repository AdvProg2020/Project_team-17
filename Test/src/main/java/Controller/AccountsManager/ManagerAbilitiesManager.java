package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Request.Request;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerAbilitiesManager {
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

    public static void deleteUser(String username) {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer.deleteCustomer(username);
        } else if (Manager.isThereManagerWithUserName(username)) {
            Manager.deleteManager(username);
        } else if (Seller.isThereSellerWithUserName(username)) {
            Seller.deleteSeller(username);
        }
    }

    public static void createAnotherManager(String username, String firstName, String lastName,
                                            String email, String phoneNumber, String password) {
        new Manager(username, firstName, lastName, email, phoneNumber, password);
    }

    public static void removeProduct(String name) {
        Product product = Product.getProductByName(name);
        Product.removeProduct(product);
    }

    public static void createDiscountCode(String code, String beginningDate, String endingDate, String discountPercent, String max, int repeat, String customers) {
        DiscountCode discountCode = new DiscountCode(code, LocalDate.parse(beginningDate), LocalDate.parse(endingDate),
                Double.parseDouble(discountPercent), Double.parseDouble(max), repeat, Customer.getCustomerByName(customers));
        Customer.getCustomerByName(customers).addDiscountCode(discountCode);

    }

    public static void removeDiscountCodeDueToException(String code, ArrayList<String> customersName) {
        ArrayList<Customer> customers = new ArrayList<>();
        for (String customer : customersName) {
            customers.add(Customer.getCustomerByName(customer));
        }
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        DiscountCode.removeDiscountCode(discountCode);
        for (Customer customer : customers) {
            customer.removeDiscountCode(discountCode);
        }
    }

    public static ObservableList<String> viewDiscountCodes() {
        ArrayList<String> discountCodeInfo = new ArrayList<>();
        for (DiscountCode discountCode : DiscountCode.getAllDiscountCodes()) {
            discountCodeInfo.add(discountCode.getDiscountCode());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(discountCodeInfo);
        return data;
    }

    public static String viewDiscountCode(String discountCode) {
        return DiscountCode.getDiscountCodeWithCode(discountCode).toString();
    }

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

    public static void removeDiscountCode(String discountCode) {
        DiscountCode.removeDiscountCode(DiscountCode.getDiscountCodeWithCode(discountCode));
    }

    public static ObservableList<String> showAllRequests() {
        ArrayList<String> requestInfo = new ArrayList<>();
        for (Request request : Request.getAllRequests()) {
            requestInfo.add(request.getId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(requestInfo);
        return data;
    }


    public static String showDetailsOfRequest(String requestId) {
        return Request.getRequestById(requestId).toString();

    }

    public static void acceptRequest(String requestId) {
        Request.getRequestById(requestId).accept();
        Request.deleteRequest(Request.getRequestById(requestId));
    }

    public static void declineRequest(String requestId) {
        Request request = Request.getRequestById(requestId);
        Request.deleteRequest(request);

    }

    public static ObservableList<String> showAllCategories() {
        ArrayList<String> showAllCategory = new ArrayList<>();
        for (Category category : Category.getAllCategories()) {
            showAllCategory.add(category.getCategoryName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(showAllCategory);
        return data;
    }

    public static ObservableList<String> showAllProducts() {
        ArrayList<String> showAllProducts = new ArrayList<>();
        for (Product product : Product.getAllProducts()) {
            showAllProducts.add(product.getName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(showAllProducts);
        return data;
    }

    public static void editCategory(Category category, String field, String newContentForThisField) {
        if (field.equals("name")) {
            category.changeCategoryName(category, newContentForThisField);
        } else if (field.equals("name")) {
            category.changeSpecialFeatures(category, newContentForThisField);
        }
    }

    public static void addCategory(String name, String feature) {
        new Category(name, feature);
    }

    public static void removeCategory(String name) {
        Category.deleteCategory(Category.getCategoryByName(name));

    }
}