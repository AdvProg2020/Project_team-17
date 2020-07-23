package Client.ClientController.AccountsController;

import Client.Client;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Category;
import Models.Discount;
import Models.Logs.BuyLog;
import Models.Logs.SellLog;
import Models.Product;

import Server.ServerController.DataBaseForServer;
import View.RegisterSellerMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CSellerController {

    private static ArrayList<Seller> onlineSellers = new ArrayList<>();
    private static Seller seller;

    public static Seller getSeller() {
        return seller;
    }

    public static void setSeller(Seller seller) {
        CSellerController.seller = seller;
    }

    public static ArrayList<Seller> getOnlineSellers() {
        return onlineSellers;
    }

    public static String showSellerInfo() throws Exception {
        if (getSeller() == null) {
            throw new Exception("there isn't any seller logged in");
        }
        String func = "Show Seller Info";
        Client.sendMessage(func);

        String username = getSeller().getUserName();
        Client.sendMessage(username);

        try {
            Object data = Client.receiveObject();
            return String.valueOf(data);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void editSellerInfo(String dataToEdit) throws Exception {
        String func = "Edit Seller Info";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = getSeller().getUserName();
        toSend[1] = dataToEdit;
        Client.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Success")) {
                String[] split = dataToEdit.split("\\s");
                Seller seller = DataBaseForServer.getSeller(getSeller().getUserName());
                String field = split[0];
                String newContentForThisField = split[1];
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
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObservableList<String> showSalesHistory() throws Exception {
        ArrayList<SellLog> allLogs;
        String func = "Show Sales History";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allLogs = (ArrayList<SellLog>) response;
        ArrayList<String> showAllLogs = new ArrayList<>();
        for (SellLog sellLog : allLogs) {
            showAllLogs.add(sellLog.getId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(showAllLogs);
        return data;

    }

    public static String showLog(String id) throws Exception {
        String func = "Show Sell Log";
        Client.sendMessage(func);

        Client.sendMessage(String.valueOf(id));
        SellLog sellLog = (SellLog) Client.receiveObject();

        if (sellLog != null) {
            String requestData = sellLog.toString();
            return requestData;
        } else {
            throw new Exception("there isn't any log with this id");
        }
    }

    public static ObservableList<String> showCategories() {
        ArrayList<Category> allCategories;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Categories";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allCategories = (ArrayList<Category>) response;
        for (Category category : allCategories) {
            info.add(category.getCategoryName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;
    }

    public static ObservableList<String> showDiscounts() {
        ArrayList<Discount> allDiscount;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Discounts";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allDiscount = (ArrayList<Discount>) response;
        for (Discount discount : allDiscount) {
            info.add(discount.getDiscountId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;
    }

    public static ObservableList<String> showProducts() {
        ArrayList<Product> allProducts;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Products";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allProducts = (ArrayList<Product>) response;
        for (Product product : allProducts) {
            info.add(product.getProductId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;
    }


    public static void addProductRequest(String productId, String productName, String companyName,
                                         double price, Category category, Seller seller, String productExplanation, String specialFeature, String path) {
        String func = "Add Product Request";
        Client.sendMessage(func);
        Product product = new Product(productId, productName, companyName, price,
                seller, category, productExplanation, 0, specialFeature, path);
        Client.sendObject(product);
    }

    public static void editProductRequest(int productId, Seller seller, String field, String newContentForThisField) throws Exception {

        String func = "Edit Product Request";
        Client.sendMessage(func);

        Object[] toSend = new Object[4];
        toSend[0] = productId;
        toSend[1] = seller;
        toSend[2] = field;
        toSend[3] = newContentForThisField;
        Client.sendObject(toSend);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any product with this id");
    }

    public static void addDiscountRequest(String id, String beginningDate, String endingDate,
                                          double discountPercent, String productName) throws IOException {
        String func = "Add Discount Request";
        Client.sendMessage(func);
        Discount discount = new Discount(id, LocalDate.parse(beginningDate), LocalDate.parse(endingDate), discountPercent, Product.getProductByName(productName));
        Client.sendObject(discount);
    }

    public static void editDiscountRequest(Discount discount, Seller seller, String field, String newContentForThisField) throws Exception {

        String func = "Edit Discount Request";
        Client.sendMessage(func);

        Object[] toSend = new Object[4];
        toSend[0] = discount;
        toSend[1] = seller;
        toSend[2] = field;
        toSend[3] = newContentForThisField;
        Client.sendObject(toSend);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any discount with this id");
    }

    public static void removeProductRequest(Product product, Seller seller) throws Exception {

        String func = "Remove Product Request";
        Client.sendMessage(func);

        Object[] toSend = new Object[4];
        toSend[0] = product;
        toSend[1] = seller;
        Client.sendObject(toSend);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any product with this id");
    }

    public static void addAuction() {
        String func = "Add Auction";
        Client.sendMessage(func);
    }
}
