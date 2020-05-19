package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Seller;
import Models.Logs.SellLog;
import Models.Request.EditOffRequest;
import Models.Request.EditProductRequest;
import View.RegisterAndLoginMenu;

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
            //nemidonam type passwordam bayad check konim ya na
        }
    }

    public static String viewFactoryInfo(Seller seller) {
        return seller.getCompanyName();
    }

    public static ArrayList<String> viewSalesHistory(Seller seller) throws Exception {
        if (seller.getLogs() != null) {
            ArrayList<SellLog> logs = seller.getLogs();
            ArrayList<String> salesHistory = new ArrayList<String>();
            for (SellLog log : logs) {
                salesHistory.add(log.toString());
            }
            return salesHistory;
        } else {
            throw new Exception("you haven't sold anything yet");
        }
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
        new EditProductRequest(seller, RegisterAndLoginMenu.getCurrentManager(), product, field, newContentForThisField);
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
        new EditOffRequest(seller, RegisterAndLoginMenu.getCurrentManager(), discount, field, newContentForThisField);
    }


    public static Discount addDiscount(Seller seller, String id, String beginningDate, String endingDate,
                                       double discountPercent, ArrayList<String> productsName) {
        Discount discount = new Discount(id, LocalDate.parse(beginningDate), LocalDate.parse(endingDate), discountPercent, Product.getProductsListByName(productsName));
        seller.addDiscountForSeller(seller, discount);
        return discount;
    }


    public static ArrayList<String> showCategories() {
        return Category.showCategories();
    }

    public static double viewBalance(Seller seller) {
        return seller.getCredit();
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
        ArrayList<String> buyers = new ArrayList<>();
        ArrayList<SellLog> logs = seller.getLogs();
        for (SellLog log : logs) {
            if (log.doesLogHaveThisProduct(id)) {
                buyers.add(log.getName());
            }
        }
        return buyers;
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