package Models;

import Models.Enums.DiscountEnum;

import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private String discountId;
    private static ArrayList<Product> discountProducts = new ArrayList<>();
    private static ArrayList<Discount> allDiscounts = new ArrayList<>();
    private Date startDate;
    private Date endDate;
    private double discountPercent;
    private DiscountEnum discountState;

    public Discount(String discountId, Date startDate, Date endDate, double discountPercent, DiscountEnum discountState) {
        this.discountId = discountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.discountState= discountState;
        allDiscounts.add(this);
    }

    public void setDiscountState(DiscountEnum discountState) {
        this.discountState = discountState;
    }

    public String getDiscountId() {
        return discountId;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public static ArrayList<Product> getDiscountProducts() {
        return discountProducts;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }


    public void addProduct(Product product) {
        discountProducts.add(product);
    }

    public DiscountEnum getDiscountState() {
        return discountState;
    }

    public static void showDiscountProducts() {
        for (Product product : discountProducts) {
            System.out.println("Product ID : "+product.getProductId() +"Price before discount :" + product.getPrice() + " " +
                    "Price after discount : " + product.getPriceAfterDiscount());
        }
        //fek konam chizaye dige ham bayad bezarim
    }

    public void deleteProduct(Product product) {
        this.discountProducts.remove(product);
    }

    public void removeProductFromDiscount(Product product) {
        for (Discount discount : allDiscounts) {
            if (discount.isThisProductInDiscount(product)) {
                deleteProduct(product);
            }
        }
    }

    public boolean isThisProductInDiscount(Product product) {
        for (Product discountProduct : discountProducts) {
            if (discountProduct.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public void deleteDiscountFromProduct(Product product) {

    }
}
