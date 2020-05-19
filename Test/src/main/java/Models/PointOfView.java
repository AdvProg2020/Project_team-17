package Models;

import Models.Accounts.Account;

public class PointOfView {
    private Account account;
    private Product product;
    private String pointOfViewStringText;
    private boolean hasBought;

    public PointOfView(Account account, Product product, String pointOfViewStringText, boolean hasBought) {
        this.account = account;
        this.product = product;
        this.pointOfViewStringText = pointOfViewStringText;
        this.hasBought = hasBought;
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

}
