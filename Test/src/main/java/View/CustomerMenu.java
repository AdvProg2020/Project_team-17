package View;

import Controller.AccountsManager.CustomerAbilitiesManager;

public class CustomerMenu extends Menu {
    private CustomerAbilitiesManager customerAbilitiesManager;
    public CustomerMenu( Menu parentMenu) {
        super("Customer Menu", parentMenu);
        //other options
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
