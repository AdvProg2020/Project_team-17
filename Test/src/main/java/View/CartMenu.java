package View;


import Models.Accounts.Customer;

public class CartMenu extends Menu {
    PurchaseMenu purchaseMenu = new PurchaseMenu(this);
    public CartMenu( Menu parentMenu) {
        super("Cart", parentMenu);
    }


    @Override
    public void show() {
        System.out.println("1.show products");
        System.out.println("2.view product");
        System.out.println("3.increase product");
        System.out.println("4.decrease product");
        System.out.println("5.show total price");
        System.out.println("6.purchase");
        System.out.println("7.back");
    }

    @Override
    public void execute() {
        int input = Integer.parseInt(scanner.nextLine());
        if (input == 1) {
            showProducts();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 2) {
            viewProduct();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 3) {
            increaseProduct();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 4) {
            decreaseProduct();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 5) {
            showTotalPrice();
            parentMenu.show();
            parentMenu.execute();
        } else if (input == 6) {
            purchaseMenu.show();
            purchaseMenu.execute();
        } else if (input == 7) {
            parentMenu.show();
            parentMenu.execute();
        }
    }
    public void showProducts(){}
    public void viewProduct(){}
    public void increaseProduct(){}
    public void decreaseProduct(){}
    public void showTotalPrice(){}

}
