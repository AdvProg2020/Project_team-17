package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Enums.DiscountEnum;
import Models.Request.Request;

import java.util.ArrayList;
import java.util.Date;

public class ManagerAbilitiesManager {
    public static ArrayList<String> showAllAccounts(){
        ArrayList<String> allAccounts= new ArrayList<>();
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


    public static String viewAccountByUsername(String username){
        String output= "";
        for (Customer customer : Customer.getAllCustomers()) {
            if(customer.getUserName().equals(username)){
                output=customer.toString();
            }
        }
        for (Seller seller : Seller.getAllSellers()) {
            if(seller.getUserName().equals(username)){
                output = seller.toString();
            }
        }
        for (Manager manager : Manager.getAllManagers()) {
            if(manager.getUserName().equals(username)){
                output = manager.toString();
            }
        }
        return output;
    }

    public static void changeField( Manager manager, String field, String newContentForThisField) {
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


    public void changeTypeOfAccount(Manager manager, String username, String type) {
        //aslan nemidonam
    }

    public static void deleteUser(String username)  {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer.deleteCustomer(username);
        } else if (Manager.isThereManagerWithUserName(username)) {
            Manager.deleteManager(username);
        } else if (Seller.isThereSellerWithUserName(username)) {
            Seller.deleteSeller(username);
        }
    }
    public static void isThereAccountWithThisUsername(String username) throws Exception{
        if ((!(Manager.isThereManagerWithUserName(username))) && (!(Customer.isThereCustomerWithUserName(username))) && (!(Seller.isThereSellerWithUserName(username)))) {

        }else {
            throw new Exception("There is already an account with this username!");
        }

    }

    public static void createAnotherManager(String username, String firstName, String lastName,
                                     String email, String phoneNumber, String password)  {
            new Manager(username, firstName, lastName, email, phoneNumber, password);

    }

    public static void removeProduct( String productId) throws Exception {
        if (Product.isThereProductWithId(productId)) {
            Product product = Product.getProductWithId(productId);
            Product.removeProduct(product);
        } else {
            throw new Exception("There isn't any product with this ID!");
        }
    }

    public void createDiscountCode() {
    }

    public static ArrayList<String>viewDiscountCodes() {
        //fek konam hamin k code ro faghat neshon bede okaye
        ArrayList<String> discountCodeInfo = new ArrayList<>();
        for (DiscountCode discountCode : DiscountCode.getAllDiscountCodes()) {
            discountCodeInfo.add(discountCode.getDiscountCode());
        }
        return discountCodeInfo;
    }
    public static void isThereDiscountCode(String discountCode ) throws Exception{
        if(DiscountCode.isThereDiscountCodeWithThisCode(discountCode)){

        }else {
            throw new Exception("There isn't any discount code with this code");
        }
    }

    public static String viewDiscountCode( String discountCode) {
        return DiscountCode.getDiscountCodeWithCode(discountCode).toString();
    }

    public static void editDiscountCode(String code,String field,String newContentForThisField) {
        DiscountCode discountCode=DiscountCode.getDiscountCodeWithCode(code);
        if(field.equals("starting date")){
            discountCode.setStartDate(new Date(newContentForThisField));
        }else if(field.equals("ending date")){
            discountCode.setEndDate(new Date(newContentForThisField));
        }else if(field.equals("discount percent")){
            discountCode.setDiscountCode(newContentForThisField);
        }else if(field.equals("maximum discount amount")){
            discountCode.setMaxDiscountAmount(Double.parseDouble(newContentForThisField));
        }else if(field.equals("count discount code")){
            discountCode.setCountDiscountCode(Integer.parseInt(newContentForThisField));
        }
    }

    public static void removeDiscountCode(String discountCode) {
        DiscountCode.removeDiscountCode(DiscountCode.getDiscountCodeWithCode(discountCode));
    }
    public static void isThereRequestWithThisId(String id) throws Exception{
        if(Request.hasRequestById(id)) {
        }
        else throw new Exception("There isn't request with this id");
    }
    public static ArrayList<String> showAllRequests(){
        ArrayList<String> requestInfo = new ArrayList<>();
        for (Request request : Request.getAllRequests()) {
            requestInfo.add(request.getId());
        }
        return requestInfo;
    }


    public static String showDetailsOfRequest( String requestId) {
        return Request.getRequestById(requestId).toString();
    }
    public static void acceptRequest( String requestId) {
        //TODO
    }
    public static void declineRequest( String requestId) {
        //TODO
    }
    public static ArrayList<Category> showAllCategories(){
        ArrayList<Category> showAllCategory = new ArrayList<>();
        for (Category category : Category.getAllCategories()) {
            showAllCategory.add(category);
        }
        return showAllCategory;
    }

    public static void editCategoryName(String oldName, String newName) {
        Category category = Category.getCategoryByName(oldName);
        category.changeCategoryName(category, newName);
    }

    public static void editCategoryFeature(String name, String newFeature) {
        Category category = Category.getCategoryByName(name);
        category.changeSpecialFeatures(category, newFeature);
    }

    public static void addCategory(String name, String feature) {
        new Category(name, feature);
    }

    public static void removeCategory(String name) {
        Category.deleteCategory(Category.getCategoryByName(name));
    }
}
