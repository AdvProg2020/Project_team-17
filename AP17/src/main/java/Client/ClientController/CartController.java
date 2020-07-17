package Client.ClientController;

import Client.Client;
import Models.Product;
import View.RegisterCustomerMenu;

public class CartController {

    public static void increaseProduct(Product product) throws Exception {
        String func = "Increase Product";
        Client.sendMessage(func);

        Client.sendObject(product);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't ant product with this id");
    }

    public static void decreaseProduct(Product product) throws Exception {
        String func = "Decrease Product";
        Client.sendMessage(func);

        Client.sendObject(product);

        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there isn't ant product with this id");
    }

    public static double showTotalPrice() {
        String func = "Show Total Price";
        Client.sendMessage(func);

        Client.sendObject(RegisterCustomerMenu.getCurrentCustomer().getCart());
        return (double) Client.receiveObject();
    }
}
