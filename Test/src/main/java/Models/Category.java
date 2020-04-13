package Models;

import java.util.ArrayList;

public class Category {
    private String categoryName;
    private String specialFeature;
    private ArrayList<Product> products;

    public Category(String categoryName, String specialFeature, ArrayList<Product> products) {
        this.categoryName = categoryName;
        this.specialFeature = specialFeature;
        this.products = products;
    }
}
