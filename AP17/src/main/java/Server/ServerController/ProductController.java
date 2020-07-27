package Server.ServerController;

import Client.Client;
import Controller.ProductManager;
import Models.Product;
import Server.ClientHandler;
import Server.ServerController.AccountsController.CustomerController;

public class ProductController {

    public static void rateProduct() {
        Object[] receivedData = (Object[]) ClientHandler.receiveObject();
        String productId = (String) receivedData[0];
        Product product = Product.getProductWithId(productId);
        if (product != null) {
            if (ProductManager.doesCustomerBoughtThisProduct(CustomerController.getCustomer(), product)) {
                ClientHandler.sendObject(product);
            } else {
                ClientHandler.sendObject(new Exception("you can only rate products you have purchased it"));
            }
        } else {
            ClientHandler.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void commentProduct() {
        Object[] receivedData = (Object[]) ClientHandler.receiveObject();
        String productId = (String) receivedData[0];
        String content = (String) receivedData[1];
        Product product = Product.getProductWithId(productId);
        if (product != null) {
            ClientHandler.sendObject(product);
        } else {
            ClientHandler.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void compareProduct() {
        Object[] receivedData = (Object[]) ClientHandler.receiveObject();
        String productId = (String) receivedData[0];

        Product product = Product.getProductWithId(productId);
        if (product != null) {
            ClientHandler.sendObject(product);
        } else {
            ClientHandler.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void addToCart() {
        Object[] receivedData = (Object[]) ClientHandler.receiveObject();
        Product product = (Product) receivedData[0];

        if (product != null) {
            ClientHandler.sendObject("Done");
        } else {
            ClientHandler.sendObject(new Exception("there isn't any product with this id"));
        }
    }
}
