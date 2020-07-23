package Client.ClientController;

import Client.Client;
import Client.ClientController.AccountsController.CManagerController;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Server.ServerController.AccountsController.ManagerController;

public class CRegisterAndLoginController {

    public static void registerManager(String dataToRegister) throws Exception {
        String func = "Register Manager";
        Client.sendMessage(func);

        Client.sendObject(dataToRegister);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = dataToRegister.split("\\s");
                new Manager(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void loginManager(String dataToLogin) throws Exception {
        String func = "Login Manager";
        Client.sendMessage(func);

        Client.sendObject(dataToLogin);
        try {
            Object response = Client.receiveObject();
            Manager manager = (Manager) response;
            ManagerController.setManager(manager);
            CManagerController.setManager(manager);
            ManagerController.addOnlineManager(manager);
            CManagerController.addOnlineManager(manager);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
