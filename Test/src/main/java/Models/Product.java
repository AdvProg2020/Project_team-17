package Models;

import Models.Accounts.Customer;
import Models.Accounts.Seller;
import Models.Enums.ProductEnum;
import Models.Logs.BuyLog;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private static ArrayList<Product> allProducts = new ArrayList<Product>();
    private String productId;
    private ProductEnum productState;
    private String name;
    private String companyName;
    private double price;
    private Seller seller;
    private Category category;
    private String explanation;
    private double averageScore = 0;
    private String productsSpecialFeature;
    private int visitedTime = 0;
    private Discount discount;
    private String path;
    private ArrayList<PointOfView> pointOfViews = new ArrayList<>();
    private ArrayList<Double> scoresForProduct = new ArrayList<>();

    public Product(String productId, String name, String companyName, double price, Seller seller, Category category, String explanation, double averageScore, String productsSpecialFeature, String path) {
        this.productId = productId;
        this.productState = ProductEnum.PRODUCING;
        this.name = name;
        this.companyName = companyName;
        this.price = price;
        this.seller = seller;
        this.category = category;
        this.explanation = explanation;
        this.averageScore = averageScore;
        scoresForProduct.add(averageScore);
        this.productsSpecialFeature = productsSpecialFeature;
        this.path = path;
        allProducts.add(this);
    }

    public void addToVisitedTime() {
        this.visitedTime++;
    }

    public int getVisitedTime() {
        return visitedTime;
    }

    public String getPath() {
        return path;
    }


    public double calculateProductPrice(Product product) {
        if (Discount.doesThisProductHaveDiscount(product)) {
            Discount discount = Discount.getProductDiscount(product);
            double discountAmount = (discount.getDiscountPercent() * product.getPrice()) / 100;
            return product.getPrice() - discountAmount;
        }
        return product.getPrice();
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public static Product getProductWithId(String productId) {
        for (Product allProduct : allProducts) {
            if (allProduct.getProductId().equals(productId))
                return allProduct;
        }
        return null;
    }

    public static ArrayList<Product> getProductsListByName(List<String> productsName) {
        ArrayList<Product> products = new ArrayList<>();
        for (String name : productsName) {
            products.add(getProductByName(name));
        }
        return products;
    }

    public static Product getProductByName(String name) {
        for (Product product : allProducts) {
            if (product.getName().equals(name)) {
                return product;
            }
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
        product.scoresForProduct.add(score);
    }

    public Category getCategory() {
        return category;
    }

    public String getProductId() {
        return productId;
    }

    public double averageScoreForProduct(Product product) {
        int sum = 0;
        for (Double score : product.scoresForProduct) {
            sum+=score;
        }
        return this.averageScore = sum / product.scoresForProduct.size();
    }

    public void setProductState(ProductEnum productState) {
        this.productState = productState;
    }

    public static void removeProduct(Product product) {
        allProducts.remove(product);
        Category category = product.getCategory();
        category.removeProductFromCategory(category, product);
        Seller seller = product.getSeller();
        seller.removeProduct(seller, product);
    }

    public static void deleteProducts(ArrayList<Product> removeProduct) {
        for (Product product : removeProduct) {
            allProducts.remove(product);
        }
    }

    public double getProductPriceAfterDiscount(Discount discount) {
        if (discount == null)
            return this.getPrice() - ((discount.getDiscountPercent() * this.getPrice()) / 100);
        else
            return this.getPrice();
    }

    public Seller getSeller() {
        return seller;
    }

    public double getAverageScore() {
        return this.averageScoreForProduct(this);
    }

    public void addCommentForProduct(Customer customer, String content) {
        boolean hasBought = false;
        ArrayList<BuyLog> buyLogs = customer.getBuyLog();
        for (BuyLog log : buyLogs) {
            if (log.doesLogHaveThisProduct(this.getProductId())) {
                hasBought = true;
            }
        }
        this.pointOfViews.add(new PointOfView(customer, this, content, hasBought));
    }

    public ArrayList<PointOfView> getPointOfViews() {
        return pointOfViews;
    }

    public static ArrayList<Product> getAllProducts() {
        return allProducts;
    }

    public ProductEnum getProductState() {
        return productState;
    }

    public String getProductsSpecialFeature() {
        return productsSpecialFeature;
    }

    public String digestAttributes() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
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
                ", seller=" + seller +
                ", category=" + category.getCategoryName() +
                ", explanation='" + explanation + '\'' +
                ", averageScore=" + averageScore +
                '}';
    }
}