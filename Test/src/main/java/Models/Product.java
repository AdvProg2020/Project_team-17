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
    private boolean isThereAvailable;
    private Category category;
    //moshakhasat khas daste ro nemidonam yani chia
    private String explanation;
    private double averageScore;
    //baraye miangin nomratat nemidonam doroste in karam ya na bayad y map begiram baraye har account ya kolan baraye har kala
    //ye miangin nomre darim?
    private ArrayList<PointOfView> pointOfViews;

}
