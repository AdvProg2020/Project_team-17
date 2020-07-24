package Server.ServerController;

import Server.ServerController.AccountsController.ManagerController;

public class HandleController {

    //System.out.println(command);
    public static void handleFunction(String command) throws Exception {
        switch (command) {
            case "Login Manager": {
                RegisterAndLoginController.loginManager();
                break;
            }
            case "Register Manager": {
                RegisterAndLoginController.registerManager();
                break;
            }

            case "Register Seller": {
                RegisterAndLoginController.registerSeller();
                break;
            }
            case "Login Seller": {
                RegisterAndLoginController.loginSeller();
                break;
            }
            case "Login Customer": {
                RegisterAndLoginController.loginCustomer();
                break;
            }
            case "Register Customer": {
                RegisterAndLoginController.registerCustomer();
                break;
            }
            case "Login Supporter": {
                RegisterAndLoginController.loginSupporter();
                break;
            }
            case "Register Supporter": {
                ManagerController.addSupporter();
                break;
            }

        }

    }
}
