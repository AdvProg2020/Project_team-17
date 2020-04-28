package View;

import Controller.ProductsManager;

public class ProductMenu extends Menu {
    private ProductsManager productsManager;
    public ProductMenu(Menu parentMenu) {
        super("Product Menu", parentMenu);
        //other options
    }
    private Menu getDigest(){
        return new Menu("Digest",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
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
