package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;

public class SellerAbilitiesManager {
    public void viewAccount(String username) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer customer = Customer.getCustomerByName(username);
            customer.toString();
        } else {
            throw new Exception("There hasn't been any registered account with this username!");
        }
    }

    public void changeField(Seller seller, String field, String newContentForThisField) {
            if (field.equalsIgnoreCase("first name")) {
                seller.changeFirstName(seller, newContentForThisField);
            } else if (field.equalsIgnoreCase("last name")) {
                seller.changeLastName(seller, newContentForThisField);
            } else if (field.equalsIgnoreCase("email")) {
                seller.changeEmail(seller, newContentForThisField);
            } else if (field.equalsIgnoreCase("phone number")) {
                seller.changePhoneNumber(seller, newContentForThisField);
            } else if (field.equalsIgnoreCase("password")) {
                seller.changePassword(seller, newContentForThisField);
                //nemidonam type passwordam bayad check konim ya na
            }
        }

    public String viewFactoryInfo(Seller seller) {
        return seller.getCompanyName();
    }

    //view saleshistory ro bayad bezanim


    public void addProduct(String productId) {
        //baraye request nemidonam chi kar konam

    }

    public void editProduct(String productId) {
        //baraye request esh nemidonam chi kar konam
    }

    public void removeProduct(Seller seller, String productId) throws Exception {
        if (Product.isThereProductWithId(productId)) {
            Product product = Product.getProductWithId(productId);
            if (seller.doesSellerHaveThisProduct(product)) {
                Product.removeProduct(product);
            } else {
                throw new Exception("This seller doesn't have this product!");
            }
        } else {
            throw new Exception("There isn't product with this ID!");
        }
    }


    public static void addOff(Seller seller, Discount newDiscount) {
        //TODO
    }

    public static void editOff(Seller seller, Discount discount) {
        //TODO
    }

    public static void showCategories() {
        Category.showCategories();
    }

    public static void viewBalance(Seller seller) {
        seller.getCredit();
    }
}
