package Server.ServerController;

import Client.Client;
import Models.Cart;
import Models.Product;
import View.RegisterCustomerMenu;

public class CartController {

    public static void increaseProduct() throws Exception {
        Product product = (Product) Client.receiveObject();
        if (product != null) {
            Cart cart = RegisterCustomerMenu.getCurrentCustomer().getCart();
            cart.increaseNumberOfProduct(product);
        } else {
            Client.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void decreaseProduct() throws Exception {
        Product product = (Product) Client.receiveObject();
        if (product != null) {
            Cart cart = RegisterCustomerMenu.getCurrentCustomer().getCart();
            cart.decreaseNumberOfProduct(product);
        } else {
            Client.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void showTotalPrice() {
        Cart cart = RegisterCustomerMenu.getCurrentCustomer().getCart();
        double totalPrice = cart.totalPriceOfProductInCart();
        Client.sendMessage(String.valueOf(totalPrice));
    }


}
