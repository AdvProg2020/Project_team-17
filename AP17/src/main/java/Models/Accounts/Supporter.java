package Models.Accounts;

import java.util.ArrayList;

public class Supporter extends Account {

    private static ArrayList<Supporter> allSupporters = new ArrayList<>();

    public Supporter(String userName, String firstName, String lastName, String email, String phoneNumber, String password, double credit,String path) {
        super(userName, firstName, lastName, email, phoneNumber, password, credit,path);
    }

    public static boolean isThereSupporterWithUserName(String username) {
        for (Supporter supporter : allSupporters) {
            if (supporter.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static Supporter getSupporterByUserName(String username) {
        for (Supporter supporter : allSupporters) {
            if (supporter.getUserName().equals(username)) {
                return supporter;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Supporter{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", credit=" + credit +
                '}';
    }
}
