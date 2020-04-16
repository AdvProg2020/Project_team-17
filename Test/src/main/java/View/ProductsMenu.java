package View;

import Controller.ProductManager;
import Models.Manager;

import java.util.ArrayList;

public class ProductsMenu extends Menu {
    private ProductManager productManager;
    public ProductsMenu(Menu parentMenu) {
        super("Products Menu", parentMenu);
        //other options
    }
    protected Menu viewCategory(){
        return new Menu("View Category",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

    protected Menu showAllProduct(){
        return new Menu("Show Products",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu ShowProductByGettingId(){
        return new Menu("Show Product",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }





}
