package View.Menu;

public class ManagingAllProducts extends Menu {
    public ManagingAllProducts( Menu parentMenu) {
        super("Managing All Products", parentMenu);
        //other options
    }
    protected Menu removeProduct(){
        return new Menu("Remove",this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {
            }
        };
    }
}
