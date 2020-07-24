package Client.ClientController;

import Client.Client;
import Models.Cart;
import Models.Product;
import Server.ServerController.AccountsController.CustomerController;
import View.RegisterCustomerMenu;

public class CCartController {

    public static void increaseProduct(Product product) throws Exception {
        String func = "Increase Product";
        Client.sendMessage(func);

        Client.sendObject(product);

        try {
            Object response = Client.receiveObject();
            String string = (String) response;
            if (string.equals("OK")) {
                Cart cart = CustomerController.getCustomer().getCart();
                cart.increaseNumberOfProduct(product);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void decreaseProduct(Product product) throws Exception {
        String func = "Decrease Product";
        Client.sendMessage(func);

        Client.sendObject(product);

        try {
            Object response = Client.receiveObject();
            String string = (String) response;
            if (string.equals("OK")) {
                Cart cart = RegisterCustomerMenu.getCurrentCustomer().getCart();
                cart.decreaseNumberOfProduct(product);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static double showTotalPrice() {
        String func = "Show Total Price";
        Client.sendMessage(func);

        Client.sendObject(CustomerController.getCustomer().getCart());
        return (double) Client.receiveObject();
    }
}
