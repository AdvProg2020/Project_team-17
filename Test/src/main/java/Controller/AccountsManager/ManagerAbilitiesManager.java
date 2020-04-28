package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;

public class ManagerAbilitiesManager {

    public void viewAccount(String username) throws Exception {
        if (Manager.isThereManagerWithUserName(username)) {
            Manager manager = Manager.getManagerByUserName(username);
            manager.toString();
        } else {
            throw new Exception("There hasn't been any registered account with this username!");
        }
    }

    public void changeField(Manager manager, String username, String field, String newContentForThisField) throws Exception {
        if (Manager.isThereManagerWithUserName(username)) {
            Account account = Manager.getManagerByUserName(username);
            //nemidonam type haro bayad check konim ya na
            if (field.equalsIgnoreCase("first name")) {
                account.changeFirstName(account, newContentForThisField);
            } else if (field.equalsIgnoreCase("last name")) {
                account.changeLastName(account, newContentForThisField);
            } else if (field.equalsIgnoreCase("email")) {
                account.changeEmail(account, newContentForThisField);
            } else if (field.equalsIgnoreCase("phone number")) {
                account.changePhoneNumber(account, newContentForThisField);
            } else if (field.equalsIgnoreCase("password")) {
                account.changePassword(account, newContentForThisField);
            }
        } else {
            throw new Exception("There hasn't been any registered account with this username!");
        }
    }

    public void changeTypeOfAccount(Manager manager, String username, String type) {
        //aslan nemidonam
    }

    public void deleteUser(String username) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer.deleteCustomer(username);
        } else if (Manager.isThereManagerWithUserName(username)) {
            Manager.deleteManager(username);
        } else if (Seller.isThereSellerWithUserName(username)) {
            Seller.deleteSeller(username);
        } else {
            throw new Exception("There hasn't been any registered account with this username!");
        }

    }

    public void createAnotherManager(String username, String firstName, String lastName,
                                     String email, String phoneNumber, String password) throws Exception {
        if ((!(Manager.isThereManagerWithUserName(username))) && (!(Customer.isThereCustomerWithUserName(username))) && (!(Seller.isThereSellerWithUserName(username)))) {
            new Manager(username, firstName, lastName, email, phoneNumber, password);
        } else {
            throw new Exception("There is already an account with this username!");
        }
    }

    public void removeProduct(Manager manager, String productId) throws Exception {
        //manager hame productaro mitone bebine na? age na k bayad check konim bebinim manager on product ro dare ya na
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

    public void editCategoryName(String oldName, String newName) {
        Category category = Category.getCategoryByName(oldName);
        category.changeCategoryName(category, newName);
    }

    public void editCategoryFeature(String name, String newFeature) {
        Category category = Category.getCategoryByName(name);
        category.changeSpecialFeatures(category, newFeature);
    }

    public void addCategory(String name, String feature) {
        //safe 22 doc neveshte faghat add category vali fek kardam belakhare y esmi chiziam bayad begire vorodi
        new Category(name, feature);
    }

    public void removeCategory(String name) {
        Category.deleteCategory(Category.getCategoryByName(name));
    }
}
