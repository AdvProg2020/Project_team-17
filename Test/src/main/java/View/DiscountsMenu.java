package View;

public class DiscountsMenu extends Menu {
    public DiscountsMenu( Menu parentMenu) {
        super("Discounts Menu", parentMenu);
        //other options
    }
    protected Menu showDiscountProducts() {
        return new Menu("Show Discount Product", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
}
