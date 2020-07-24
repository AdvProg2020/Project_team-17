package Server.ServerController;

import Client.Client;
import Models.Cart;
import Models.Product;
import Server.ClientHandler;

public class CartController {

    public static void increaseProduct() throws Exception {
        Product product = (Product) ClientHandler.receiveObject();
        if (product != null) {
            ClientHandler.sendObject("OK");
        } else {
            ClientHandler.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void decreaseProduct() throws Exception {
        Product product = (Product) ClientHandler.receiveObject();
        if (product != null) {
            ClientHandler.sendObject("OK");
        } else {
            ClientHandler.sendObject(new Exception("there isn't any product with this id"));
        }
    }

    public static void showTotalPrice() {
        Cart cart = (Cart) ClientHandler.receiveObject();
        double totalPrice = cart.totalPriceOfProductInCart();
        Client.sendMessage(String.valueOf(totalPrice));
    }
}
