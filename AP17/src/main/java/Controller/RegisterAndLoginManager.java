package Controller;

import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Accounts.Supporter;

public class RegisterAndLoginManager {

    public static boolean isUserNameAndPasswordCorrectForManager(String userName, String password) {
        if (Manager.getManagerByUserName(userName).getPassword().equals(password)) {
            return true;
        }
        return false;
    }


    public static boolean isUserNameAndPasswordCorrectForCustomer(String userName, String password) {
        if (Customer.getCustomerByName(userName).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static boolean isUserNameAndPasswordCorrectForSeller(String userName, String password) {
        if (Seller.getSellerByName(userName).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static boolean isUserNameAndPasswordCorrectForSupporter(String userName, String password) {
        if (Supporter.getSupporterByUserName(userName).getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public static boolean canHaveAccountWithThisUsername(String username) {
        if (Seller.isThereSellerWithUserName(username) || Customer.isThereCustomerWithUserName(username) || Manager.isThereManagerWithUserName(username) || Supporter.isThereSupporterWithUserName(username)) {
            return false;
        }
        return true;
    }
}