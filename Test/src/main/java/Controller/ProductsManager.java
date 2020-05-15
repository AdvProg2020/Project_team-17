package Controller;

import Models.Accounts.Seller;
import Models.Category;
import Models.Enums.ProductEnum;
import Models.Product;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ProductsManager {
    private static ArrayList<Product> filterProduct = new ArrayList<>(Product.getAllProducts());

    public static ArrayList<Product> showProducts() {
        return Product.getAllProducts();
    }

    public static ArrayList<String> showCategory() {
        ArrayList<String> categoryName = new ArrayList<>();
        for (Category category : Category.getAllCategories()) {
            categoryName.add(category.getCategoryName());
        }
        return categoryName;
    }

    public static String showAvailableFilter() {
        return "by category\nby name\nby price\nby brand\nby seller\nby availability\nby category feature";
        // baraye category featue o name o brand idk
    }

    public static ArrayList<String> getFilterProductsName() {
        ArrayList<String> productsName = new ArrayList<>();
        for (Product product : filterProduct) {
            productsName.add(product.getName());
        }
        return productsName;
    }

    public static void filterByCategory(String categoryName) {
        Category category = Category.getCategoryByName(categoryName);
        for (Product product : filterProduct) {
            if (!(product.getCategory().equals(category))) {
                filterProduct.remove(product);
            }
        }
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
        for (Product product : filterProduct) {
            if (product.getPrice() <= minPrice && product.getPrice() >= maxPrice) {
                filterProduct.remove(product);
            }
        }
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
        for (Product product : filterProduct) {
            if (!(product.getSeller().equals(seller))) {
                filterProduct.remove(product);
            }
        }
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
        for (Product product : Product.getAllProducts()) {
            if (!(product.getProductState().equals(productEnum))) {
                filterProduct.remove(product);
            }
        }
    }

    public static void disableFilterByAvailability(ProductEnum productEnum) {
        for (Product product : Product.getAllProducts()) {
            if (!(product.getProductState().equals(productEnum))) {
                filterProduct.add(product);
            }
        }
    }

    public static void filterByName(String name) {
        for (Product product : Product.getAllProducts()) {
            if (!(product.getName().equals(name))) {
                filterProduct.remove(product);
            }
        }
    }

    public static void disableFilterByName(String name) {
        for (Product product : Product.getAllProducts()) {
            if (!(product.getName().equals(name))) {
                filterProduct.add(product);
            }
        }
    }

    public static void filterBySpecialFeature(String feature) {
        for (Product product : Product.getAllProducts()) {
            if (!(product.getProductsSpecialFeature().equals(feature))) {
                filterProduct.remove(product);
            }
        }
    }

    public static void disableFilterBySpecialFeature(String feature) {
        for (Product product : Product.getAllProducts()) {
            if (!(product.getProductsSpecialFeature().equals(feature))) {
                filterProduct.add(product);
            }
        }
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


}
