package Models;

import java.util.ArrayList;

public class Category {
    private String categoryName;
    private String specialFeature;
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private ArrayList<Product> products;

    public Category(String categoryName, String specialFeature) {
        this.categoryName = categoryName;
        this.specialFeature = specialFeature;
        products = new ArrayList<>();
        allCategories.add(this);
    }

    public String getSpecialFeature() {
        return specialFeature;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public static boolean isThereCategoryWithName(String name) {
        for (Category category : allCategories) {
            if (category.getCategoryName().equals(name))
                return true;
        }
        return false;
    }

    public void addProductToThisCategory(Category category, Product product) {
        category.products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public static Category getCategoryByName(String name) {
        for (Category category : allCategories) {
            if (category.getCategoryName().equals(name))
                return category;
        }
        return null;
    }

    public void changeCategoryName(Category category, String name) {
        category.categoryName = name;
    }

    public void changeSpecialFeatures(Category category, String feature) {
        category.specialFeature = feature;
    }

    public static void deleteCategory(Category category) {
        //dige nemidonam product haye in bayad az jaye digeyi ham hazf beshe ya na az arraylist allproducts to class products
        //pak kardam vali az cart o ina motmaen nistam
        ArrayList<Product> productsOfThisCategory = new ArrayList<>();
        allCategories.remove(category);
        for (Product product : category.getProducts()) {
            productsOfThisCategory.add(product);
        }
        Product.deleteProducts(productsOfThisCategory);
    }

    public void removeProductFromCategory(Category category, Product product) {
        category.products.remove(product);
    }

    public static ArrayList<String> showCategories() {
        ArrayList<String> categoriesName = new ArrayList<>();
        for (Category category : allCategories) {
            categoriesName.add(category.getCategoryName());
        }
        return categoriesName;
    }
}
