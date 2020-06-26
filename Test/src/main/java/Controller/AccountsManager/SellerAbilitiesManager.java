package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Seller;
import Models.Logs.Log;
import Models.Logs.SellLog;
import Models.Request.EditOffRequest;
import Models.Request.EditProductRequest;
import Models.Request.RemoveProductRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SellerAbilitiesManager {

    public static void changeField(Seller seller, String field, String newContentForThisField) {
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
        }
    }

    public static ObservableList<String> viewSalesHistory(Seller seller) {
        ArrayList<SellLog> logs = seller.getLogs();
        ArrayList<String> salesHistory = new ArrayList<String>();
        for (SellLog log : logs) {
            salesHistory.add(log.getId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(salesHistory);
        return data;
    }

    public static String showDiscountInfo(String id) {
        SellLog log = (SellLog) Log.getLogWithId(id);
        return log.toString();
    }


    public static Product addProduct(String productId, String productName, String companyName,
                                     double price, Category category, Seller seller, String productExplanation, String specialFeature, String path) {
        Product product = new Product(productId, productName, companyName, price,
                seller, category, productExplanation, 0, specialFeature, path);
        Product.addProduct(product);
        return product;
    }

    public static void sendEditingProductRequest(Product product, Seller seller, String field, String newContentForThisField) {
        new EditProductRequest(seller, product, field, newContentForThisField);
    }

    public static void sendRemovingProductRequest(Product product, Seller seller) {
        new RemoveProductRequest(seller, product);
    }

    public static void sendEditingOffRequest(Discount discount, Seller seller, String field, String newContentForThisField) {
        new EditOffRequest(seller, discount, field, newContentForThisField);
    }

    public static Discount addDiscount(String id, String beginningDate, String endingDate,
                                       double discountPercent, String productName) {
        Discount discount = new Discount(id, LocalDate.parse(beginningDate), LocalDate.parse(endingDate), discountPercent, Product.getProductByName(productName));
        return discount;
    }

    public static ObservableList<String> showCategories() {
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(Category.showCategories());
        return data;
    }

    public static ObservableList<String> showProducts(Seller seller) {
        ArrayList<String> products = new ArrayList<>();
        for (Product product : seller.getAllProducts()) {
            products.add(product.getName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(products);
        return data;
    }


    public static ObservableList<String> viewOffs(Seller seller) {
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(seller.getDiscountInfo(seller));
        return data;
    }

}