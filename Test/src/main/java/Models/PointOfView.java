package Models;

import Models.Accounts.Account;
import Models.Enums.PointOfViewEnum;

public class PointOfView {
    private Account account;
    private Product product;
    private String pointOfViewStringText;
    private PointOfViewEnum pointOfViewState;
    private boolean hasBought;

    public PointOfView(Account account, Product product, String pointOfViewStringText, PointOfViewEnum pointOfViewState,boolean hasBought) {
        this.account = account;
        this.product = product;
        this.pointOfViewStringText = pointOfViewStringText;
        this.pointOfViewState = pointOfViewState;
        this.hasBought= hasBought;
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

    public PointOfViewEnum getPointOfViewState() {
        return pointOfViewState;
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

    public void setPointOfViewState(PointOfViewEnum pointOfViewState) {
        this.pointOfViewState = pointOfViewState;
    }
}
