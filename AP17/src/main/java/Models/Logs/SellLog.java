package Models.Logs;

import Models.Product;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SellLog extends Log {
    public SellLog( LocalDate date, double paymentAmount, String address, String phoneNumber, String customerName, ArrayList<Product> products, boolean isReceived, double discountAmount) throws IOException {
        super("Sell Log ---> "+(getAllLogs().size()+1), date, paymentAmount, address, phoneNumber, customerName, products, isReceived, discountAmount);
    }

    @Override
    public String toString() {
        return "SellLog{" +
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
