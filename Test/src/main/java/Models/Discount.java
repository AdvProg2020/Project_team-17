package Models;

import java.util.ArrayList;
import java.util.Date;

public class Discount {
    private String discountId;
    private ArrayList<Product> discountProducts;
    private Date startDate;
    private Date endDate;
    private double discountPercent;
    private DiscountEnum discountState;

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

    public void addDiscountToProduct(Product product) {

    }

    public void deleteDiscountFromProduct(Product product) {

    }
}
