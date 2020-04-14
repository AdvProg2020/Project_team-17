package Models;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {
    private double amountPaid;
    private double discountAmount;
    private ArrayList<Product> boughtProducts;
    private String customerName;
    private BuyLogEnum buyLogEnum;

    public BuyLog(String logId, Date date, int amountPaid, double discountAmount, String customerName, BuyLogEnum buyLogEnum) {
        super(logId, date);
        this.amountPaid = amountPaid;
        this.discountAmount = discountAmount;
        this.customerName = customerName;
        this.buyLogEnum = buyLogEnum;
        boughtProducts = new ArrayList<>();
    }
    public void addProductToBuyLog(Product product){
        boughtProducts.add(product);
    }
    public void setBuyLogForCustomer(String customerName){
        Customer customer = Customer.getCustomerWithName(customerName);
        customer.setBuyLog(this);
    }
}
