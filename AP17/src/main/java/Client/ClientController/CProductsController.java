package Client.ClientController;

import Client.Client;
import Models.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CProductsController {
    public static ObservableList<String> showCategories() {
        ArrayList<Category> allCategories;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Categories";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allCategories = (ArrayList<Category>) response;
        for (Category category : allCategories) {
            info.add(category.getCategoryName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;
    }
}
