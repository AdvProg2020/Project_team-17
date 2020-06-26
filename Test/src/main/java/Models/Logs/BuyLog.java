package Models.Logs;

import Models.Product;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class BuyLog extends Log {

    private static ArrayList<BuyLog> allBuyLogs = new ArrayList<>();

    public BuyLog(LocalDate date, double paymentAmount, String address, String phoneNumber, String sellerName,
                  ArrayList<Product> products, boolean isReceived, double discountAmount) throws IOException {
        super("Buy log ---> " + (getAllLogs().size() + 1), date, paymentAmount, address, phoneNumber, sellerName, products, isReceived, discountAmount);
        allBuyLogs.add(this);
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
                "id='" + id + '\'' +
                ", date=" + date +
                ", paymentAmount=" + paymentAmount +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", allProducts=" + allProducts +
                ", name='" + name + '\'' +
                ", isReceived=" + isReceived +
                ", discountAmount=" + discountAmount +
                '}';
    }
}