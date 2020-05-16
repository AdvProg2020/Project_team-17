package Controller.AccountsManager;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Seller;
import Models.Enums.DiscountEnum;
import Models.Enums.ProductEnum;
import Models.Logs.SellLog;
import Models.Request.AddProductRequest;
import Models.Request.EditOffRequest;
import Models.Request.EditProductRequest;
import Models.Request.Request;
import View.RegisterAndLoginMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SellerAbilitiesManager {
    public void viewAccount(String username) throws Exception {
        if (Customer.isThereCustomerWithUserName(username)) {
            Customer customer = Customer.getCustomerByName(username);
            customer.toString();
        } else {
            throw new Exception("There hasn't been any registered account with this username!");
        }
    }

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

    public static ArrayList<String> viewSalesHistory(Seller seller) {
        ArrayList<SellLog> logs = seller.getLogs();
        ArrayList<String> salesHistory = new ArrayList<String>();
        for (SellLog log : logs) {
            salesHistory.add(log.toString());
        }
        return salesHistory;
    }


    public static Product addProduct(String productId, ProductEnum productStatus, String productName, String companyName,
                                     double price, Category category, Seller seller, String productExplanation, String specialFeature) {
        Product product = new Product(productId, productName, companyName, price,
                seller, category, productExplanation, 0, specialFeature);
        product.setProductState(ProductEnum.PRODUCING);
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
        new EditOffRequest(seller, RegisterAndLoginMenu.getCurrentManager(), discount);
    }

    public static Discount editOff(String id, String field, String newContentForThisField) {
        Discount discount = Discount.getDiscountById(id);
        if (field.equals("discount percent")) {
            discount.setDiscountPercent(Double.parseDouble(newContentForThisField));
        } else if (field.equals("start date")) {
            discount.setStartDate(new Date(newContentForThisField));
        } else if (field.equals("end date")) {
            discount.setEndDate(new Date(newContentForThisField));
        }
        discount.setDiscountState(DiscountEnum.EDITING);
        return discount;
    }

    public static Discount addDiscount(Seller seller, String id, String beginningDate, String endingDate,
                                       double discountPercent, ArrayList<String> productsName) {
        Discount discount = new Discount(id, new Date(beginningDate), new Date(endingDate), discountPercent, Product.getProductsListByName(productsName));
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
                buyers.add(log.getBuyerName());
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