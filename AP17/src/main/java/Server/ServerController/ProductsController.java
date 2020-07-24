package Server.ServerController;

import Models.Category;
import Server.ClientHandler;

import java.util.ArrayList;

public class ProductsController {

    public static void showCategories() {
        ArrayList<Category> allCategories = new ArrayList<>(DataBaseForServer.getAllCategories());
        ClientHandler.sendObject(allCategories);
    }
}
