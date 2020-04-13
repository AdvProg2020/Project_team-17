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


}
