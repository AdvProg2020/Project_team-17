package View;

public class ManagingCart extends Menu {
    public ManagingCart( Menu parentMenu) {
        super("View Cart", parentMenu);
        //other options
        //purchase
    }
    protected Menu showProducts(){
        return new Menu("Show Products",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
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
    protected Menu increaseProduct(){
        return new Menu("Increase Product",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu decreaseProduct(){
        return new Menu("Decrease Product",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu showTotalPrice(){
        return new Menu("Show total Price",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

}
