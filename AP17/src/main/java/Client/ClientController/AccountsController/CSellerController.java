package Client.ClientController.AccountsController;

import Client.Client;
import Models.Accounts.Seller;
import Models.Auction;
import Models.Category;
import Models.Discount;
import Models.Logs.SellLog;
import Models.Product;

import Models.Request.*;
import Server.ServerController.DataBaseForServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
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

    public static void addOnlineSeller(Seller seller) {
        onlineSellers.add(seller);
    }

    public static void removeOnlineSeller(Seller seller) {
        onlineSellers.remove(seller);
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

    public static void addProductRequest(String data) throws Exception {
        String func = "Add Product Request";
        Client.sendMessage(func);

        Client.sendObject(data);

        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = data.split("\\s");
                Product product = new Product(split[0], split[1], split[2],
                        Double.parseDouble(split[3]), getSeller(),
                        DataBaseForServer.getCategory(split[4]), split[5], 0, split[6], split[7]);
                new AddProductRequest(getSeller(), product, DataBaseForServer.getCategory(split[4]));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    public static void editProductRequest(String id, String data) throws Exception {
        String func = "Edit Product Request";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = id;
        toSend[1] = data;
        Client.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            String[] split = data.split("\\s");
            Product product = (Product) response;
            String field = split[0];
            String newContentForThisField = split[1];
            new EditProductRequest(getSeller(), product, field, newContentForThisField);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void addDiscountRequest(String data) throws Exception {
        String func = "Add Discount Request";
        Client.sendMessage(func);

        Client.sendObject(data);

        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = data.split("\\s");
                Discount discount = new Discount(split[0], LocalDate.parse(split[1]), LocalDate.parse(split[2]),
                        Double.parseDouble(split[4]), DataBaseForServer.getProduct(split[5]));
                new AddOffRequest(getSeller(), discount);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }

    public static void editDiscountRequest(String code, String data) throws Exception {

        String func = "Edit Discount Request";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = code;
        toSend[1] = data;
        Client.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            String[] split = data.split("\\s");
            Discount discount = (Discount) response;
            String field = split[0];
            String newContentForThisField = split[1];
            new EditOffRequest(getSeller(), discount, field, newContentForThisField);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void removeProductRequest(String id) throws Exception {
        String func = "Remove Product Request";
        Client.sendMessage(func);

        Client.sendObject(id);

        Object response = Client.receiveObject();

        try {
            Object data = Client.receiveObject();
            Product product = (Product) data;
            new RemoveProductRequest(getSeller(), product);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void addAuction(String data) throws Exception {
        String func = "Add Auction";
        Client.sendMessage(func);

        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = data.split("\\s");
                new Auction(DataBaseForServer.getProduct(split[0]), new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(split[1]));
                //.start auction
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //    public static void addAuction(String productID, String endDate) {
//        try {
//            Date endDateAsDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(endDate);
//            Product product = Product.getProductWithId(productID);
//
//            Auction auction = new Auction(product, endDateAsDate);
//            auction.start();
//        } catch (ParseException e) {
//            e.getMessage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
