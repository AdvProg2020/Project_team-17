package View;

public class ProductsMenu extends Menu {
    public ProductsMenu(Menu parentMenu) {
        super("Products Menu", parentMenu);
        //other options
    }
    protected Menu productsAttributes(){
        return new Menu("Attributes",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu CompareProducts(){
        return new Menu("Compare Products",this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {
            }
        };
    }


}
