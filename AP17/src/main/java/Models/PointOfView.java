package Models;

import Models.Accounts.Account;

import java.io.Serializable;
import java.util.ArrayList;

public class PointOfView implements Serializable {
    private Account account;
    private Product product;
    private String pointOfViewStringText;
    private boolean hasBought;
    private static ArrayList<PointOfView> allPointOfViews = new ArrayList<>();

    public PointOfView(Account account, Product product, String pointOfViewStringText, boolean hasBought) {
        this.account = account;
        this.product = product;
        this.pointOfViewStringText = pointOfViewStringText;
        this.hasBought = hasBought;
        allPointOfViews.add(this);
    }

    public Account getAccount() {
        return account;
    }

    public Product getProduct() {
        return product;
    }

    public String getPointOfViewStringText() {
        return pointOfViewStringText;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPointOfViewStringText(String pointOfViewStringText) {
        this.pointOfViewStringText = pointOfViewStringText;
    }

    public static ArrayList<PointOfView> getAllPointOfViews() {
        return allPointOfViews;
    }
}
