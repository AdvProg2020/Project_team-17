package Client.ClientController;

import Client.Client;

import java.util.ArrayList;

public class CProductsController {
    public static ArrayList<String> viewCategories() {
        String func = "View Categories For Products";
        Client.sendMessage(func);

        return (ArrayList<String>) Client.receiveObject();
    }
}
