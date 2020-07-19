package Client.ClientController;

import Client.Client;

public class CRegisterAndLoginController {

    public static void registerSeller() {
        String func = "Add Seller Account";
        Client.sendMessage(func);
    }

    public static void registerManager(){
        String func = "Add Manager Account";
        Client.sendMessage(func);
    }

    public static void registerCustomer() {
        String func = "Add Customer Account";
        Client.sendMessage(func);
    }

    //TODO handle login and multi accounts login


}
