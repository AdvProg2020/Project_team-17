package Client.ClientController;

import Client.Client;
import Models.Cart;
import Models.Product;
import Server.ServerController.AccountsController.CustomerController;
import View.RegisterMenus.RegisterCustomerMenu;

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

    public static double showTotalPriceWithDiscountCode(String code) {
        String func = "Show Total Price With Discount Code";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = code;
        toSend[1] = CustomerController.getCustomer().getCart();
        Client.sendObject(toSend);

        Client.sendObject(CustomerController.getCustomer().getCart());
        return (double) Client.receiveObject();
    }
}
