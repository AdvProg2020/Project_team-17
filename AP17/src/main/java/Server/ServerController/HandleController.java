package Server.ServerController;

import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.ManagerController;

public class HandleController {

    //System.out.println(command);
    public static void handleFunction(String command) throws Exception {
        switch (command) {
            case "Login Manager": {
                RegisterAndLoginController.loginManager();
                break;
            }
            case "Register Manager": {
                RegisterAndLoginController.registerManager();
                break;
            }

            case "Register Seller": {
                RegisterAndLoginController.registerSeller();
                break;
            }
            case "Login Seller": {
                RegisterAndLoginController.loginSeller();
                break;
            }
            case "Login Customer": {
                RegisterAndLoginController.loginCustomer();
                break;
            }
            case "Register Customer": {
                RegisterAndLoginController.registerCustomer();
                break;
            }
            case "Login Supporter": {
                RegisterAndLoginController.loginSupporter();
                break;
            }
            case "Register Supporter": {
                ManagerController.addSupporter();
                break;
            }

            /////
            case "Show Customer Info" : {
                CustomerController.showCustomerInfo();
                break;
            }
            case "Edit Customer Info" : {
                CustomerController.editCustomerInfo();
                break;
            }
            case "Show Buy Logs" : {
                CustomerController.showCustomerLogs();
                break;
            }
            case "Show Buy Log" : {
                CustomerController.showLog();
                break;
            }
            case "Show Discount Codes" : {
                CustomerController.showDiscountCodes();
                break;
            }
            case "Show Discount Code": {
                CustomerController.showDiscountCode();
                break;
            }
            case "Show Product And State": {
                CustomerController.showProductState();
                break;
            }
            case "Show Online Supporter": {
                CustomerController.showOnlineSupporters();
                break;
            }
            case "Show All Auction": {
                CustomerController.showAuctions();
                break;
            }
            case "Join Auction": {
                CustomerController.joinAuction();
                break;
            }
            case "Bid Auction": {
                CustomerController.bidAuction();
                break;
            }

            ////////

        }
    }
}
