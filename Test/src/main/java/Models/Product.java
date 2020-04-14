package Models;

import java.util.ArrayList;

public class Product {
    private static ArrayList<Product> allProducts = new ArrayList<Product>();
    private String productId;
    private ProductEnum productState;
    private String name;
    private String companyName;
    private double price;
    private Seller seller;
    private Category category;
    //moshakhasat khas daste ro nemidonam yani chia
    private String explanation;
    private double averageScore = 0;
    private ArrayList<String> pointOfViews = new ArrayList<>();
    private ArrayList<Double> scoresForProduct = new ArrayList<>();

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

    public ArrayList<Double> getScoresForProduct() {
        return scoresForProduct;
    }

    public static Product getProductWithId(String productId){
        for (Product allProduct : allProducts) {
            if (allProduct.getProductId().equals(productId))
                return allProduct;
        }
        return null;
    }
    public void addScoreForProduct(double score){
        scoresForProduct.add(score);
    }

    public String getProductId() {
        return productId;
    }
    public void averageScoreForProduct(ArrayList<Double> scores){
        int sum = 0;
        for (int i = 0; i < scores.size(); i++) {
            sum += scores.get(i);
        }
        this.averageScore = sum / scores.size();
    }

    public double getAverageScore() {
        return averageScore;
    }
    public void addCommentForProduct(String comment){
        pointOfViews.add(comment);
    }
}
