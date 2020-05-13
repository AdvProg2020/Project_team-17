package Controller;

import Models.Category;
import Models.Product;

import java.util.ArrayList;

public class ProductsManager {
    public ArrayList<Product> showProducts() {
        return null;
    }

    public static ArrayList<String> showCategory() {
        ArrayList<String> categoryName = new ArrayList<>();
        for (Category category : Category.getAllCategories()) {
            categoryName.add(category.getCategoryName());
        }
        return categoryName;
    }

    public void showAvailableFilter() {


    }

    public void showCurrentFilters() {

    }

    public void disableFilter(String filter) {

    }

    public void showAvailableSort() {

    }

    public void sort(String sortType) {

    }

    public void currentSort() {

    }

    public void disableSort(String sort) {

    }
    //show products?

    public void showProductById(String id) {
        //fek konam in ezafe hast
    }


}
