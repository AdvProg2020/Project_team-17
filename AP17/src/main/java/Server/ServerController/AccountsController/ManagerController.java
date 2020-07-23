package Server.ServerController.AccountsController;

import Client.Client;
import Client.ClientController.CRegisterAndLoginController;
import Models.*;
import Models.Accounts.*;
import Models.Logs.BuyLog;
import Models.Request.Request;
import Server.ClientHandler;
import View.RegisterCustomerMenu;
import View.RegisterManagerMenu;
import View.RegisterSellerMenu;
import View.RegisterSupporterMenu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ManagerController {
    private static Manager manager;

    public ManagerController(Manager manager) {
        this.manager = manager;
    }

    public static Manager getManager() {
        return manager;
    }

    public static void setManager(Manager manager) {
        ManagerController.manager = manager;
    }

    public static void addAdminAccount() throws Exception {
        String newAdminDetails = ClientHandler.receiveMessage();
        System.out.println(newAdminDetails);
        Gson gson = new GsonBuilder().serializeNulls().create();
        Manager admin1 = gson.fromJson(newAdminDetails, Manager.class);
        if (CRegisterAndLoginController.checkUsername(admin1.getUserName())) {
            CRegisterAndLoginController.registerAdmin(newAdminDetails);
        } else {
            ClientHandler.sendObject(new Exception("username already exists"));
            return;
        }
        ClientHandler.sendMessage("Success!");
    }

    //    //public static String showManagerInfo() {
//        return (RegisterManagerMenu.getCurrentManager().toString());
//    }
//    public static void showAdminInfo() {
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        if (getManager() == null) {
//            ClientHandler.sendObject(new ExceptionsLibrary.NoAccountException());
//        }
//        String username = ClientHandler.receiveMessage();
//        Manager manager = (Manager) GetDataFromDatabaseServerSide.getAccount(username);
//        setManager(manager);
//        String data = gson.toJson(manager);
//        ClientHandler.sendMessage(data);
//    }


    public static void editManagerInfo(Manager manager, String field, String newContentForThisField) throws Exception {
//        Manager manager = RegisterManagerMenu.getCurrentManager();
        if (field.equalsIgnoreCase("first name")) {
            manager.changeFirstName(manager, newContentForThisField);
        } else if (field.equalsIgnoreCase("last name")) {
            manager.changeLastName(manager, newContentForThisField);
        } else if (field.equalsIgnoreCase("email")) {
            manager.changeEmail(manager, newContentForThisField);
        } else if (field.equalsIgnoreCase("phone number")) {
            manager.changePhoneNumber(manager, newContentForThisField);
        } else if (field.equalsIgnoreCase("password")) {
            manager.changePassword(manager, newContentForThisField);
        }
    }

    public static void createManager(String username, String firstName, String lastName, String email, String phoneNumber, String password, String path) throws IOException {
        new Manager(username, firstName, lastName, email, phoneNumber, password, path);
    }

    public static void showManagerRequests() {
        ArrayList<Request> allRequests = new ArrayList<>();
        allRequests.addAll(Request.showRequestsNeedToBeAccepted());
        Client.sendObject(allRequests);
    }

    public static String showRequest(String id) {
        Request request = Request.getRequestById(id);
        String string = "";
        if (request != null) {
            string = request.toString();
        } else {
            Client.sendObject(new Exception("there isn't any request with this id"));
        }
        return string;
    }

    public static void processRequest(String requestId, String requestState) throws Exception {
        Object[] toSend = new Object[2];
        toSend[0] = requestId;
        toSend[1] = requestState;
        Client.sendObject(toSend);
        Object response = Client.receiveObject();
        if (response instanceof Exception) {
            throw new Exception("there isn't request with this ID");
        }
    }

    public static void showDiscountCode() {
        ArrayList<DiscountCode> allDiscountCode = new ArrayList<>();
        allDiscountCode.addAll(DiscountCode.getAllDiscountCodes());
        Client.sendObject(allDiscountCode);
    }

    public static void editDiscountCodeInfo(DiscountCode discountCode, String field, String newContentForThisField) {
        if (field.equals("starting date")) {
            discountCode.setStartDate(LocalDate.parse(newContentForThisField));
        } else if (field.equals("ending date")) {
            discountCode.setEndDate(LocalDate.parse(newContentForThisField));
        } else if (field.equals("discount percent")) {
            discountCode.setDiscountCode(newContentForThisField);
        } else if (field.equals("maximum discount amount")) {
            discountCode.setMaxDiscountAmount(Double.parseDouble(newContentForThisField));
        } else if (field.equals("count discount code")) {
            discountCode.setCountDiscountCode(Integer.parseInt(newContentForThisField));
        }
    }

    public static void addDiscountCode(String code, String beginningDate, String endingDate, String discountPercent, String max, int repeat, String customers) {
        if (DiscountCode.getDiscountCodeWithCode(code) == null) {
            DiscountCode discountCode = new DiscountCode(code, LocalDate.parse(beginningDate), LocalDate.parse(endingDate),
                    Double.parseDouble(discountPercent), Double.parseDouble(max), repeat, Customer.getCustomerByName(customers));
            Customer.getCustomerByName(customers).addDiscountCode(discountCode);
        } else {
            Client.sendObject(new Exception("there is a discount code with this code"));
        }
    }

    public static String showDiscountCodeInfo(String code) {
        String string = "";
        if (DiscountCode.getDiscountCodeWithCode(code) != null) {
            string = (DiscountCode.getDiscountCodeWithCode(code).toString());
        } else {
            Client.sendObject(new Exception("there isn't any account with this username"));
        }
        return string;
    }

    public static String showLogInfo(String id) {
        String string = "";
        if (BuyLog.getBuyLogWithId(id) != null) {
            string = (BuyLog.getBuyLogWithId(id).getName());
        } else {
            Client.sendObject(new Exception("there isn't any account with this username"));
        }
        return string;
    }

    public static String showUserStatusInfo(String username) {
        String string;
        if (RegisterManagerMenu.getOnlineManagers().contains(Manager.getManagerByUserName(username)) ||
                RegisterCustomerMenu.getOnlineCustomers().contains(Customer.getCustomerByName(username)) ||
                RegisterSupporterMenu.getOnlineSupporter().contains(Supporter.getSupporterByUserName(username)) ||
                RegisterSellerMenu.getOnlineSellers().contains(Seller.getSellerByName(username))) {
            string = "online";
        } else {
            string = "offline";
        }
        return string;
    }

    public static void showAllAccounts() {
        ArrayList<Account> list = new ArrayList<>();
        for (Customer customer : Customer.getAllCustomers()) {
            list.add(customer);
        }
        for (Seller seller : Seller.getAllSellers()) {
            list.add(seller);
        }
        for (Manager manager : Manager.getAllManagers()) {
            list.add(manager);
        }
        Client.sendObject(list);
    }

    public static String showAccountInfo(String username) {
        Account account;
        String message = "";
        if (Customer.getCustomerByName(username) != null) {
            account = Customer.getCustomerByName(username);
            message = (account.toString());
        } else if (Manager.getManagerByUserName(username) != null) {
            account = Manager.getManagerByUserName(username);
            message = (account.toString());
        } else if (Seller.getSellerByName(username) != null) {
            account = Seller.getSellerByName(username);
            message = (account.toString());
        } else if (Supporter.getSupporterByUserName(username) != null) {
            account = Supporter.getSupporterByUserName(username);
            message = (account.toString());
        } else {
            Client.sendObject(new Exception("there isn't any account with this username"));
        }
        return message;
    }

    public static void deleteUser(String username) {
        if (Customer.getCustomerByName(username) != null) {
            Customer.deleteCustomer(username);
        } else if (Manager.getManagerByUserName(username) != null) {
            Manager.deleteManager(username);
        } else if (Seller.getSellerByName(username) != null) {
            Seller.deleteSeller(username);
        } else {
            Client.sendObject(new Exception("there isn't any account with this username"));
        }
    }

    public static void addManager(String username, String firstName, String lastName,
                                  String email, String phoneNumber, String password, String path) throws Exception {
        if (Manager.getManagerByUserName(username) == null && Customer.getCustomerByName(username) == null && Seller.getSellerByName(username) == null) {
            new Manager(username, firstName, lastName, email, phoneNumber, password, path);
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }

    public static void addSupporter(String username, String firstName, String lastName,
                                    String email, String phoneNumber, String password, String path) throws Exception {
        if (Manager.getManagerByUserName(username) == null && Customer.getCustomerByName(username) == null && Seller.getSellerByName(username) == null && Supporter.getSupporterByUserName(username) == null) {
            new Supporter(username, firstName, lastName, email, phoneNumber, password, 0, path);
        } else {
            Client.sendObject(new Exception("there is an account with this username"));
        }
    }

    public static void deleteProduct(String productId) {
        Product product = Product.getProductByName(productId);
        if (product != null) {
            Product.removeProduct(product);
        } else {
            Client.sendObject(new Exception("there isn't any product with this name"));
        }
    }

    public static void showCategories() {
        ArrayList<Category> allCategories = new ArrayList<>();
        allCategories.addAll(Category.getAllCategories());
        Client.sendObject(allCategories);
    }

    public static void deleteCategory(String categoryName) {
        Category category = Category.getCategoryByName(categoryName);
        if (category != null) {
            Category.deleteCategory(category);
        } else {
            Client.sendObject(new Exception("there isn't any category with this name"));
        }
    }

    public static void addCategory(String name, String feature) {
        if (Category.getCategoryByName(name) == null) {
            new Category(name, feature);
        } else {
            Client.sendObject(new Exception("there is a category with this name"));
        }
    }

    public static void deleteDiscountCode(String code) {
        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
        if (discountCode != null) {
            DiscountCode.removeDiscountCode(discountCode);
        } else {
            Client.sendObject(new Exception("there isn't any discount code with this code"));
        }
    }

    public static void editCategory(Category category, String field, String newContentForThisField) {
        if (field.equals("name")) {
            category.changeCategoryName(category, newContentForThisField);
        } else if (field.equals("feature")) {
            category.changeSpecialFeatures(category, newContentForThisField);
        }
    }

    public static void defineLeastAmountForSellerAndCustomerWallet(double amount) {
        Wallet.setLeastAmount(amount);
    }

    public static void showAllProducts() {
        ArrayList<Product> allProduct = new ArrayList<>();
        allProduct.addAll(Product.getAllProducts());
        Client.sendObject(allProduct);
    }

    public static void addAuction(String productID, String endDate) {
        try {
            Date endDateAsDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(endDate);
            Product product = Product.getProductWithId(productID);

            Auction auction = new Auction(product, endDateAsDate);
            auction.start();
        } catch (ParseException e) {
            e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}