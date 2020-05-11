package Models;

import Models.Enums.DiscountEnum;

import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private String discountId;
    private ArrayList<Product> discountProducts;
    private static ArrayList<Discount> allDiscounts = new ArrayList<>();
    private Date startDate;
    private Date endDate;
    private double discountPercent;
    private DiscountEnum discountState;

    public Discount(String discountId, Date startDate, Date endDate, double discountPercent, ArrayList<Product> products) {
        this.discountId = discountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.discountProducts = products;
        this.discountState = DiscountEnum.PROCESSING;
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

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
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

    public static void showProductsHaveDiscount() {
        for (Discount discount : allDiscounts) {
            for (Product product : discount.discountProducts) {
                System.out.println("Product ID : " + product.getProductId() + "Price before discount :" + product.getPrice() + " " +
                        "Price after discount : " + product.getPriceAfterDiscount());
            }
        }
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

    public static Discount getDiscountById(String id) {
        for (Discount discount : allDiscounts) {
            if (discount.getDiscountId().equals(id)) {
                return discount;
            }
        }
        return null;
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

    @Override
    public String toString() {
        return "Discount{" +
                "discountId='" + discountId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
