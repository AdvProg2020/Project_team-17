package Client.ClientController;

import Client.Client;
import Models.Accounts.Account;
import Models.Accounts.Manager;
import Server.ServerController.AccountsController.ManagerController;

import java.util.HashMap;

public class CRegisterAndLoginController {


    public static void register(String dataToRegister) throws Exception {

        String func = "Register";
        Client.sendMessage(func);

        Client.sendObject(dataToRegister);
//        Object response = Client.receiveObject();
//        String responseString = (String) response;
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {

            }
        }catch(Exception e){
                throw new Exception(e.getMessage());
            }
//        if (responseString.equals("Done")) {
//            return;
//        } else if (response instanceof Exception) {
//            //TODO ask yani vaghti chand ta exception be dalayele mokhtalef throw mikone khob man mikham hamoon essage ro neshon bede injori chejori mitonam in karo bokonam k begam hamoon message ro neshon bede ?
//        }

    }

    public static void login(HashMap<String, String> dataToLogin) throws Exception {
        String func = "Log in";
        Client.sendMessage(func);

        Client.sendObject(dataToLogin);

        Object response = Client.receiveObject();

        if (response instanceof Exception) {
            throw new Exception("wrong info");
        } else {
            Account account = (Account) response;
            if (account.getRole().equals("Manager")) {
                ManagerController.setManager((Manager) account);
                return;
            }
        }
    }

    public static void registerAdmin(String data) {

        String func = "Register Admin";
        Client.sendMessage(func);

        Client.sendMessage(data);
    }

    public static boolean checkUsername(String username) {

        String func = "Check Username";
        Client.sendMessage(func);

        Client.sendMessage(username);

        return (boolean) Client.receiveObject();
    }


}
