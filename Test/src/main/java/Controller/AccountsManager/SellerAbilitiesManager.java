package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Seller;
import Models.Logs.SellLog;
import Models.Request.EditOffRequest;
import Models.Request.EditProductRequest;
import View.RegisterManagerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

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
            salesHistory.add(log.toString());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(salesHistory);
        return data;
    }


    public static Product addProduct(String productId, String productName, String companyName,
                                     double price, Category category, Seller seller, String productExplanation, String specialFeature) {
        Product product = new Product(productId, productName, companyName, price,
                seller, category, productExplanation, 0, specialFeature);
        Product.addProduct(product);
        return product;
    }

    public static void sendEditingProductRequest(String id, Seller seller, String field, String newContentForThisField) {
        Product product = Product.getProductWithId(id);
        new EditProductRequest(seller, RegisterManagerMenu.getCurrentManager(), product, field, newContentForThisField);
    }

    public static void removeProduct(Seller seller, String productId) throws Exception {
        if (Product.isThereProductWithId(productId)) {
            Product product = Product.getProductWithId(productId);
            if (seller.doesSellerHaveThisProduct(product)) {
                Product.removeProduct(product);
                seller.removeProduct(seller, product);
            } else {
                throw new Exception("This seller doesn't have this product!");
            }
        } else {
            throw new Exception("There isn't product with this ID!");
        }
    }

    public static void sendEditingOffRequest(String id, Seller seller, String field, String newContentForThisField) {
        Discount discount = Discount.getDiscountById(id);
        new EditOffRequest(seller, RegisterManagerMenu.getCurrentManager(), discount, field, newContentForThisField);
    }


    public static Discount addDiscount(String id, String beginningDate, String endingDate,
                                       double discountPercent, ArrayList<String> productsName) {
        Discount discount = new Discount(id, LocalDate.parse(beginningDate), LocalDate.parse(endingDate), discountPercent, Product.getProductsListByName(productsName));
        return discount;
    }


    public static ObservableList<String> showCategories() {
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(Category.showCategories());
        return data;
    }


    public static void checkProductByID(String id) throws Exception {
        if (Product.isThereProductWithId(id)) {
        } else {
            throw new Exception("There isn't any product with this id");
        }
    }

    public static ArrayList<String> viewOffs(Seller seller) {
        return seller.getDiscountInfo(seller);
    }

    public static void isThereOffByThisId(Seller seller, String id) throws Exception {
        if (seller.isThereDiscountWithThisIdForSeller(seller, id)) {
        } else {
            throw new Exception("you don't have any discount with this id");
        }
    }

    public static ArrayList<String> viewProductsBuyer(Seller seller, String id) {
        ArrayList<String> customers = new ArrayList<>();
        ArrayList<SellLog> logs = seller.getLogs();
        for (SellLog log : logs) {
            for (Product product : log.getAllProducts()) {
                if (product.getProductId().equals(id)) {
                    customers.add(log.getName());
                }
            }
        }
        return customers;
    }

    public static void doesSellerHaveThisProduct(Seller seller, String id) throws Exception {
        if (seller.doesSellerHaveThisProduct(Product.getProductWithId(id))) {

        } else {
            throw new Exception("There isn't any product with this id");
        }
    }


    public static String viewOffByGettingId(Seller seller, String id) {
        Discount discount = Discount.getDiscountById(id);
        String output = discount.toString();
        return output;
    }

}