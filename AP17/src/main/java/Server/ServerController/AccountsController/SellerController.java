package Server.ServerController.AccountsController;

import Client.Client;
import Client.ClientController.AccountsController.CSellerController;
import Models.Accounts.Seller;
import Models.Auction;
import Models.Category;
import Models.Discount;
import Models.Logs.Log;
import Models.Logs.SellLog;
import Models.Product;
import Models.Request.*;
import View.RegisterSellerMenu;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SellerController {

    private static ArrayList<Seller> onlineSellers = new ArrayList<>();
    private static Seller seller;

    public static Seller getSeller() {
        return seller;
    }

    public static void setSeller(Seller seller) {
        SellerController.seller = seller;
    }

    public static ArrayList<Seller> getOnlineSellers() {
        return onlineSellers;
    }


    public static void showSellerInfo() {
        if (RegisterSellerMenu.getCurrentSeller() == null) {
            Client.sendObject(new Exception("seller should first login"));
        }
        String username = Client.receiveMessage();
        Client.sendMessage(Seller.getSellerByName(username).toString());
    }

    public static void editSellerInfo() throws Exception {
        Object[] receivedItems = (Object[]) Client.receiveObject();
        String field = (String) receivedItems[0];
        String newContentForThisField = (String) receivedItems[1];
        if (RegisterSellerMenu.getCurrentSeller() == null) {
            throw new Exception("seller should login first!");
        } else {
            Seller seller = RegisterSellerMenu.getCurrentSeller();
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
            Client.sendMessage("Success!");
        }
    }

    public static void showSellerLogs() {
        ArrayList<SellLog> logs = RegisterSellerMenu.getCurrentSeller().getLogs();
        ArrayList<String> salesHistory = new ArrayList<String>();
        for (SellLog log : logs) {
            salesHistory.add(log.getId());
        }
        Client.sendObject(salesHistory);
    }

    public static void showLog() {
        SellLog sellLog = (SellLog) Log.getLogWithId(Client.receiveMessage());
        if (sellLog != null) {
            Client.sendMessage(sellLog.toString());
        } else {
            Client.sendObject(new Exception("there isn't any log with this id"));
        }
    }

    public static void showCategories() {
        ArrayList<Category> allCategories = new ArrayList<>();
        allCategories.addAll(Category.getAllCategories());
        Client.sendObject(allCategories);
    }

    public static void showDiscounts() {
        ArrayList<String> sellerDiscounts = new ArrayList<>();
        sellerDiscounts.addAll(RegisterSellerMenu.getCurrentSeller().getDiscountInfo(RegisterSellerMenu.getCurrentSeller()));
        Client.sendObject(sellerDiscounts);
    }

    public static void showProducts() {
        ArrayList<String> products = new ArrayList<>();
        for (Product product : RegisterSellerMenu.getCurrentSeller().getAllProducts()) {
            products.add(product.getName());
        }
        Client.sendObject(products);
    }

    public static void addProductRequest() throws IOException {
        Product product = (Product) Client.receiveObject();
        new AddProductRequest(RegisterSellerMenu.getCurrentSeller(), product, product.getCategory());
    }

    public static void editProductRequest() throws Exception {
        Object[] receivedData = (Object[]) Client.receiveObject();
        String productId = (String) receivedData[0];
        if (Product.getProductWithId(productId) != null) {
            Seller seller = Seller.getSellerByName((String) receivedData[1]);
            String field = (String) receivedData[1];
            String newContentForThisField = (String) receivedData[2];
            new EditProductRequest(seller, Product.getProductWithId(productId), field, newContentForThisField);
        } else {
            Client.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void addDiscountRequest() throws IOException {
        Discount discount = (Discount) Client.receiveObject();
        new AddOffRequest(RegisterSellerMenu.getCurrentSeller(), discount);
    }

    public static void editDiscountRequest() throws Exception {
        Object[] receivedData = (Object[]) Client.receiveObject();
        Discount discount = (Discount) receivedData[0];
        if (Discount.getAllDiscounts().contains(discount)) {
            Seller seller = Seller.getSellerByName((String) receivedData[1]);
            String field = (String) receivedData[1];
            String newContentForThisField = (String) receivedData[2];
            new EditOffRequest(seller, discount, field, newContentForThisField);
        } else {
            Client.sendObject(new Exception("there isn't any discount with this id"));
        }
    }

    public static void removeProductRequest() throws Exception {
        Object[] receivedData = (Object[]) Client.receiveObject();
        Product product = (Product) receivedData[0];
        Seller seller = (Seller) receivedData[1];
        if (Product.getAllProducts().contains(product)) {
            new RemoveProductRequest(seller, product);
        } else {
            Client.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void addAuction(String productID, String endDate) {
        try {
            Date endDateAsDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(endDate);
            Product product = Product.getProductWithId(productID);

            Auction auction = new Auction(product, endDateAsDate);
            auction.start();
        } catch (ParseException e) {
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



