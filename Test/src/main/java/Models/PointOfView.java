package Models;

import Models.Accounts.Account;
import Models.Enums.PointOfViewEnum;

public class PointOfView {
    private Account account;
    private Product product;
    private String pointOfViewStringText;
    private PointOfViewEnum pointOfViewState;
    private boolean isAccountBuyer;
    //baraye in k aya nazardahande in kala ra kharide ast nazari nemidonam chi kar bayad bokonim?

    public PointOfView(Account account, Product product, String pointOfViewStringText, PointOfViewEnum pointOfViewState) {
        this.account = account;
        this.product = product;
        this.pointOfViewStringText = pointOfViewStringText;
        this.pointOfViewState = pointOfViewState;
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
