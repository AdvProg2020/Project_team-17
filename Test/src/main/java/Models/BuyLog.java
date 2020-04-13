package Models;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {
    private int amountPaid;
    private double discountAmount;
    //baraye meghdar takhfife emal shode motmaen nistam dorost neveshtam ya na
    private ArrayList<Product> boughtProducts;
    private String customerName;
    private BuyLogEnum buyLogEnum;

    public BuyLog(String logId, Date date, int amountPaid, double discountAmount, ArrayList<Product> boughtProducts, String customerName, BuyLogEnum buyLogEnum) {
        super(logId, date);
        this.amountPaid = amountPaid;
        this.discountAmount = discountAmount;
        this.boughtProducts = boughtProducts;
        this.customerName = customerName;
        this.buyLogEnum = buyLogEnum;
    }
}
