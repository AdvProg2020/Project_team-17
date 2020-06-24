package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Seller;
import Models.Logs.BuyLog;
import Models.Logs.SellLog;
import View.PurchasingProcessMenus.ReceivingInformationPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerAbilitiesManager {

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

    public static ObservableList<String> viewOrders(Customer customer) {
        ArrayList<String> orders = new ArrayList<>();
        for (BuyLog buyLog : customer.getBuyLog()) {
            orders.add(buyLog.getId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(orders);
        return data;
    }

    public static String showOrder(String id) {
        return BuyLog.getButLogWithId(id).toString();
    }

    public static ObservableList<String> showDiscountCodes(Customer customer) {
        ArrayList<String> discountCodes = new ArrayList<>();
        for (DiscountCode discountCode : customer.getDiscountCodes()) {
            discountCodes.add(discountCode.getDiscountCode());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(discountCodes);
        return data;
    }

    public static String showDiscountCodeInfo(String id) {
        return DiscountCode.getDiscountCodeWithCode(id).toString();
    }

    public static String finalPay(Customer customer, DiscountCode discountCode) {
        if (canPay(customer, discountCode)) {
            pay(customer, discountCode);
            return "payment done";
        } else return "there isn't enough money in your card!";
    }

    public static boolean canPay(Customer customer, DiscountCode discountCode) {
        if (customer.getCart().totalPriceWithDiscount(discountCode) <= customer.getCredit()) {
            return true;
        }
        return false;
    }
    public static void pay(Customer customer, DiscountCode discountCode) {
        double amountForPay = customer.getCart().totalPriceWithDiscount(discountCode);
        double sum = customer.getCart().totalPriceOfProductInCart();
        double discountAmount = DiscountCode.calculateDiscountAmount(sum, discountCode);
        ArrayList<Product> boughtProducts = customer.getCart().getProductsInCart();
        ArrayList<Seller> sellers = getSellers(boughtProducts);
        for (Seller seller : sellers) {
            ArrayList<Product> productsThatThisSellerSold = new ArrayList<>();
            for (Product product : boughtProducts) {
                if (product.getSeller().equals(seller)) {
                    productsThatThisSellerSold.add(product);
                }
            }
            SellLog sellLog = new SellLog(LocalDate.now(), amountForPay, ReceivingInformationPage.getAddress(), ReceivingInformationPage.getPhoneNum(),
                    customer.getUserName(), boughtProducts, false, discountAmount);
            seller.addLogToSellLog(sellLog);
            BuyLog buyLog = new BuyLog(LocalDate.now(), amountForPay, ReceivingInformationPage.getAddress(), ReceivingInformationPage.getPhoneNum(),
                    seller.getUserName(), boughtProducts, false, discountAmount);
            customer.addLogToBuyLog(buyLog);
        }
        customer.payMoney(customer, amountForPay);
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

    public static ArrayList<Seller> getSellers(ArrayList<Product> boughtProducts) {
        ArrayList<Seller> sellers = new ArrayList<>();
        for (Product product : boughtProducts) {
            sellers.add(product.getSeller());
        }
        return sellers;
    }

}