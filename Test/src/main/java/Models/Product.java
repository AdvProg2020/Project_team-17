package Models;

import Models.Accounts.Account;
import Models.Accounts.Customer;
import Models.Accounts.Seller;
import Models.Enums.PointOfViewEnum;
import Models.Enums.ProductEnum;

import java.util.ArrayList;

public class Product {
    //moshakhasat khas daste ro nemidonam yani chia
    private static ArrayList<Product> allProducts = new ArrayList<Product>();
    private String productId;
    private ProductEnum productState;
    private String name;
    private String companyName;
    private double price;
    private double priceAfterDiscount;
    private Seller seller;
    private Category category;
    private String explanation;
    private double averageScore = 0;
    private ArrayList<PointOfView> pointOfViews = new ArrayList<>();
    private ArrayList<Score> scoresForProduct = new ArrayList<>();

    public Product(String productId, ProductEnum productState, String name, String companyName, double price, Seller seller, Category category, String explanation, double averageScore) {
        this.productId = productId;
        this.productState = productState;
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.seller = seller;
        this.category = category;
        this.explanation = explanation;
        this.averageScore = averageScore;
    }

    public ArrayList<Score> getScoresForProduct() {
        return scoresForProduct;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public static Product getProductWithId(String productId) {
        for (Product allProduct : allProducts) {
            if (allProduct.getProductId().equals(productId))
                return allProduct;
        }
        return null;
    }

    public static boolean isThereProductWithId(String productId) {
        for (Product product : allProducts) {
            if (product.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }

    public void addScoreForProduct(Customer customer, Product product, double score) {
        product.scoresForProduct.add(new Score(customer, product, score));
    }

    public Category getCategory() {
        return category;
    }

    public String getProductId() {
        return productId;
    }

    public void averageScoreForProduct(Product product) {
        int sum = 0;
        for (Score score : product.scoresForProduct) {
            sum += score.getScore();
        }
        this.averageScore = sum / product.scoresForProduct.size();
    }

    public static void removeProduct(Product product) {
        allProducts.remove(product);
        Category category = product.getCategory();
        category.removeProductFromCategory(category, product);
        Seller seller = product.getSeller();
        seller.removeProduct(product);
    }

    public static void deleteProduct(Product product) {
        allProducts.remove(product);
    }

    public Seller getSeller() {
        return seller;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void addCommentForProduct(Account account, String content, PointOfViewEnum title) {
        //nemidonam title hamoon vaziate comment hast ya na
        this.pointOfViews.add(new PointOfView(account, this, content, title));
    }

    public ArrayList<PointOfView> getPointOfViews() {
        return pointOfViews;
    }

    public String digestAttributes() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", priceAfterDiscount=" + priceAfterDiscount +
                ", seller=" + seller +
                ", category=" + category +
                ", explanation='" + explanation + '\'' +
                ", averageScore=" + averageScore +
                '}';
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productState=" + productState +
                ", name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                ", priceAfterDiscount=" + priceAfterDiscount +
                ", seller=" + seller +
                ", category=" + category +
                ", explanation='" + explanation + '\'' +
                ", averageScore=" + averageScore +
                '}';
    }
}