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
            case "Show Customer Info": {
                CustomerController.showCustomerInfo();
                break;
            }
            case "Edit Customer Info": {
                CustomerController.editCustomerInfo();
                break;
            }
            case "Show Customer Buy Logs": {
                CustomerController.showCustomerLogs();
                break;
            }
            case "Show Customer Buy Log": {
                CustomerController.showLog();
                break;
            }
            case "Show Customer Discount Codes": {
                CustomerController.showDiscountCodes();
                break;
            }
            case "Show Customer Discount Code": {
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

            case "Show Manager Info": {
                ManagerController.showManagerInfo();
                break;
            }
            case "Edit Manager Info": {
                ManagerController.editManagerInfo();
                break;
            }
            case "Show Manager Requests": {
                ManagerController.showRequests();
                break;
            }
            case "Show Request": {
                ManagerController.showRequest();
                break;
            }
            case "Accept Request": {
                ManagerController.acceptRequest();
                break;
            }
            case "Decline Request": {
                ManagerController.declineRequest();
                break;
            }
            case "Show Manager Discount Codes": {
                ManagerController.showDiscountCodes();
                break;
            }
            case "Show Manager Discount Code": {
                ManagerController.showDiscountCode();
                break;
            }
            case "Edit Discount Code": {
                ManagerController.editDiscountCodeInfo();
                break;
            }
            case "Delete Discount Code": {
                ManagerController.deleteDiscountCode();
                break;
            }
            case "Create Discount Code": {
                ManagerController.createDiscountCode();
                break;
            }
            case "Show All Users": {
                ManagerController.showAllUsers();
                break;
            }
            case "Show User": {
                ManagerController.showUser();
                break;
            }
            case "Delete User": {
                ManagerController.deleteUser();
                break;
            }
            case "Add Manager": {
                ManagerController.addManager();
                break;
            }
            case "Add Supporter": {
                ManagerController.addSupporter();
                break;
            }
            case "Show Categories": {
                ManagerController.showCategories();
                break;
            }
            case "Add Category": {
                ManagerController.createCategory();
                break;
            }
            case "Delete Category": {
                ManagerController.deleteCategory();
                break;
            }
            case "Edit Category": {
                ManagerController.editCategory();
                break;
            }
            case "Delete Product": {
                ManagerController.deleteProduct();
                break;
            }
            case "Show Products": {
                ManagerController.showProducts();
                break;
            }
            case "Show User Status": {
                ManagerController.showUserStatus();
                break;
            }
            case "Show  Logs": {
                ManagerController.showLogs();
                break;
            }
            case "Show Log": {
                ManagerController.showLog();
                break;
            }

        }
    }
}
