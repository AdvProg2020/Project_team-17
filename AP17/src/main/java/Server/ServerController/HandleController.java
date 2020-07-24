package Server.ServerController;

import Models.Accounts.Seller;
import Server.ServerController.AccountsController.CustomerController;
import Server.ServerController.AccountsController.ManagerController;
import Server.ServerController.AccountsController.SellerController;

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
            case "Show Manager Categories": {
                ManagerController.showCategories();
                break;
            }
            case "Add Category": {
                ManagerController.createCategory();
                break;
            }
            case "Set Least Amount": {
                ManagerController.setLeastAmount();
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
            case "Show Manager Products": {
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
            case "Show Seller Info": {
                SellerController.showSellerInfo();
                break;
            }
            case "Edit Seller Info": {
                SellerController.editSellerInfo();
                break;
            }
            case "Show Sales History": {
                SellerController.showSellerLogs();
                break;
            }
            case "Show Sell Log": {
                SellerController.showLog();
                break;
            }
            case "Show Seller Categories": {
                SellerController.showCategories();
                break;
            }
            case "Show Discounts": {
                SellerController.showDiscounts();
                break;
            }
            case "Show Seller Products": {
                SellerController.showProducts();
                break;
            }
            case "Add Product Request": {
                SellerController.addProductRequest();
                break;
            }
            case "Edit Product Request": {
                SellerController.editProductRequest();
                break;
            }
            case "Add Discount Request": {
                SellerController.addDiscountRequest();
                break;
            }
            case "Edit Discount Request": {
                SellerController.editDiscountRequest();
                break;
            }
            case "Remove Product Request": {
                SellerController.removeProductRequest();
                break;
            }
            case "Add Auction": {
                SellerController.addAuction();
                break;
            }

            ////////

            case "Increase Product": {
                CartController.increaseProduct();
                break;
            }
            case "Decrease Product": {
                CartController.decreaseProduct();
                break;
            }
            case "Show Total Price": {
                CartController.showTotalPrice();
                break;
            }
            case "Show Total Price With Discount Code": {
                CartController.showTotalPriceWithDiscountCode();
                break;
            }
        }
    }
}
