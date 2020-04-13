package View.Menu;

public class CustomerMenu extends Menu {
    public CustomerMenu( Menu parentMenu) {
        super("Customer Menu", parentMenu);
        //other options
    }
    protected Menu RegisterAccount() {
        return new Menu("Register", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

    protected Menu LoginAccount() {
        return new Menu("Login", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

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
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
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
