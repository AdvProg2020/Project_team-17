package View;

public class ManagerMenu extends Menu {
    public ManagerMenu( Menu parentMenu) {
        super("Manager Menu", parentMenu);
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

    protected Menu createDiscountCode(){
        return new Menu("Create Discount Code" , this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }


}
