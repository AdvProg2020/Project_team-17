package Client.ClientController.AccountsController;

import Client.Client;
import Models.*;
import Models.Accounts.*;
import Models.Logs.BuyLog;
import Models.Request.Request;
import Server.ServerController.DataBaseForServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class CManagerController {

    private static Manager manager;
    private static ArrayList<Manager> onlineManagers = new ArrayList<>();

    public static Manager getManager() {
        return manager;
    }

    public static void setManager(Manager manager) {
        CManagerController.manager = manager;
    }

    public static void addOnlineManager(Manager manager) {
        onlineManagers.add(manager);
    }

    public static void removeOnlineManger(Manager manager) {
        onlineManagers.remove(manager);
    }

    public static String showManagerInfo() throws Exception {
        if (getManager() == null) {
            throw new Exception("there isn't any manager logged in");
        }
        String func = "Show Manager Info";
        Client.sendMessage(func);

        String adminUsername = getManager().getUserName();
        Client.sendMessage(adminUsername);

        try {
            Object data = Client.receiveObject();
            return String.valueOf(data);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void editManagerInfo(String dataToEdit) throws Exception {
        String func = "Edit Manager Info";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = getManager().getUserName();
        toSend[1] = dataToEdit;
        Client.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Success")) {
                String[] split = dataToEdit.split("\\s");
                Manager manager = DataBaseForServer.getManager(getManager().getUserName());
                String field = split[0];
                String newContentForThisField = split[1];
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
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public static ObservableList<String> showManagerRequests() throws Exception {
        ArrayList<Request> allRequests;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Manager Requests";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allRequests = (ArrayList<Request>) response;
        for (Request request : allRequests) {
            info.add(request.getId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;
    }


    public static String showRequest(String requestId) throws Exception {
        String func = "Show Request";
        Client.sendMessage(func);

        Client.sendMessage(String.valueOf(requestId));
        Request request = (Request) Client.receiveObject();

        if (request != null) {
            String requestData = request.toString();
            return requestData;
        } else {
            throw new Exception("there isn't any request with this id");
        }
    }


    public static void acceptRequest(String requestId) throws Exception {
        String func = "Accept Request";
        Client.sendMessage(func);

        Client.sendMessage(requestId);
        try {
            Object data = Client.receiveObject();
            Request request = (Request) data;
            request.accept();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void declineRequest(String requestId) throws Exception {
        String func = "Decline Request";
        Client.sendMessage(func);

        Client.sendMessage(requestId);

        try {
            Object data = Client.receiveObject();
            Request request = (Request) data;
            Request.deleteRequest(request);
            DataBaseForServer.deleteRequest(request);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObservableList<String> showDiscountCodes() {
        ArrayList<DiscountCode> allDiscountCodes;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Manager Discount Codes";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allDiscountCodes = (ArrayList<DiscountCode>) response;
        for (DiscountCode discountCode : allDiscountCodes) {
            info.add(discountCode.getDiscountCode());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;
    }


    public static String showDiscountCode(String code) throws Exception {
        String func = "Show Manager Discount Code";
        Client.sendMessage(func);

        Client.sendMessage(String.valueOf(code));
        DiscountCode discountCode = (DiscountCode) Client.receiveObject();

        if (discountCode != null) {
            String discountCodeData = discountCode.toString();
            return discountCodeData;
        } else {
            throw new Exception("there isn't any request with this id");
        }
    }

    public static void editDiscountCode(String code, String dataToEdit) throws Exception {
        String func = "Edit Discount Code";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = code;
        toSend[1] = dataToEdit;
        Client.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            String[] split = dataToEdit.split("\\s");
            DiscountCode discountCode = (DiscountCode) response;
            String field = split[0];
            String newContentForThisField = split[1];
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
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void deleteDiscountCode(String code) throws Exception {
        String func = "Delete Discount Code";
        Client.sendMessage(func);

        Client.sendMessage(code);

        try {
            Object data = Client.receiveObject();
            DiscountCode discountCode = (DiscountCode) data;
            DiscountCode.removeDiscountCode(discountCode);
            DataBaseForServer.deleteDiscountCode(discountCode);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public static void createDiscountCode(String data) throws Exception {
        String func = "Create Discount Code";
        Client.sendMessage(func);

        Client.sendObject(data);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = data.split("\\s");
                new DiscountCode(split[0], LocalDate.parse(split[1]), LocalDate.parse(split[2]),
                        Double.parseDouble(split[3]), Double.parseDouble(split[4]), Integer.parseInt(split[5]), Customer.getCustomerByName(split[6]));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObservableList<String> showUsers() {
        ArrayList<Account> allUsers;
        String func = "Show All Users";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allUsers = (ArrayList<Account>) response;
        ArrayList<String> allAccounts = new ArrayList<>();
        for (Account user : allUsers) {
            allAccounts.add(user.getUserName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(allAccounts);
        return data;
    }

    public static String showUser(String username) throws Exception {
        String func = "Show User";
        Client.sendMessage(func);

        Client.sendMessage(username);
        Account account = (Account) Client.receiveObject();

        if (account != null) {
            String info = "";
            if (account instanceof Customer) {
                Customer customer = (Customer) account;
                info = customer.toString();
            } else if (account instanceof Manager) {
                Manager manager = (Manager) account;
                info = manager.toString();
            } else if (account instanceof Seller) {
                Seller seller = (Seller) account;
                info = seller.toString();
            } else if (account instanceof Supporter) {
                Supporter supporter = (Supporter) account;
                info = supporter.toString();
            }
            return info;
        } else {
            throw new Exception("there isn't any request with this id");
        }
    }

    public static void deleteUser(String username) throws Exception {
        String func = "Delete User";
        Client.sendMessage(func);

        Client.sendMessage(username);

        try {
            Object data = Client.receiveObject();
            Account account = (Account) data;
            if (account != null) {
                if (account instanceof Customer) {
                    Customer customer = (Customer) account;
                    Customer.deleteCustomer(customer);
                } else if (account instanceof Manager) {
                    Manager manager = (Manager) account;
                    Manager.deleteManager(manager);
                } else if (account instanceof Seller) {
                    Seller seller = (Seller) account;
                    Seller.deleteSeller(seller);
                } else if (account instanceof Supporter) {
                    Supporter supporter = (Supporter) account;
                    Supporter.deleteSupporter(supporter);
                }
            } else {
                throw new Exception("there isn't any request with this id");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void addManager(String dataToRegister) throws Exception {
        String func = "Add Manager";
        Client.sendMessage(func);

        Client.sendObject(dataToRegister);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = dataToRegister.split("\\s");
                new Manager(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void addSupporter(String dataToRegister) throws Exception {
        String func = "Add Supporter";
        Client.sendMessage(func);

        Client.sendObject(dataToRegister);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = dataToRegister.split("\\s");
                new Supporter(split[0], split[1], split[2], split[3], split[4], split[5], split[6]);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObservableList<String> showCategories() {
        ArrayList<Category> allCategories;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Categories";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allCategories = (ArrayList<Category>) response;
        for (Category category : allCategories) {
            info.add(category.getCategoryName());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;
    }

    public static void addCategory(String data) throws Exception {
        String func = "Add Category";
        Client.sendMessage(func);

        Client.sendObject(data);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                String[] split = data.split("\\s");
                new Category(split[0], split[1]);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void setLeastAmount(String num) throws Exception {
        String func = "Set Least Amount";
        Client.sendMessage(func);

        Client.sendObject(num);
        try {
            Object response = Client.receiveObject();
            String responseString = (String) response;
            if (responseString.equals("Done")) {
                Wallet.setLeastAmount(Double.parseDouble(num));
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void deleteCategory(String name) throws Exception {
        String func = "Delete Category";
        Client.sendMessage(func);

        Client.sendMessage(name);

        try {
            Object data = Client.receiveObject();
            Category category = (Category) data;
            Category.deleteCategory(category);
            DataBaseForServer.deleteCategory(category);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public static void editCategory(String name, String dataToEdit) throws Exception {
        String func = "Edit Category";
        Client.sendMessage(func);

        Object[] toSend = new Object[2];
        toSend[0] = name;
        toSend[1] = dataToEdit;
        Client.sendObject(toSend);
        try {
            Object response = Client.receiveObject();
            String[] split = dataToEdit.split("\\s");
            Category category = (Category) response;
            String field = split[0];
            String newContentForThisField = split[1];
            if (field.equals("name")) {
                category.changeCategoryName(category, newContentForThisField);
            } else if (field.equals("feature")) {
                category.changeSpecialFeatures(category, newContentForThisField);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static void deleteProduct(String id) throws Exception {
        String func = "Delete Product";
        Client.sendMessage(func);

        Client.sendMessage(id);

        try {
            Object data = Client.receiveObject();
            Product product = (Product) data;
            Product.removeProduct(product);
            DataBaseForServer.deleteProduct(product);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObservableList<String> showProducts() {
        ArrayList<Product> allProducts;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Products";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allProducts = (ArrayList<Product>) response;
        for (Product product : allProducts) {
            info.add(product.getProductId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(info);
        return data;
    }

    public static String showUserStatus(String username) throws Exception {
        String func = "Show User Status";
        Client.sendMessage(func);

        Client.sendMessage(username);

        try {
            Object data = Client.receiveObject();
            return String.valueOf(data);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObservableList<String> showLogs() throws Exception {
        ArrayList<BuyLog> allLogs;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show  Logs";
        Client.sendMessage(func);

        Object response = Client.receiveObject();
        allLogs = (ArrayList<BuyLog>) response;
        ArrayList<String> showAllLogs = new ArrayList<>();
        for (BuyLog buyLog : allLogs) {
            showAllLogs.add(buyLog.getId());
        }
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(showAllLogs);
        return data;

    }


    public static String showLog(String id) throws Exception {
        String func = "Show Log";
        Client.sendMessage(func);

        Client.sendMessage(String.valueOf(id));
        BuyLog buyLog = (BuyLog) Client.receiveObject();

        if (buyLog != null) {
            String requestData = buyLog.toString();
            return requestData;
        } else {
            throw new Exception("there isn't any log with this id");
        }
    }
}