package Controller;

import Models.Accounts.Customer;
import Models.Cart;
import Models.DiscountCode;
import Models.Product;

import java.util.ArrayList;

public class CartManager {
    public static ArrayList<String> showProducts(Customer customer) {
        ArrayList<String> showProducts = new ArrayList<>();
        Cart cart = customer.getCart();
        for (Product product : cart.showProductsOfCart()) {
            showProducts.add(product.getProductId());
        }
        return showProducts;
    }

    /*public static double showTotalPriceOfCart(Customer customer) throws Exception {
        Cart cart = customer.getCart();
        if (cart != null) {
            return cart.totalPriceOfProductInCart();
        } else throw new Exception("Cart is empty");
    }*/
    public static double showTotalPriceOfCart(Customer customer, DiscountCode discountCode){
        return customer.getCart().totalPriceWithDiscount(discountCode);
    }


    public static void increaseProduct(Customer customer, String id) throws Exception {
        Cart cart = customer.getCart();
        if (cart != null) {
            try {
                Product product = cart.getProductInCart(id);
                cart.increaseNumberOfProduct(product);
            } catch (Exception e) {
                System.out.println("There isn't any product with this id in cart");
            }
        } else throw new Exception("Cart is empty");
    }

    public static void decreaseProduct(Customer customer, String id) throws Exception {
        Cart cart = customer.getCart();
        if (cart != null) {
            try {
                Product product = cart.getProductInCart(id);
                cart.decreaseNumberOfProduct(product);
            } catch (Exception e) {
                System.out.println("There isn't any product with this id in cart");
            }
        } else throw new Exception("Cart is empty");
    }

}
