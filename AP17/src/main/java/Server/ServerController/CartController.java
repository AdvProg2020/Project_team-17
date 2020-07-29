package Server.ServerController;

import Models.Cart;
import Models.DiscountCode;
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
        ClientHandler.sendObject(String.valueOf(totalPrice));
    }

    public static void showTotalPriceWithDiscountCode() {
        Object[] receivedItems = (Object[]) ClientHandler.receiveObject();
        String code = (String) receivedItems[0];
        DiscountCode discountCode = DataBaseForServer.getDiscountCode(code);

        Cart cart = (Cart) receivedItems[1];

        double totalPrice = cart.totalPriceWithDiscount(discountCode);
        ClientHandler.sendObject(String.valueOf(totalPrice));
    }
}
