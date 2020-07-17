package Server.ServerController;

import Client.Client;
import Models.Product;
import View.RegisterCustomerMenu;

public class ProductController {

    public static void rateProduct() throws Exception {
        Object[] receivedData = (Object[]) Client.receiveObject();
        String productId = (String) receivedData[0];
        double score = (double) receivedData[1];
        Product product = Product.getProductWithId(productId);
        if (product != null) {
            product.addScoreForProduct(RegisterCustomerMenu.getCurrentCustomer(), product, score);
        } else {
            Client.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void commentProduct() throws Exception {
        Object[] receivedData = (Object[]) Client.receiveObject();
        String productId = (String) receivedData[0];
        String content = (String) receivedData[1];
        Product product = Product.getProductWithId(productId);
        if (product != null) {
            product.addCommentForProduct(RegisterCustomerMenu.getCurrentCustomer(), content);
        } else {
            Client.sendObject(new Exception("there isn't any product with this id"));
        }
    }


}
