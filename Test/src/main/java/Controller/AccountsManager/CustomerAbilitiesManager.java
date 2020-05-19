package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Seller;
import Models.Logs.BuyLog;
import Models.Logs.SellLog;
import View.PurchasingProcessMenus.ReceivingInformationPage;

import java.time.LocalDate;
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

    public static void changeField(Customer customer, String field, String newContentForThisField) {
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

    public static ArrayList<String> viewOrders(Customer customer) throws Exception {
        ArrayList<String> buyLogs = new ArrayList<>();
        if (customer.getBuyLog() != null) {
            for (BuyLog log : customer.getBuyLog()) {
                buyLogs.add(log.showOrders());
            }
            return buyLogs;
        } else {
            throw new Exception("there isn't any order");
        }
    }

    public static String showOrder(String id) throws Exception {
        if (BuyLog.isThereBuyLogWithThisId(id)) {
            return BuyLog.getButLogWithId(id).toString();
        } else {
            throw new Exception("There isn't log with this id");
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
            throw new Exception("There isn't any product with this id");
        }
    }


    public static ArrayList<String> showDiscountCodes(Customer customer) throws Exception {
        ArrayList<String> discountCodes = new ArrayList<>();
        if (customer.getDiscountCodes() != null) {
            discountCodes.add(customer.getDiscountCodes().toString());
            return discountCodes;
        } else throw new Exception("There isn't any discount code already");
    }

    public static void checkAndPay(Customer customer, String code) throws Exception {
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        if (customer.getCart().totalPriceWithDiscount(discountCode) <= customer.getCredit()) {
            pay(customer, code);
        } else
            throw new Exception("there isn't enough money in your card!");
    }

    public static void pay(Customer customer, String code) {
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        double amountForPay = customer.getCart().totalPriceWithDiscount(discountCode);
        double sum = customer.getCart().totalPriceOfProductInCart();
        double discountAmount = DiscountCode.calculateDiscountAmount(sum, discountCode);
        ArrayList<Product> boughtProducts = customer.getCart().getProductsInCart();
        ArrayList<Seller> sellers = getSellers(boughtProducts);
        for (Seller seller : sellers) {
            ArrayList<Product> productsThatThisSellerSold = new ArrayList<>();
            for (Product product : boughtProducts) {
                if(product.getSeller().equals(seller)){
                    productsThatThisSellerSold.add(product);
                }
            }
            SellLog sellLog = new SellLog(LocalDate.now(),amountForPay,ReceivingInformationPage.getAddress(),ReceivingInformationPage.getPhoneNum(),
                    customer.getUserName(),boughtProducts,false,discountAmount);
            seller.addLogToSellLog(sellLog);
            BuyLog buyLog = new BuyLog(LocalDate.now(),amountForPay,ReceivingInformationPage.getAddress(),ReceivingInformationPage.getPhoneNum(),
                    seller.getUserName(),boughtProducts,false,discountAmount);
            customer.addLogToBuyLog(buyLog);
        }customer.payMoney(customer, amountForPay);
        for (Product product : boughtProducts) {
            product.getSeller().addMoneyToCredit(product);
        }
        for (Product product : boughtProducts) {
            customer.getCart().removeProductAfterPurchasingFromCart(product);
        }
    }

    public static void checkDiscountCodeValidation(String code) throws Exception {
        if (DiscountCode.isThereDiscountCodeWithThisCode(code)) {
        } else {
            throw new Exception("this code isn't valid");
        }
    }
    public static ArrayList<Seller> getSellers(ArrayList<Product> boughtProducts){
        ArrayList<Seller> sellers = new ArrayList<>();
        for (Product product : boughtProducts) {
            sellers.add(product.getSeller());
        }
        return sellers;
    }

}

