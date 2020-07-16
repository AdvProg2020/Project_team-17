package Server.ServerController;

import Client.Client;
import Models.Category;

import java.util.ArrayList;

public class ProductsController {

    public static void viewCategories() {
        ArrayList<String> categoriesName = new ArrayList();
        for (Category category : Category.getAllCategories()) {
            categoriesName.add(category.getCategoryName());
        }
        Client.sendObject(categoriesName);
    }
}
