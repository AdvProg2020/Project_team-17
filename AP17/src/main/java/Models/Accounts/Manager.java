package Models.Accounts;


import java.io.IOException;
import java.util.ArrayList;

public class Manager extends Account {
    private static ArrayList<Manager> allManagers = new ArrayList<>();

    public Manager(String userName, String firstName, String lastName, String email, String phoneNumber, String password,String path) throws IOException {
        super(userName, firstName, lastName, email, phoneNumber, password, 0,path);
        allManagers.add(this);
    }

    public static ArrayList<Manager> getAllManagers() {
        return allManagers;
    }

    public static void deleteManager(String userName) {
        allManagers.remove(getManagerByUserName(userName));
    }

    public static boolean isThereManagerWithUserName(String username) {
        for (Manager manager : allManagers) {
            if (manager.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static Manager getManagerByUserName(String username) {
        for (Manager manager : allManagers) {
            if (manager.getUserName().equals(username)) {
                return manager;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return "Manager{" +
                "username='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}

