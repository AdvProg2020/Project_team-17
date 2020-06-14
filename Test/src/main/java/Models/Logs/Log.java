package Models.Logs;

import Models.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public abstract class Log {
    protected String id;
    protected LocalDate date;
    protected double paymentAmount;
    protected String address;
    protected String phoneNumber;
    protected ArrayList<Product> allProducts;
    protected String name;
    protected boolean isReceived;
    protected double discountAmount;
    private static ArrayList<Log> allLogs = new ArrayList<>();

    public Log(String id, LocalDate date, double paymentAmount, String address, String phoneNumber, String name,
               ArrayList<Product> products, boolean isReceived, double discountAmount) {
        this.id = id;
        this.date = date;
        this.paymentAmount = paymentAmount;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.allProducts = products;
        this.name = name;
        this.isReceived = isReceived;
        this.discountAmount = discountAmount;
        allLogs.add(this);
    }

    public static Log getLogWithId(String id) {
        for (Log log : allLogs) {
            if (log.getId().equals(id)) {
                return log;
            }
        }
        return null;
    }

    @Override
    public abstract String toString();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ArrayList<Log> getAllLogs() {
        return allLogs;
    }

    public boolean doesLogHaveThisProduct(String id) {
        for (Product product : allProducts) {
            if (product.getProductId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Product> getAllProducts() {
        return allProducts;
    }
}
