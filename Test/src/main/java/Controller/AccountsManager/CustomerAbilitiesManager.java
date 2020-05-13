package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Manager;

import java.util.ArrayList;

public class CustomerAbilitiesManager {
    public void viewAccount(String username) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer customer = Customer.getCustomerByName(username);
            customer.toString();
        } else {
            throw new Exception("There hasn't been any registered account with this username!");
        }
    }

    public static void changeField(Customer customer, String field, String newContentForThisField){
        if (field.equalsIgnoreCase("first name")) {
            customer.changeFirstName(customer, newContentForThisField);
        } else if (field.equalsIgnoreCase("last name")) {
            customer.changeLastName(customer, newContentForThisField);
        } else if (field.equalsIgnoreCase("email")) {
            customer.changeEmail(customer, newContentForThisField);
        } else if (field.equalsIgnoreCase("phone number")) {
            customer.changePhoneNumber(customer, newContentForThisField);
        } else if (field.equalsIgnoreCase("password")) {
            customer.changePassword(customer, newContentForThisField);
        }
    }



    public static void rateProduct(Customer customer, Product product, double score) throws Exception {
        Cart cart = customer.getCart();
        if (Product.isThereProductWithId(product.getProductId())) {
            if (cart.isThereProductInCart(product)) {
                product.addScoreForProduct(customer, product, score);
            } else {
                throw new Exception("There isn't this product in customer's cart");
            }
        } else {
            throw new Exception("");//nemidonam asan lazeme k in exception ro handle konim ya na
        }

    }


    public static ArrayList<String> showDiscountCodes(Customer customer) {
        ArrayList<String> discountCodes= new ArrayList<>();
        discountCodes.add(customer.getDiscountCodes().toString());
        return discountCodes;
    }

    public void purchase(Customer customer, Product product) {
        //nemidonam asan
    }
    //view orders o nemkidonam chi kar bayad kard

}
