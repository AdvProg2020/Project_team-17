package Controller;

import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Cart;
import Models.Category;
import Models.Enums.PointOfViewEnum;
import Models.Product;

public class ProductManager {
    public void digestAttributes(Product product) {
        product.digestAttributes();
    }

    public void addToCart(Customer customer, Product product) {
        Cart cart = customer.getCart();
        cart.addProductToCart(customer, product);
        //in k hanooz login nashode bood bere safeheye login fek konam to menu ha bayad bezarim
    }

    public void showAttributes(Product product) {
        product.toString();
        //safe 32 vijegi haye category ro nemifahmam yani chi
    }

    public String compareProduct(String firstId, String secondId) {
        String twoProductsAttributes;
        Product firstProduct = Product.getProductWithId(firstId);
        Product secondProduct = Product.getProductWithId(secondId);
        twoProductsAttributes = "Product 1 : " + firstProduct.toString() + " Product 2 : " + secondProduct.toString();
        return twoProductsAttributes;
    }

    public void showComments(Product product) {
        product.getPointOfViews();
    }

    public void addComment(Account account, Product product, String content, PointOfViewEnum title) {
        product.addCommentForProduct(account, content, title);
    }
}