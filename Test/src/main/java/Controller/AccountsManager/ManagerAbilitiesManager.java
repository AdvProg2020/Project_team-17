package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Request.Request;

import java.time.LocalDate;
import java.util.ArrayList;

public class ManagerAbilitiesManager {
    public static ArrayList<String> showAllAccounts() {
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
        return allAccounts;
    }


    public static String viewAccountByUsername(String username) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer customer = Customer.getCustomerByName(username);
            return customer.toString();
        } else if (Seller.isThereSellerWithUserName(username)) {
            Seller seller = Seller.getSellerByName(username);
            return seller.toString();
        } else if (Manager.isThereManagerWithUserName(username)) {
            Manager manager = Manager.getManagerByUserName(username);
            return manager.toString();
        } else throw new Exception("There isn't any account with thus username");

    }

    public static String changeField(Manager manager, String field, String newContentForThisField) {
        if (field.equalsIgnoreCase("first name")) {
            manager.changeFirstName(manager, newContentForThisField);
            return"field successfully changed";
        } else if (field.equalsIgnoreCase("last name")) {
            manager.changeLastName(manager, newContentForThisField);
            return"field successfully changed";
        } else if (field.equalsIgnoreCase("email")) {
            manager.changeEmail(manager, newContentForThisField);
            return"field successfully changed";
        } else if (field.equalsIgnoreCase("phone number")) {
            manager.changePhoneNumber(manager, newContentForThisField);
            return"field successfully changed";
        } else if (field.equalsIgnoreCase("password")) {
            manager.changePassword(manager, newContentForThisField);
            return"field successfully changed";
        }else {
            return "enter a valid field";
        }
    }

    public static void deleteUser(String username) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer.deleteCustomer(username);
        } else if (Manager.isThereManagerWithUserName(username)) {
            Manager.deleteManager(username);
        } else if (Seller.isThereSellerWithUserName(username)) {
            Seller.deleteSeller(username);
        } else {
            throw new Exception("there isn't account with this username");
        }
    }

    public static void isThereAccountWithThisUsername(String username) throws Exception {
        if ((!(Manager.isThereManagerWithUserName(username))) && (!(Customer.isThereCustomerWithUserName(username))) && (!(Seller.isThereSellerWithUserName(username)))) {

        } else {
            throw new Exception("There is already an account with this username!");
        }

    }

    public static void createAnotherManager(String username, String firstName, String lastName,
                                            String email, String phoneNumber, String password) {
        new Manager(username, firstName, lastName, email, phoneNumber, password);

    }

    public static void removeProduct(String productId) throws Exception {
        if (Product.isThereProductWithId(productId)) {
            Product product = Product.getProductWithId(productId);
            Product.removeProduct(product);
        } else {
            throw new Exception("There isn't any product with this ID!");
        }
    }

    public static void createDiscountCode(String code, String beginningDate, String endingDate, String discountPercent, String max, int repeat, ArrayList<String> customersName) {
        ArrayList<Customer> customers = new ArrayList<>();
        for (String customer : customersName) {
            customers.add(Customer.getCustomerByName(customer));
        }
        DiscountCode discountCode = new DiscountCode(code, LocalDate.parse(beginningDate), LocalDate.parse(endingDate),
                Double.parseDouble(discountPercent), Double.parseDouble(max), repeat, customers);
        for (Customer customer : customers) {
            customer.addDiscountCode(discountCode);
        }
        System.out.println("discount code created");
    }
    public static void removeDiscountCodeDueToException(String code, ArrayList<String> customersName){
        ArrayList<Customer> customers = new ArrayList<>();
        for (String customer : customersName) {
            customers.add(Customer.getCustomerByName(customer));
        }
        DiscountCode discountCode= DiscountCode.getDiscountCodeWithCode(code);
        DiscountCode.removeDiscountCode(discountCode);
        for (Customer customer : customers) {
            customer.removeDiscountCode(discountCode);
        }
    }

    public static ArrayList<String> viewDiscountCodes() {
        ArrayList<String> discountCodeInfo = new ArrayList<>();
        for (DiscountCode discountCode : DiscountCode.getAllDiscountCodes()) {
            discountCodeInfo.add(discountCode.getDiscountCode());
        }
        return discountCodeInfo;
    }

    public static void isThereDiscountCode(String discountCode) throws Exception {
        if (DiscountCode.isThereDiscountCodeWithThisCode(discountCode)) {

        } else {
            throw new Exception("There isn't any discount code with this code");
        }
    }

    public static String viewDiscountCode(String discountCode) {
        return DiscountCode.getDiscountCodeWithCode(discountCode).toString();
    }

    public static String editDiscountCode(String code, String field, String newContentForThisField) {
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        if (field.equals("starting date")) {
            discountCode.setStartDate(LocalDate.parse(newContentForThisField));
            return"discount code edited successfully";
        } else if (field.equals("ending date")) {
            discountCode.setEndDate(LocalDate.parse(newContentForThisField));
            return"discount code edited successfully";
        } else if (field.equals("discount percent")) {
            discountCode.setDiscountCode(newContentForThisField);
            return"discount code edited successfully";
        } else if (field.equals("maximum discount amount")) {
            discountCode.setMaxDiscountAmount(Double.parseDouble(newContentForThisField));
            return"discount code edited successfully";
        } else if (field.equals("count discount code")) {
            discountCode.setCountDiscountCode(Integer.parseInt(newContentForThisField));
            return"discount code edited successfully";
        }else{
            return "enter a valid field";
        }
    }

    public static void removeDiscountCode(String discountCode) {
        DiscountCode.removeDiscountCode(DiscountCode.getDiscountCodeWithCode(discountCode));
    }

    public static ArrayList<String> showAllRequests() {
        ArrayList<String> requestInfo = new ArrayList<>();
        for (Request request : Request.getAllRequests()) {
            requestInfo.add(request.getId());
        }
        return requestInfo;
    }


    public static String showDetailsOfRequest(String requestId) throws Exception {
        if (Request.isThereRequestById(requestId)) {
            return Request.getRequestById(requestId).toString();
        } else {
            throw new Exception("There isn't request with this id");
        }
    }

    public static String acceptRequest(String requestId) throws Exception {
        if (Request.isThereRequestById(requestId)) {
            Request.getRequestById(requestId).accept();
            Request.deleteRequest(Request.getRequestById(requestId));
            return "request accepted";
        } else {
            throw new Exception("there isn't request with this ID");
        }
    }

    public static void declineRequest(String requestId) throws Exception {
        if (Request.isThereRequestById(requestId)) {
            Request request = Request.getRequestById(requestId);
            Request.deleteRequest(request);
        } else {
            throw new Exception("there isn't request with this ID");
        }
    }

    public static ArrayList<String> showAllCategories() {
        ArrayList<String> showAllCategory = new ArrayList<>();
        for (Category category : Category.getAllCategories()) {
            showAllCategory.add(category.getCategoryName());
        }
        return showAllCategory;
    }

    public static void editCategoryName(Category category, String newName) {
        category.changeCategoryName(category, newName);
    }

    public static void editCategoryFeature(Category category, String newFeature) {
        category.changeSpecialFeatures(category, newFeature);
    }

    public static void addCategory(String name, String feature) throws Exception {
        if (!(Category.isThereCategoryWithName(name))) {
            new Category(name, feature);
        } else {
            throw new Exception("there is a category with this name");
        }
    }

    public static void removeCategory(String name) throws Exception {
        if (Category.isThereCategoryWithName(name)) {
            Category.deleteCategory(Category.getCategoryByName(name));
        } else throw new Exception("there isn't any category with this name");
    }
}

