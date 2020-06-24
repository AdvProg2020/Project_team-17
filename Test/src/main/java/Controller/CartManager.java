package Controller;

import Models.Accounts.Customer;
import Models.Cart;
import Models.DiscountCode;
import Models.Product;

import java.util.ArrayList;

public class CartManager {

    public static ArrayList<Product> cartProducts(Customer customer) {
        Cart cart = customer.getCart();
        return cart.showProductsOfCart();
    }

    public static int numberOfProducts(Customer customer, Product product) {
        Cart cart = customer.getCart();
        return cart.numberOfProductInCart(product);
    }

    public static double showTotalPriceOfCart(Customer customer) {
        Cart cart = customer.getCart();
        return cart.totalPriceOfProductInCart();
    }

    public static double showTotalPriceOfCartIncludingDiscountCode(Customer customer, DiscountCode discountCode) {
        return customer.getCart().totalPriceWithDiscount(discountCode);
    }


    public static void increaseProduct(Customer customer, Product product) {
        Cart cart = customer.getCart();
        cart.increaseNumberOfProduct(product);
    }

    public static void decreaseProduct(Customer customer, Product product) {
        Cart cart = customer.getCart();
        cart.decreaseNumberOfProduct(product);
    }

}