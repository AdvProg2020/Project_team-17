package View;

public class ManagingAccount extends Menu {
    public ManagingAccount( Menu parentMenu) {
        super("Managing Account", parentMenu);
        //other options with hashmap
    }
    protected Menu viewUserName(){
        return new Menu("View Username", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu deleteAccount(){
        return new Menu("Delete User", this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu createMangerProfile(){
        return new Menu("Create Manger Profile",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

}
