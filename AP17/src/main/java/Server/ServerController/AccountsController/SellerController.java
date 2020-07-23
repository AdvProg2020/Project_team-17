package Server.ServerController.AccountsController;

import Client.Client;
import Client.ClientController.AccountsController.CSellerController;
import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Logs.Log;
import Models.Logs.SellLog;
import Models.Request.*;
import Server.ClientHandler;
import Server.ServerController.DataBaseForServer;
import View.RegisterSellerMenu;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public static void showManagerInfo() throws Exception {
        if (getSeller() == null) {
            ClientHandler.sendObject(new Exception("there isn't any seller logged in"));
        } else {
            String username = ClientHandler.receiveMessage();
            Seller seller = DataBaseForServer.getSeller(username);
            setSeller(seller);
            ClientHandler.sendObject(seller.toString());
        }
    }

    public static void editManagerInfo() throws Exception {
        String receivedItems = (String) ClientHandler.receiveObject();

        Seller seller = DataBaseForServer.getSeller(receivedItems);
        if (seller == null) {
            ClientHandler.sendObject(new Exception("there isn't any seller with this username"));
        } else {
            ClientHandler.sendObject("Success!");
        }
    }


    public static void showSellerLogs() {
        ArrayList<SellLog> logs = new ArrayList<>(getSeller().getLogs());
        Client.sendObject(logs);
    }

    public static void showLog() {
        SellLog sellLog = (SellLog) DataBaseForServer.getLog(Client.receiveMessage());
        if (sellLog != null) {
            Client.sendMessage(sellLog.toString());
        } else {
            Client.sendObject(new Exception("there isn't any log with this id"));
        }
    }

    public static void showCategories() {
        ArrayList<Category> allCategories = new ArrayList<>(DataBaseForServer.getAllCategories());
        ClientHandler.sendObject(allCategories);
    }

    public static void showDiscounts() {
        ArrayList<Discount> sellerDiscounts = new ArrayList<>(getSeller().getAllDiscount());
        Client.sendObject(sellerDiscounts);
    }

    public static void showProducts() {
        ArrayList<Product> products = new ArrayList<>(getSeller().getAllProducts());
        Client.sendObject(products);
    }

    public static void addProductRequest() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getProduct(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is a product with this id"));
        } else if (DataBaseForServer.getCategory(split[5]) == null) {
            ClientHandler.sendObject(new Exception("there isn't any category with this name"));
        } else {
            ClientHandler.sendObject("Done");
            Product product = new Product(split[0], split[1], split[2],
                    Double.parseDouble(split[3]), DataBaseForServer.getSeller(split[4]),
                    DataBaseForServer.getCategory(split[5]), split[6], Double.parseDouble(split[7]), split[8], split[9]);
            DataBaseForServer.addProduct(new Product(split[0], split[1], split[2],
                    Double.parseDouble(split[3]), getSeller(),
                    DataBaseForServer.getCategory(split[5]), split[6], 0, split[8], split[9]));
            DataBaseForServer.addRequest(new AddProductRequest(getSeller(), product, DataBaseForServer.getCategory(split[5])));
        }
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



