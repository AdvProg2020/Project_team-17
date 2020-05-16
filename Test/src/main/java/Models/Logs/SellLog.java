package Models.Logs;

import Models.Product;

import java.util.ArrayList;
import java.util.Date;

public class SellLog extends Log {
    private double discountAmount;
    private String buyerName;

    public SellLog(String id, Date date, double paymentAmount, String address, String phoneNumber, String customerName, ArrayList<Product> allProducts, boolean isReceived, double discountAmount, String buyerName) {
        super(id, date, paymentAmount, address, phoneNumber, customerName, allProducts, isReceived);
        this.discountAmount = discountAmount;
        this.buyerName = buyerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    @Override
    public String toString() {
        return "SellLog{" +
                "discountAmount=" + discountAmount +
                ", buyerName='" + buyerName + '\'' +
                ", id='" + id + '\'' +
                ", date=" + date +
                ", paymentAmount=" + paymentAmount +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", allProducts=" + allProducts +
                ", customerName='" + customerName + '\'' +
                ", isReceived=" + isReceived +
                '}';
    }
}
