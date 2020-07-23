package Server.ServerController.AccountsController;

import Client.Client;
import Client.ClientController.CRegisterAndLoginController;
import Models.*;
import Models.Accounts.*;
import Models.Logs.BuyLog;
import Models.Request.Request;
import Server.ClientHandler;
import Server.ServerController.DataBaseForServer;
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

    public static void showManagerInfo() throws Exception {
        if (getManager() == null) {
            ClientHandler.sendObject(new Exception("there isn't any manager logged in"));
        } else {
            String username = ClientHandler.receiveMessage();
            Manager manager = DataBaseForServer.getManager(username);
            setManager(manager);
            ClientHandler.sendObject(manager.toString());
        }
    }

    public static void editManagerInfo() throws Exception {
        String receivedItems = (String) ClientHandler.receiveObject();

        Manager manager = DataBaseForServer.getManager(receivedItems);
        if (manager == null) {
            ClientHandler.sendObject(new Exception("there isn't any manager with this username"));
        } else {
            ClientHandler.sendObject("Success!");
        }
    }

    public static void showRequests() {
        ArrayList<Request> allRequests = new ArrayList<>(DataBaseForServer.getAllRequests());
        ClientHandler.sendObject(allRequests);
    }


    public static void showRequest() throws Exception {
        String requestId = ClientHandler.receiveMessage();
        Request request = DataBaseForServer.getRequest(requestId);
        if (request != null) {
            ClientHandler.sendObject(request);
        } else {
            ClientHandler.sendObject(new Exception("there isn't request with this id"));
        }
    }

    public static void acceptRequest() throws Exception {
        String requestId = ClientHandler.receiveMessage();
        Request request = DataBaseForServer.getRequest(requestId);
        if (request != null) {
            ClientHandler.sendObject(request);
        } else {
            ClientHandler.sendObject(new Exception("there isn't request with this id"));
        }
    }

    public static void declineRequest() throws Exception {
        String requestId = ClientHandler.receiveMessage();
        Request request = DataBaseForServer.getRequest(requestId);
        if (request != null) {
            ClientHandler.sendObject(request);
            DataBaseForServer.deleteRequest(request);
        } else {
            ClientHandler.sendObject(new Exception("there isn't request with this id"));
        }
    }

    public static void showDiscountCodes() {
        ArrayList<DiscountCode> allDiscountCodes = new ArrayList<>(DataBaseForServer.getAllDiscountCodes());
        ClientHandler.sendObject(allDiscountCodes);
    }

    public static void showDiscountCode() throws Exception {
        String code = ClientHandler.receiveMessage();
        DiscountCode discountCode = DataBaseForServer.getDiscountCode(code);
        if (discountCode != null) {
            ClientHandler.sendObject(discountCode);
        } else {
            ClientHandler.sendObject(new Exception("there isn't discount code with this code"));
        }
    }

    public static void editDiscountCodeInfo() throws Exception {
        Object[] receivedItems = (Object[]) ClientHandler.receiveObject();

        DiscountCode discountCode = DataBaseForServer.getDiscountCode(receivedItems[0]);
        if (discountCode == null) {
            ClientHandler.sendObject(new Exception("there isn't any discount code with this code"));
        } else {
            ClientHandler.sendObject(discountCode);
        }
    }

    public static void deleteDiscountCode() throws Exception {
        String code = ClientHandler.receiveMessage();
        DiscountCode discountCode = DataBaseForServer.getDiscountCode(code);
        if (discountCode != null) {
            ClientHandler.sendObject(discountCode);
            DataBaseForServer.deleteDiscountCode(discountCode);
        } else {
            ClientHandler.sendObject(new Exception("there isn't request with this id"));
        }
    }

    public static void createDiscountCode() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getDiscountCode(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is a discount code with this code"));
        } else {
            if (DataBaseForServer.getDiscountCode(split[6]) == null) {
                ClientHandler.sendObject(new Exception("there isn't any customer with this username"));
            } else {
                ClientHandler.sendObject("Done");
                DataBaseForServer.addDiscountCode(new DiscountCode(split[0], LocalDate.parse(split[1]), LocalDate.parse(split[2]),
                        Double.parseDouble(split[3]), Double.parseDouble(split[4]), Integer.parseInt(split[5]), Customer.getCustomerByName(split[6])));
            }
        }
    }

    public static void showAllUsers() {
        ArrayList<Account> allAccounts = new ArrayList<>();
        allAccounts.addAll(DataBaseForServer.getAllManagers());
        allAccounts.addAll(DataBaseForServer.getAllCustomers());
        allAccounts.addAll(DataBaseForServer.getAllSellers());
        allAccounts.addAll(DataBaseForServer.getAllSupporters());
        ClientHandler.sendObject(allAccounts);
    }

    public static void showUser() {
        String username = ClientHandler.receiveMessage();
        if (DataBaseForServer.getCustomer(username) != null) {
            Customer customer = DataBaseForServer.getCustomer(username);
            ClientHandler.sendObject(customer);
        } else if (DataBaseForServer.getManager(username) != null) {
            Manager manager = DataBaseForServer.getManager(username);
            ClientHandler.sendObject(manager);
        } else if (DataBaseForServer.getSeller(username) != null) {
            Seller seller = DataBaseForServer.getSeller(username);
            ClientHandler.sendObject(seller);
        } else if (DataBaseForServer.getSupporter(username) != null) {
            Supporter supporter = DataBaseForServer.getSupporter(username);
            ClientHandler.sendObject(supporter);
        } else {
            ClientHandler.sendObject(new Exception("there isn't any account with this username"));
        }
    }

    public static void deleteUser() {
        String username = ClientHandler.receiveMessage();
        if (DataBaseForServer.getCustomer(username) != null) {
            Customer customer = DataBaseForServer.getCustomer(username);
            DataBaseForServer.deleteCustomer(customer);
            ClientHandler.sendObject(customer);
        } else if (DataBaseForServer.getManager(username) != null) {
            Manager manager = DataBaseForServer.getManager(username);
            DataBaseForServer.deleteManager(manager);
            ClientHandler.sendObject(manager);
        } else if (DataBaseForServer.getSeller(username) != null) {
            Seller seller = DataBaseForServer.getSeller(username);
            DataBaseForServer.deleteSeller(seller);
            ClientHandler.sendObject(seller);
        } else if (DataBaseForServer.getSupporter(username) != null) {
            Supporter supporter = DataBaseForServer.getSupporter(username);
            DataBaseForServer.deleteSupporter(supporter);
            ClientHandler.sendObject(supporter);
        } else {
            ClientHandler.sendObject(new Exception("there isn't any account with this username"));
        }

    }

    public static void addManager() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getManager(split[0]) != null && DataBaseForServer.getCustomer(split[0]) != null && DataBaseForServer.getSeller(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is an account with this username"));
        } else {
            ClientHandler.sendObject("Done");
            DataBaseForServer.addManager(new Manager(split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
        }
    }

    public static void addSupporter() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getManager(split[0]) != null && DataBaseForServer.getCustomer(split[0]) != null && DataBaseForServer.getSeller(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is an account with this username"));
        } else {
            ClientHandler.sendObject("Done");
            DataBaseForServer.addSupporter(new Supporter(split[0], split[1], split[2], split[3], split[4], split[5], split[6]));
        }
    }

    public static void showCategories() {
        ArrayList<Category> allCategories = new ArrayList<>(DataBaseForServer.getAllCategories());
        ClientHandler.sendObject(allCategories);
    }

    public static void editCategory() throws Exception {
        String receivedItem = (String) ClientHandler.receiveObject();

        Category category = DataBaseForServer.getCategory(receivedItem);
        if (category == null) {
            ClientHandler.sendObject(new Exception("there isn't any category with this name"));
        } else {
            ClientHandler.sendObject(category);
        }
    }

    public static void deleteDiscountCode() throws Exception {
        String name = ClientHandler.receiveMessage();
        Category category = DataBaseForServer.getCategory(name);
        if (category != null) {
            ClientHandler.sendObject(category);
            DataBaseForServer.deleteCategory(category);
        } else {
            ClientHandler.sendObject(new Exception("there isn't category with this name"));
        }
    }

    public static void createDiscountCode() throws IOException {
        String dataToRegister = ClientHandler.receiveMessage();
        String[] split = dataToRegister.split("\\s");

        if (DataBaseForServer.getCategory(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is a category with this name"));
        } else {
            if (DataBaseForServer.getCategory(split[6]) == null) {
                ClientHandler.sendObject(new Exception("there isn't any customer with this username"));
            } else {
                ClientHandler.sendObject("Done");
                DataBaseForServer.addCategory(new Category(split[0], split[1]);
            }
        }
    }


//    public static void deleteProduct() throws ExceptionsLibrary.NoProductException, ExceptionsLibrary.NoAccountException {
//        int productId = Integer.parseInt(ClientHandler.receiveMessage());
//        Product product = GetDataFromDatabaseServerSide.getProduct(productId);
//        String path = "Resources/Products/" + product.getProductId() + ".json";
//        SetDataToDatabase.updateSellerOfProduct(product, 1);
//        File file = new File(path);
//        file.delete();
//    }
//

//    public static void getAllProducts() throws ExceptionsLibrary.NoProductException {
//        ArrayList<Product> allProducts = new ArrayList<>();
//        String path = "Resources/Products";
//        File folder = new File(path);
//        FileFilter fileFilter = new FileFilter() {
//            @Override
//            public boolean accept(File file1) {
//                if (file1.getName().endsWith(".json")) {
//                    return true;
//                }
//                return false;
//            }
//        };
//        for (File i : folder.listFiles(fileFilter)) {
//            String fileName = i.getName();
//            int productId = Integer.parseInt(fileName.replace(".json", ""));
//            allProducts.add(GetDataFromDatabaseServerSide.getProduct(productId));
//        }
//        ClientHandler.sendObject(allProducts);
//    }
//
//
////    public static void addAuction(String productID, String endDate) {
////        try {
////            Date endDateAsDate = new SimpleDateFormat("yyyy-MM-dd_HH:mm").parse(endDate);
////            Product product = Product.getProductWithId(productID);
////
////            Auction auction = new Auction(product, endDateAsDate);
////            auction.start();
////        } catch (ParseException e) {
////            e.getMessage();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
}