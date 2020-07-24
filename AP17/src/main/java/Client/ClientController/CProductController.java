package Client.ClientController;

import Client.Client;

public class CProductController {

    public static void rateProduct(String productId, double rateScore) throws Exception {
        String func = "Rate Product";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = productId;
        toSend[1] = rateScore;

        Object response = Client.receiveObject();
        if (response instanceof Exception)
            throw new Exception("there isn't any product with this id");
    }

    public static void commentProduct(String productId, String comment) throws Exception {
        String func = "Comment Product";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = productId;
        toSend[1] = comment;

        Object response = Client.receiveObject();
        if (response instanceof Exception)
            throw new Exception("there isn't any product with this id");
    }
}
