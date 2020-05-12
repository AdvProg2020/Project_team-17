package Models.Logs;

import Models.Product;

import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {
    private double discountAmount;
    private String sellerName;
    private static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();

    public BuyLog(String id, Date date, double paymentAmount, String address, String phoneNumber, String customerName,
                  ArrayList<Product> products, boolean isReceived, double discountAmount, String sellerName) {
        super(id, date, paymentAmount, address, phoneNumber, customerName, products, isReceived);
        this.discountAmount = discountAmount;
        this.sellerName = sellerName;
        allBuyLogs.add(this);
    }

    public String getSellerName() {
        return sellerName;
    }

    public String showOrders() {
        return "Order ID: " + this.getId() + "-----> Products: " + this.getAllProducts();
    }

    public static boolean isThereBuyLogWithThisId(String id) {
        for (BuyLog buyLog : allBuyLogs) {
            if (buyLog.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static BuyLog getButLogWithId(String id) {
        for (BuyLog buyLog : allBuyLogs) {
            if (buyLog.getId().equals(id)) {
                return buyLog;
            }
        }
        return null;
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