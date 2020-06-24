package Controller;

import Models.Accounts.Customer;
import Models.Cart;
import Models.Logs.BuyLog;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductManager {

    public static void addToCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        cart.addProductToCart(customer, product);
    }

    public static String compareProduct(Product firstProduct, String secondId) {
        String twoProductsAttributes;
        Product secondProduct = Product.getProductWithId(secondId);
        twoProductsAttributes = "Product 1 -----> " + firstProduct.toString() + "\n Product 2 -----> " + secondProduct.toString();
        return twoProductsAttributes;
    }

    public static ObservableList<String> showComments(Product product) {
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(product.getPointOfViews());
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

    public static void addComment(Customer customer, Product product, String content) {
        product.addCommentForProduct(customer, content);
    }
}