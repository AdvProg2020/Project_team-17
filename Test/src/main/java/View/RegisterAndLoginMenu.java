package View;

import Controller.RegisterAndLoginManager;

public class RegisterAndLoginMenu extends Menu {
    private RegisterAndLoginManager registerAndLoginManager;
    public RegisterAndLoginMenu( Menu parentMenu) {
        super("Register And Login Menu", parentMenu);
        //other options
    }
    private Menu registerMenu() {
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

    private Menu loginMenu() {
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
