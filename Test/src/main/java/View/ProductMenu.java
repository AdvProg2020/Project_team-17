package View;

import Controller.ProductManager;

public class ProductMenu extends Menu {
    private ProductManager productManager;
    public ProductMenu(Menu parentMenu) {
        super("Product Menu", parentMenu);
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
