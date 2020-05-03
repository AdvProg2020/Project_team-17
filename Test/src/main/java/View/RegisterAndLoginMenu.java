package View;

import Controller.RegisterAndLoginManager;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterAndLoginMenu extends Menu {
    private RegisterAndLoginManager registerAndLoginManager;
    public Customer currentCustomer;
    public Seller currentSeller;
    public  Manager currentManager;

    public RegisterAndLoginMenu(Menu parentMenu) {
        super("Register And Login ", parentMenu);
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public Seller getCurrentSeller() {
        return currentSeller;
    }

    public Manager getCurrentManager() {
        return currentManager;
    }
}