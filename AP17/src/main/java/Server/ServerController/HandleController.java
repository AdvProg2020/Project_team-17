package Server.ServerController;

import Server.ServerController.*;
import Server.ServerController.AccountsController.ManagerController;

public class HandleController {

    //System.out.println(command);
    public static void handleFunction(String command) throws Exception {
        switch (command) {
            case "Log in": {
                RegisterAndLoginController.login();
                break;
            }
            case "Register": {
                RegisterAndLoginController.register();
                break;
            }

        }

    }
}
