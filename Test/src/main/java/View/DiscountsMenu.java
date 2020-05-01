package View;

public class DiscountsMenu extends Menu {
    public DiscountsMenu(Menu parentMenu) {
        super("Discounts Menu", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.offs");
        System.out.println("2.show product");
        System.out.println("3.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1){
            showProducts();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 2){
            showProduct();
            parentMenu.show();
            parentMenu.execute();
        }else if (input == 3){
            parentMenu.show();
            parentMenu.execute();
        }
    }
    public void showProducts(){

    }
    public void showProduct(){
        System.out.println("show product [productId]");
    }
}
