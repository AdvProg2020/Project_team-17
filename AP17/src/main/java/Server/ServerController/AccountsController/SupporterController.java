package Server.ServerController.AccountsController;

import Models.Accounts.Supporter;

import java.util.ArrayList;

public class SupporterController {
    private static ArrayList<Supporter> onlineSupporters = new ArrayList<>();
    private static Supporter supporter;

    public Supporter getSupporter() {
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
}
