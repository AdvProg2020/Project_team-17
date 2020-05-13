package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Logs.BuyLog;
import View.ReceivingInformationPage;
import View.RegisterAndLoginMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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
    //TODO view orders
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

    public static boolean canPay(Customer customer,String code){
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        if(customer.getCart().totalPriceWithDiscount(discountCode)<=customer.getCredit()){
            return true;
        }else
            return false;
    }
    public static void pay(Customer customer , String code){
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        double amountForPay = customer.getCart().totalPriceWithDiscount(discountCode);
        double sum = customer.getCart().totalPriceOfProductInCart();
        double discountAmount = DiscountCode.calculateDiscountAmount(sum,discountCode);
        customer.payMoney(customer,amountForPay);
        ArrayList<Product> boughtProducts = customer.getCart().getProductsInCart();
        //random id ro bayad dorost konim
        BuyLog buyLog =new BuyLog("random id",new Date(ReceivingInformationPage.getDate()),amountForPay,ReceivingInformationPage.getAddress(),
                ReceivingInformationPage.getPhoneNum(),customer.getUserName(),boughtProducts,false,discountAmount, RegisterAndLoginMenu.getCurrentSeller().getUserName());
        customer.addLogToBuyLog(buyLog);
    }

    public static void checkDiscountCodeValidation(String code) throws Exception {
        if(DiscountCode.isThereDiscountCodeWithThisCode(code)){

        }else {
            throw new Exception("this code isn't valid");
        }
    }

}
