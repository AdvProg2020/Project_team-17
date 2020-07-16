package Client.ClientController.AccountsController;

import Client.Client;
import Models.Accounts.Seller;
import Models.Category;
import Models.Discount;
import Models.Logs.SellLog;
import Models.Product;

import View.RegisterSellerMenu;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SellerController {

    public static String showSellerInfo() throws Exception {
        String func = "Show Seller Info";
        Client.sendMessage(func);
        if (RegisterSellerMenu.getCurrentSeller() == null) {
            throw new Exception("a seller should login first!");
        } else {
            String sellerUsername = RegisterSellerMenu.getCurrentSeller().getUserName();
            Client.sendMessage(sellerUsername);
            Object data = Client.receiveObject();
            if (data instanceof Exception) {
                throw new Exception("account with this username doesn't found");
            } else {
                return String.valueOf(data);
            }
        }
    }

    public static void editSellerInfo(String field, String newContent) throws Exception {
        String func = "Edit Seller Info";
        Client.sendMessage(func);
        if (RegisterSellerMenu.getCurrentSeller() == null) {
            throw new Exception("a seller should login first!");
        } else {
            Object[] toSend = new Object[2];
            toSend[0] = field;
            toSend[1] = newContent;
            Client.sendObject(toSend);
            Client.receiveObject();
        }
    }

    public static ArrayList<SellLog> showSalesHistory() {
        String func = "Show Sales History";
        Client.sendMessage(func);

        Client.sendObject(RegisterSellerMenu.getCurrentSeller());
        Object response = Client.receiveObject();
        return (ArrayList<SellLog>) response;
    }

    public static String showLogDetails(String id) throws Exception {
        String func = "Show Log Details";
        Client.sendMessage(func);

        Client.sendMessage(id);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't any log with this id");
        else {
            return (String) response;
        }
    }

    public static ArrayList<Category> showCategories() throws Exception {
        String func = "Show Categories";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Category>) response;
    }

    public static ArrayList<Discount> showDiscounts() throws Exception {
        String func = "Show Discounts";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Discount>) response;
    }

    public static ArrayList<Product> showProducts() throws Exception {
        String func = "Show Discounts";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        return (ArrayList<Product>) response;
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
}
