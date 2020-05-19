package Controller;

import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;

public class RegisterAndLoginManager {

    public static void isUserNameAndPasswordCorrectForManager(String userName, String password) throws Exception {
        if (Manager.isThereManagerWithUserName(userName)) {
            if (Manager.getManagerByUserName(userName).getPassword().equals(password)) {
            } else {
                throw new Exception("Password is incorrect");
            }
        } else {
            throw new Exception("There isn't any manager with this username");
        }
    }


    public static void isUserNameAndPasswordCorrectForCustomer(String userName, String password) throws Exception {
        //nemidonam inja doroste k to if hichi nazashtim
        if (Customer.isThereCustomerWithUserName(userName)) {
            if (Customer.getCustomerByName(userName).getPassword().equals(password)) {
            } else {
                throw new Exception("Password is incorrect");
            }
        } else {
            throw new Exception("There isn't any customer with this username");
        }
    }

    public static void isUserNameAndPasswordCorrectForSeller(String userName, String password) throws Exception {
        if (Seller.isThereSellerWithUserName(userName)) {
            if (Seller.getSellerByName(userName).getPassword().equals(password)) {
            } else {
                throw new Exception("Password is incorrect");
            }
        } else {
            throw new Exception("There isn't any seller with this username");
        }
    }
}
