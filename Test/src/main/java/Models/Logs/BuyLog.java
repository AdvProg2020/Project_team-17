package Models.Logs;

import Models.Product;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {
  private double discountAmount;
  private String sellerName;

    public BuyLog(String id, Date date, double paymentAmount, String address, String phoneNumber, String customerName,
                  ArrayList<Product> products, boolean isReceived, double discountAmount, String sellerName) {
        super(id, date, paymentAmount, address, phoneNumber, customerName, products, isReceived);
        this.discountAmount = discountAmount;
        this.sellerName = sellerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    @Override
    public String toString() {
        return "BuyLog{" +
                "discountAmount=" + discountAmount +
                ", sellerName='" + sellerName + '\'' +
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
