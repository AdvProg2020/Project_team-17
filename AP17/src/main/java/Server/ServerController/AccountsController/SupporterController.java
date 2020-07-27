package Server.ServerController.AccountsController;

import Models.Accounts.Manager;
import Models.Accounts.Supporter;
import Server.ClientHandler;
import Server.ServerController.DataBaseForServer;

import java.util.ArrayList;

public class SupporterController {
    private static ArrayList<Supporter> onlineSupporters = new ArrayList<>();
    private static Supporter supporter;

    public static Supporter getSupporter() {
        return supporter;
    }

    public static void setSupporter(Supporter supporter) {
        SupporterController.supporter = supporter;
    }

    public static ArrayList<Supporter> getOnlineSupporters() {
        return onlineSupporters;
    }

    public static void addOnlineSupporters(Supporter supporter) {
        onlineSupporters.add(supporter);
    }

    public static void removeOnlineSupporter(Supporter supporter) {
        onlineSupporters.remove(supporter);
    }

    public static void showSupporterInfo() throws Exception {
        if (getSupporter() == null) {
            ClientHandler.sendObject(new Exception("there isn't any supporter logged in"));
        } else {
            String username = ClientHandler.receiveMessage();
            Supporter supporter = DataBaseForServer.getSupporter(username);
            setSupporter(supporter);
            ClientHandler.sendObject(supporter.toString());
        }
    }
}
