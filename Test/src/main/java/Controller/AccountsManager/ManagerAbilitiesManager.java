package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;

import java.util.ArrayList;

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

    public void createDiscountCode(Manager manager) {

    }

    public void viewDiscountCodes(Manager manager) {
    }

    public void viewDiscount(Manager manager, String discountCode) {
    }

    public void editDiscount(Manager manager, String discountCode) {
    }

    public void removeDiscount(Manager manager, String discountCode) {
    }

    public void getDetailsOfRequest(Manager manager, String requestId) {
    }

    public void acceptRequest(Manager manager, String requestId) {
    }

    public void declineRequest(Manager manager, String requestId) {
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
