package Client.ClientController;

import Client.Client;
import Client.ClientController.AccountsController.CCustomerController;
import Models.Cart;
import Models.Product;
import Server.ClientHandler;
import View.RegisterMenus.RegisterCustomerMenu;

public class CProductController {

    public static void rateProduct(String productId, double rateScore) throws Exception {
        String func = "Rate Product";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = productId;
        toSend[1] = rateScore;
        ClientHandler.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            Product product = (Product) response;
            product.addScoreForProduct(RegisterCustomerMenu.getCurrentCustomer(), product, rateScore);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void commentProduct(String productId, String comment) throws Exception {
        String func = "Comment Product";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = productId;
        toSend[1] = comment;
        ClientHandler.sendObject(toSend);

        try {
            Object response = Client.receiveObject();
            Product product = (Product) response;
            product.addCommentForProduct(RegisterCustomerMenu.getCurrentCustomer(), comment);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static Product compareProduct(String productId) throws Exception {
        String func = "Compare Product";
        Client.sendMessage(func);

        Object[] toSend = new Object[1];
        toSend[0] = productId;
        Client.sendObject(toSend);

        try {
            Object response = Client.receiveObject();
            Product product = (Product) response;
            return product;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void addToCart(Product product) throws Exception {
        String func = "Add To Cart";
        Client.sendMessage(func);

        Object[] toSend = new Object[1];
        toSend[0] = product;
        Client.sendObject(toSend);

        try {
            Object response = Client.receiveObject();
            String string = (String) response;
            if(string.equals("Done")){
                Cart cart = CCustomerController.getCustomer().getCart();
                cart.addProductToCart(CCustomerController.getCustomer(), product);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
