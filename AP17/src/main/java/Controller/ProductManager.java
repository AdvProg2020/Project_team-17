package Controller;

import Models.Accounts.Customer;
import Models.Cart;
import Models.Logs.BuyLog;
import Models.PointOfView;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class ProductManager {

    public static void addToCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        cart.addProductToCart(customer, product);
    }

    public static ObservableList<String> showComments(Product product) {
        ArrayList<String> comments = new ArrayList<>();
        for (PointOfView pointOfView : product.getPointOfViews()) {
            comments.add(pointOfView.getPointOfViewStringText());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(comments);
        return data;
    }

    public static boolean doesCustomerBoughtThisProduct(Customer customer, Product product) {
        for (BuyLog buyLog : customer.getBuyLog()) {
            for (Product logProduct : buyLog.getAllProducts()) {
                if (logProduct.equals(product)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void rateProduct(Customer customer, Product product, double score) {
        product.addScoreForProduct(customer, product, score);
    }

    public static void addComment(Customer customer, Product product, String content) throws IOException {
        product.addCommentForProduct(customer, content);
    }
}