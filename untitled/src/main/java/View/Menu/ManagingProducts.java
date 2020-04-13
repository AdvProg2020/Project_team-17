package View.Menu;

public class ManagingProducts extends Menu {
    public ManagingProducts( Menu parentMenu) {
        super("Managing Products", parentMenu);
        //other options
    }
    protected Menu viewProduct(){
        return new Menu("View Product",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu viewBuyers(){
        return new Menu("View Buyers",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu editProductId(){
        return new Menu("Edit ProductId",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
            }
        };
    }

}
