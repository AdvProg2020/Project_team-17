package Controller;

import Models.Accounts.Customer;
import Models.Cart;
import Models.Product;

import java.util.ArrayList;

public class CartManager {
    public static ArrayList<String> showProducts(Customer customer){
        ArrayList<String> showProducts = new ArrayList<>();
        Cart cart = customer.getCart();
        for (Product product : cart.showProductsOfCart()) {
            showProducts.add(product.getProductId());
        }
        return showProducts;
    }
    public static double showTotalPriceOfCart(Customer customer){
        Cart cart = customer.getCart();
        return cart.totalPriceOfProductInCart();
    }
    public static void isThereProductInThisCart(Customer customer,Product product) throws Exception{
        Cart cart =customer.getCart();
        if(cart.isThereProductInCart(product)){

        }else
            throw new Exception("There isn't any product with this ID in cart!");
        }
    public static void increaseProduct(Customer customer, Product product) throws Exception {
        Cart cart = customer.getCart();
        if (cart.isThereProductInCart(product)) {
            cart.increaseNumberOfProduct(product);
        } else {
            throw new Exception("There isn't any product with this ID in cart!");
        }

    }

    public static void decreaseProduct(Customer customer, Product product) throws Exception {
        Cart cart = customer.getCart();
        if (cart.isThereProductInCart(product)) {
            cart.decreaseNumberOfProduct(product);
        } else {
            throw new Exception("There isn't any product in cart!");
        }
    }

}
