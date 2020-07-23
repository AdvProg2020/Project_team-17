package Server.ServerController;

import Client.Client;
import Models.Accounts.*;
import Server.ClientHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class RegisterAndLoginController {
    public static void registerManager() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getManager(split[0]) != null && DataBaseForServer.getCustomer(split[0]) != null && DataBaseForServer.getSeller(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is an account with this username"));
        } else {
            ClientHandler.sendObject("Done");
            DataBaseForServer.addManager(new Manager(split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
        }
    }

    public static void loginManager() {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        Manager manager = DataBaseForServer.getManager(split[0]);

        if (manager != null) {
            if (manager.getPassword().equals(split[1])) {
                ClientHandler.sendObject(manager);
            } else {
                ClientHandler.sendObject(new Exception("wrong password"));
            }
        } else {
            ClientHandler.sendObject(new Exception("wrong username"));
        }
    }

    public static void registerSeller() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getManager(split[0]) != null && DataBaseForServer.getCustomer(split[0]) != null && DataBaseForServer.getSeller(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is an account with this username"));
        } else {
            ClientHandler.sendObject("Done");
            DataBaseForServer.addSeller(new Seller(split[0], split[1], split[2], split[3], split[4], split[5], 0, split[6], split[7]));
        }
    }

    public static void loginSeller() {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        Seller seller = DataBaseForServer.getSeller(split[0]);

        if (seller != null) {
            if (seller.getPassword().equals(split[1])) {
                ClientHandler.sendObject(seller);
            } else {
                ClientHandler.sendObject(new Exception("wrong password"));
            }
        } else {
            ClientHandler.sendObject(new Exception("wrong username"));
        }
    }


}
