package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Account;
import Models.Accounts.Seller;
import Models.Logs.Log;
import Models.Logs.SellLog;
import Models.Request.*;
import View.RegisterSellerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SellerAbilitiesManager {

    //handle in network
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

    //handle in network
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

    //handle in network
    public static String showLogInfo(String id) {
        SellLog log = (SellLog) Log.getLogWithId(id);
        return log.toString();
    }

    //handle in network (but need to be checked)
    public static void addProduct(String productId, String productName, String companyName,
                                  double price, Category category, Seller seller, String productExplanation, String specialFeature, String path) throws IOException {
        Product product = new Product(productId, productName, companyName, price,
                seller, category, productExplanation, 0, specialFeature, path);
        new AddProductRequest(RegisterSellerMenu.getCurrentSeller(), product, category);
        //WriteIntoFile.writeProductsIntoFile();
    }

    //handle in network
    public static void sendEditingProductRequest(Product product, Seller seller, String field, String newContentForThisField) throws IOException {
        new EditProductRequest(seller, product, field, newContentForThisField);
    }

    public static void sendRemovingProductRequest(Product product, Seller seller) throws IOException {
        new RemoveProductRequest(seller, product);
    }

    //handle in network
    public static void sendEditingOffRequest(Discount discount, Seller seller, String field, String newContentForThisField) throws IOException {
        new EditOffRequest(seller, discount, field, newContentForThisField);
    }

    //handle in network
    public static Discount addDiscount(String id, String beginningDate, String endingDate,
                                       double discountPercent, String productName) throws IOException {
        Discount discount = new Discount(id, LocalDate.parse(beginningDate), LocalDate.parse(endingDate), discountPercent, Product.getProductByName(productName));
        new AddOffRequest(RegisterSellerMenu.getCurrentSeller(), discount);
        return discount;
    }

    //handle in network
    public static ObservableList<String> showCategories() {
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(Category.showCategories());
        return data;
    }

    //handle in network
    public static ObservableList<String> showProducts(Seller seller) {
        ArrayList<String> products = new ArrayList<>();
        for (Product product : seller.getAllProducts()) {
            products.add(product.getName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(products);
        return data;
    }

    //handle in network
    public static ObservableList<String> viewOffs(Seller seller) {
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(seller.getDiscountInfo(seller));
        return data;
    }

}