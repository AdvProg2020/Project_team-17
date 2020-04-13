package View;

public class RegisterAndLoginMenu extends Menu {
    public RegisterAndLoginMenu( Menu parentMenu) {
        super("Register And Login Menu", parentMenu);
        //other options
    }
    private Menu getRegisterMenu() {
        return new Menu("register", this) {
            @Override
            public void show() {
                //TODO
            }

            @Override
            public void execute() {
                //TODO
            }
        };
    }

    private Menu getLoginMenu() {
        return new Menu("login", this) {
            @Override
            public void show() {
                //TODO
            }

            @Override
            public void execute() {
                //TODO
            }
        };
    }


}
