package View.Menu;

public class ManagingOrders extends Menu {
    public ManagingOrders( Menu parentMenu) {
        super("View Orders", parentMenu);
    }
    protected Menu allOrders(){
        return new Menu("All Orders",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu showOrder(){
        return new Menu("Show Order",this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu rateOrder(){
        return new Menu("Rate Order",this) {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }

}
