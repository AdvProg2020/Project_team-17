package Controller;

import Models.Accounts.Customer;
import Models.Cart;
import Models.Category;
import Models.PointOfView;
import Models.Product;

import java.util.ArrayList;

public class ProductManager {
    public static String digestAttributes(Product product) {
        return product.digestAttributes();
    }

    public static void addToCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        cart.addProductToCart(customer, product);
    }

    public static String showAttributes(Product product) {
        Category category = product.getCategory();
        return "Product info -----> " + product.toString() + " Category info of this product ----->" + category.getSpecialFeature();
    }

    public static String compareProduct(Product firstProduct, String secondId) {
        String twoProductsAttributes;
        Product secondProduct = Product.getProductWithId(secondId);
        twoProductsAttributes = "Product 1 -----> " + firstProduct.toString() + "\n Product 2 -----> " + secondProduct.toString();
        return twoProductsAttributes;
    }

    public static String showComments(Product product) {
        ArrayList<String> comments = new ArrayList<>();
        for (PointOfView pointOfView : product.getPointOfViews()) {
            comments.add(pointOfView.getPointOfViewStringText());
        }
        String output = "Product score: " + Double.toString(product.getAverageScore());
        output += "Product comments: " + comments;
        return output;
    }

    public static void addComment(Customer customer, Product product, String content) {
        product.addCommentForProduct(customer, content);
    }
}