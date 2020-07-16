package Client.ClientController;

import Client.Client;

public class RegisterAndLoginController {

    public static void registerSeller(String username, String firstName, String lastName,
                                  String email, String phoneNumber, String password,String extraInfo) throws Exception {
        String func = "Add Seller Account";
        Client.sendMessage(func);

        String info = username + " " + firstName + " " + lastName + " " + email + " " + phoneNumber + " " + password+" "+extraInfo;

        Client.sendMessage(info);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there is an account with this username");
    }

    public static void registerManager(String username, String firstName, String lastName,
                                  String email, String phoneNumber, String password) throws Exception {
        String func = "Add Manager Account";
        Client.sendMessage(func);

        String info = username + " " + firstName + " " + lastName + " " + email + " " + phoneNumber + " " + password;

        Client.sendMessage(info);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there is an account with this username");
    }

    public static void registerCustomer(String username, String firstName, String lastName,
                                 String email, String phoneNumber, String password,String extraInfo,String address) throws Exception {
        String func = "Add Customer Account";
        Client.sendMessage(func);

        String info = username + " " + firstName + " " + lastName + " " + email + " " + phoneNumber + " " + password+" "+extraInfo+" "+address;

        Client.sendMessage(info);
        Object response = Client.receiveObject();

        if (response instanceof Exception)
            throw new Exception("there is an account with this username");
    }


}
