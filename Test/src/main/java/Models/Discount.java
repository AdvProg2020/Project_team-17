package Models;

import Models.Enums.DiscountEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Discount {
    private String discountId;
    private static List<Product> discountProducts = new ArrayList<>();
    private static ArrayList<Discount> allDiscounts = new ArrayList<>();
    private Product product;
    private LocalDate startDate;
    private LocalDate endDate;
    private double discountPercent;
    private DiscountEnum discountState;

    public Discount(String discountId, LocalDate startDate, LocalDate endDate, double discountPercent, Product product) {
        this.discountId = discountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.product = product;
        this.discountState = DiscountEnum.PROCESSING;
        discountProducts.add(product);
        allDiscounts.add(this);
    }

    public static ArrayList<Discount> getAllDiscounts() {
        return allDiscounts;
    }

    public List<Product> getDiscountProducts() {
        return discountProducts;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void addProduct(Product product) {
        discountProducts.add(product);
    }

    public DiscountEnum getDiscountState() {
        return discountState;
    }

    public Product getProduct() {
        return product;
    }

    public static ArrayList<Product> productsHaveDiscount() {
        ArrayList<Product> products = new ArrayList<>();
        for (Product discountProduct : discountProducts) {
            products.add(discountProduct);
        }
        return products;
    }

    public static boolean doesThisProductHaveDiscount(Product product) {
        for (Product discountProduct : discountProducts) {
            if (discountProduct.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public static Discount getProductDiscount(Product product) {
        for (Discount discount : allDiscounts) {
            if (discount.getProduct().equals(product)) {
                return discount;
            }
        }
        return null;
    }

    public void deleteProduct(Product product) {
        discountProducts.remove(product);
        for (Discount discount : allDiscounts) {
            if (discount.getProduct().equals(product)) {
                allDiscounts.remove(discount);
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
