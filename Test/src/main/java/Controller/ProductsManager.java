package Controller;

import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Comparator;

public class ProductsManager {
    private static ArrayList<Product> filterProduct = new ArrayList<>(Product.getAllProducts());
    private static ArrayList<Product> sortProducts = new ArrayList<>(Product.getAllProducts());
    private static String currentSort = "visited time";

    public static ArrayList<Product> showProducts() {
        return Product.getAllProducts();
    }

    public static ObservableList<String> showCategory() {
        ArrayList<String> categoryName = new ArrayList<>();
        for (Category category : Category.getAllCategories()) {
            categoryName.add(category.getCategoryName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(categoryName);
        return data;
    }

    public static ArrayList<Product> getFilterProduct() {
        return filterProduct;
    }

    public static void filterByCategory(String categoryName) {
        Category category = Category.getCategoryByName(categoryName);
        filterProduct.removeIf(product -> !(product.getCategory().equals(category)));
    }

    public static void disableFilterByCategory(String categoryName) {
        Category category = Category.getCategoryByName(categoryName);
        for (Product product : filterProduct) {
            if (!(product.getCategory().equals(category))) {
                filterProduct.add(product);
            }
        }
    }

    public static void filterByPrice(double minPrice, double maxPrice) {
        filterProduct.removeIf(product -> product.getPrice() <= minPrice && product.getPrice() >= maxPrice);
    }

    public static void disableFilterByPrice(double minPrice, double maxPrice) {
        for (Product product : filterProduct) {
            if (product.getPrice() <= minPrice && product.getPrice() >= maxPrice) {
                filterProduct.add(product);
            }
        }
    }

    public static void filterBySeller(String sellerName) {
        Seller seller = Seller.getSellerByName(sellerName);
        filterProduct.removeIf(product -> !(product.getSeller().equals(seller)));
    }

    public static void disableFilterBySeller(String sellerName) {
        Seller seller = Seller.getSellerByName(sellerName);
        for (Product product : filterProduct) {
            if (!(product.getSeller().equals(seller))) {
                filterProduct.add(product);
            }
        }
    }

    public static void filterByAvailability(ProductEnum productEnum) {
        filterProduct.removeIf(product -> !(product.getProductState().equals(productEnum)));
    }

    public static void disableFilterByAvailability(ProductEnum productEnum) {
        for (Product product : filterProduct) {
            if (!(product.getProductState().equals(productEnum))) {
                filterProduct.add(product);
            }
        }
    }

    public static void filterByName(String name) {
        filterProduct.removeIf(product -> !(product.getName().equals(name)));
    }

    public static void disableFilterByName(String name) {
        for (Product product : Product.getAllProducts()) {
            if (!(filterProduct.contains(product))) {
                filterProduct.add(product);
            }
        }
    }

    public static void filterBySpecialFeature(String feature) {
        filterProduct.removeIf(product -> !(product.getProductsSpecialFeature().equals(feature)));
    }

    public static void disableFilterBySpecialFeature(String feature) {
        for (Product product : filterProduct) {
            if (!(product.getProductsSpecialFeature().equals(feature))) {
                filterProduct.add(product);
            }
        }
    }

    public static void filterByCompanyName(String name) {
        filterProduct.removeIf(product -> !(product.getCompanyName().equals(name)));
    }

    public static void disableFilterByCompanyName(String name) {
        for (Product product : filterProduct) {
            if (!(product.getCompanyName().equals(name))) {
                filterProduct.add(product);
            }
        }
    }

    public static String showAvailableSort() {
        return "by score\nby visited time";
    }

    public static void applyDefaultSort() {
        sortProducts.sort(Comparator.comparing(o -> Integer.toString(o.getVisitedTime())));
    }

    public static ArrayList<Product> sort(String sortType) {
        if (sortType.equals("score")) {
            currentSort = "score";
            ArrayList<Product> sortByScore = sortProducts;
            sortByScore.sort(Comparator.comparing(o -> Double.toString(o.getAverageScore())));
            return sortByScore;
        } else if (sortType.equals("visited time")) {
            currentSort = "visited time";
            ArrayList<Product> sortByVisitedTime = sortProducts;
            sortByVisitedTime.sort(Comparator.comparing(o -> Integer.toString(o.getVisitedTime())));
            return sortByVisitedTime;
        }
        return Product.getAllProducts();
    }

    public static ArrayList<String> getSortProductsName(String currentSort) {
        ArrayList<String> sortProductsName = new ArrayList<>();
        for (Product product : sort(currentSort)) {
            sortProductsName.add(product.getName());
        }
        return sortProductsName;
    }

    public static void disableSort(String sortType) throws Exception {
        if (sortType.equals("score")) {
            if (currentSort.equals("score")) {
                currentSort = "visited time";
                applyDefaultSort();
            }
        } else {
            throw new Exception("current sort is default sort(price)");
        }
    }

    public static String getCurrentSort() {
        return currentSort;
    }
}