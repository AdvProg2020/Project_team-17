package Models;

import Models.Enums.DiscountEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Discount {
    private String discountId;
    private List<Product> discountProducts;
    private static ArrayList<Discount> allDiscounts = new ArrayList<>();
    private LocalDate startDate;
    private LocalDate endDate;
    private double discountPercent;
    private DiscountEnum discountState;

    public Discount(String discountId, LocalDate startDate, LocalDate endDate, double discountPercent, List<Product> products) {
        this.discountId = discountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercent = discountPercent;
        this.discountProducts = products;
        this.discountState = DiscountEnum.PROCESSING;
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

    public static ArrayList<String> showProductsHaveDiscount() {
        ArrayList<String> productInfo = new ArrayList<>();
        for (Discount discount : allDiscounts) {
            for (Product product : discount.discountProducts) {
                productInfo.add("Product ID : " + product.getProductId() + "Price before discount :" + product.getPrice() + " " +
                        "Price after discount : " + product.getProductPriceAfterDiscount(discount));
            }
        }
        return productInfo;
    }

    public static boolean doesThisProductHaveDiscount(Product product) {
        for (Discount allDiscount : allDiscounts) {
            for (Product discountProduct : allDiscount.discountProducts) {
                if (discountProduct.equals(product)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Discount getProductDiscount(Product product) {
        for (Discount discount : allDiscounts) {
            for (Product discountProduct : discount.discountProducts) {
                if (discountProduct.equals(product)) {
                    return discount;
                }
            }
        }
        return null;
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
