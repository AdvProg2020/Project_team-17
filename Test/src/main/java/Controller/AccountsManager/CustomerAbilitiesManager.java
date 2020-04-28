package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Manager;

public class CustomerAbilitiesManager {
    public void viewAccount(String username) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer customer = Customer.getCustomerByName(username);
            customer.toString();
        } else {
            throw new Exception("There hasn't been any registered account with this username!");
        }
    }

    public void changeField(Manager manager, String username, String field, String newContentForThisField) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Account account = Customer.getCustomerByName(username);
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

    public void increaseProduct(Customer customer, Product product) throws Exception {
        Cart cart = customer.getCart();
        if (cart.isThereProductInCart(product)) {
            cart.increaseNumberOfProduct(product);
        } else {
            throw new Exception("There isn't any product in cart!");
        }

    }

    public void decreaseProduct(Customer customer, Product product) throws Exception {
        Cart cart = customer.getCart();
        if (cart.isThereProductInCart(product)) {
            cart.decreaseNumberOfProduct(product);
        } else {
            throw new Exception("There isn't any product in cart!");
        }
    }

    public void rateProduct(Customer customer, Product product, double score) throws Exception {
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

    public void showProductsInCart(Customer customer) {
        Cart cart = customer.getCart();
        cart.showProductsOfCart();
    }

    public void showTotalPrice(Customer customer) {
        Cart cart = customer.getCart();
        cart.totalPriceOfProductInCart();
    }

    public void showDiscountCodes(Customer customer) {
        customer.getDiscountCodes();
    }

    public void purchase(Customer customer, Product product) {
        //nemidonam asan
    }
    //view orders o nemkidonam chi kar bayad kard

}
