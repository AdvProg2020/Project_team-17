package Controller;

import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;

public class RegisterAndLoginManager {
    public void registerAccount(String type, String username) {

    }

    public void isUserNameAndPasswordCorrectForManager(String userName, String password) throws Exception {
        if (Manager.isThereManagerWithUserName(userName)) {
            if (Manager.getManagerByUserName(userName).getPassword().equals(password)) {
            } else {
                throw new Exception("Password is incorrect");
            }
        } else {
            throw new Exception("There isn't any manager with this username");
        }
    }


    public void isUserNameAndPasswordCorrectForCustomer(String userName, String password) throws Exception {
        if (Customer.isThereCustomerWithUserName(userName)) {
            if (Customer.getCustomerByName(userName).getPassword().equals(password)) {
            } else {
                throw new Exception("Password is incorrect");
            }
        } else {
            throw new Exception("There isn't any customer with this username");
        }
    }

    public void isUserNameAndPasswordCorrectForSeller(String userName, String password) throws Exception {
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
