package Client.ClientController.AccountsController;

import Client.Client;
import Models.Accounts.Supporter;

import java.util.ArrayList;

public class CSupporterController {
    private static ArrayList<Supporter> onlineSupporters = new ArrayList<>();
    private static Supporter supporter;

    public static Supporter getSupporter() {
        return supporter;
    }

    public static void setSupporter(Supporter supporter) {
        CSupporterController.supporter = supporter;
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

    public static String showSupporterInfo() throws Exception {
        String func = "Show Supporter Info";
        Client.sendMessage(func);

        String username = getSupporter().getUserName();
        Client.sendMessage(username);

        try {
            Object data = Client.receiveObject();
            return String.valueOf(data);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

