package Models;

public class PointOfView {
    private Account account;
    private Product product;
    private String pointOfViewStringText;
    private pointOfViewEnum pointOfViewState;
    private boolean isAccountBuyer;
    //baraye in k aya nazardahande in kala ra kharide ast nazari nemidonam chi kar bayad bokonim?

    public PointOfView(Account account,Product product, String pointOfViewStringText, pointOfViewEnum pointOfViewState) {
        this.account=account;
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

    public pointOfViewEnum getPointOfViewState() {
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

    public void setPointOfViewState(pointOfViewEnum pointOfViewState) {
        this.pointOfViewState = pointOfViewState;
    }
}
