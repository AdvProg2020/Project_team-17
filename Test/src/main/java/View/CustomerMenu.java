package View;

import Controller.AccountsManager.CustomerAbilitiesManager;

import java.util.HashMap;

public class CustomerMenu extends Menu {
    private CustomerAbilitiesManager customerAbilitiesManager;
    public CustomerMenu( Menu parentMenu) {
        super("Customer Menu", parentMenu);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, viewPersonalInfo());
        submenus.put(2, new ManagingCart(this));
        submenus.put(3, new PurchaseMenu(this));
        submenus.put(4, new ManagingOrders(this));

    }

    protected Menu ManagingOrders() {
        return new Menu("managing orders", this) {
            @Override
            public void show() {
                System.out.println("1.show orders");
                System.out.println("2.rate");
            }

            @Override
            public void execute() {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 1){
                    //sabegheye kharid
                }else if (input == 2){
                  //  CustomerAbilitiesManager.rateProduct();
                }

            }
        };
    }
    protected Menu LogoutAccount() {
        return new Menu("Logout", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu viewPersonalInfo(){
        return new Menu("View Personal Info",this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {
            }
        };
    }
    protected Menu editField(){
        return new Menu("Edit Field",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu viewBalance(){
        return new Menu("View Balance",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu viewDiscountCodes(){
        return new Menu("View Discount Code",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

}
