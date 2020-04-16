package View;

public class PurchaseMenu extends Menu {
    public PurchaseMenu( Menu parentMenu) {
        super("Purchase", parentMenu);
    }
    public void receivingInformation(){
        System.out.println("enter your receiving address");
    }
    public void receivingDiscountCode(){

    }
    public void payment(){

    }
    protected Menu purchase() {
        return new Menu("purchase", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
            receivingInformation();
            receivingDiscountCode();
            payment();
            }
        };
    }
}
