package View;

public class ManagingDiscount extends Menu {
    public ManagingDiscount( Menu parentMenu) {
        super("Managing Discount",parentMenu);
        //other options
    }
    public Menu viewDiscountId(){
        return new Menu("View DiscountId",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    public Menu addDiscount(){
        return new Menu("Add Discount",this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {

            }
        };
    }

}
