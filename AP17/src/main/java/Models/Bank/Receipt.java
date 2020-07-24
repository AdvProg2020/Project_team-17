package Models.Bank;

import java.io.Serializable;
import java.util.ArrayList;

public class Receipt implements Serializable {
    private int id;
    private String type;
    private double money;
    private int sourceId;
    private int destinationId;
    private String description;
    private boolean paid;
    private static ArrayList<Receipt> allReceipts = new ArrayList<>();

    public Receipt(String type, double money, int sourceId, int destinationId, String description) {
        this.type = type;
        this.money = money;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.description = description;
        this.paid = false;
        this.id = allReceipts.size() + 1000;
        allReceipts.add(this);
    }

    public static Receipt getReceiptById(int id) {
        for (Receipt receipt : allReceipts) {
            if (receipt.getId() == id) {
                return receipt;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Receipt> getAllReceipts() {
        return allReceipts;
    }
}