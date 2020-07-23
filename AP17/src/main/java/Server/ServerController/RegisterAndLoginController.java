package Server.ServerController;

import Client.Client;
import Models.Accounts.*;
import Server.ClientHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class RegisterAndLoginController {
    public static void register() {

        String dataToRegister = ClientHandler.receiveMessage();

        Gson gson = new GsonBuilder().serializeNulls().create();
        Account account = gson.fromJson(dataToRegister, Account.class);
        String username = account.getUserName();
        String role = account.getRole();
        String accountPath = "Resources/Accounts/" + role + "/" + username + ".json";
        File file = new File(accountPath);
        if (!checkUsername(username)) {
            ClientHandler.sendObject(new Exception("username already exists"));
            return;
        } else {
            if (role.equals("Manager")) {
                if (new File("Resources/Accounts/Admin").listFiles().length != 0) {
                    ClientHandler.sendObject(new Exception("there is an manager already"));
                    return;
                } else {
                    String firstAdminPath = "Resources/Accounts/Admin" + account.getUserName() + ".json";
                    try {
                        File adminFile = new File(firstAdminPath);
                        adminFile.createNewFile();
                        FileWriter fileWriter = new FileWriter(adminFile);
                        fileWriter.write(dataToRegister);
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (role.equals("Seller")) {
                String sellerPath = "Resources/Accounts/Seller" + account.getUserName() + ".json";
                try {
                    File sellerFile = new File(sellerPath);
                    sellerFile.createNewFile();
                    FileWriter fileWriter = new FileWriter(sellerFile);
                    fileWriter.write(dataToRegister);
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ClientHandler.sendObject("Done");
        }
    }

    public static void registerAdmin() {
        String data = ClientHandler.receiveMessage();
        Gson gson = new GsonBuilder().serializeNulls().create();
        Manager admin = gson.fromJson(data, Manager.class);
        File file = new File("Resources/Accounts/Admin/" + admin.getUserName() + ".json");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerAdmin(String data) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Manager admin = gson.fromJson(data, Manager.class);
        File file = new File("Resources/Accounts/Admin/" + admin.getUserName() + ".json");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void login() throws Exception {
        HashMap<String, String> dataToLogin = (HashMap<String, String>) ClientHandler.receiveObject();
        Account account = GetDataFromDataBase.getAccount(dataToLogin.get("username"));
        if (account != null) {
            if (account.getPassword().equals(dataToLogin.get("password"))) {
                ClientHandler.sendObject(account);
                return;
            } else {
                ClientHandler.sendObject(new Exception("wrong password"));
                return;
            }
        } else {
            ClientHandler.sendObject(new Exception("wrong username"));
            return;
        }
    }


    public static void checkUsername() {
        String username = ClientHandler.receiveMessage();
        File folder1 = new File("Resources/Accounts/Customer/");
        File folder2 = new File("Resources/Accounts/Admin/");
        File folder3 = new File("Resources/Accounts/Seller/");
        if (new File(folder1, username + ".json").exists()) {
            ClientHandler.sendObject(false);
        } else if (new File(folder2, username + ".json").exists()) {
            ClientHandler.sendObject(true);
        } else ClientHandler.sendObject(!new File(folder3, username + ".json").exists());
    }

    public static boolean checkUsername(String username) {
        File folder1 = new File("Resources/Accounts/Customer/");
        File folder2 = new File("Resources/Accounts/Admin/");
        File folder3 = new File("Resources/Accounts/Seller/");
        if (new File(folder1, username + ".json").exists()) {
            return false;
        } else if (new File(folder2, username + ".json").exists()) {
            return true;
        } else return !new File(folder3, username + ".json").exists();
//        if (Manager.isThereManagerWithUserName(username)) {
//            return true;
//        }
//        return false;
    }


//    public static void registerSeller(String username, String firstName, String lastName,
//                                      String email, String phoneNumber, String password, String extraInfo, String path) {
//        if (Seller.getSellerByName(username) == null && Manager.getManagerByUserName(username) == null &&
//                Customer.getCustomerByName(username) == null && Supporter.getSupporterByUserName(username) == null) {
//            try {
//                new Seller(username, firstName, lastName, email,
//                        phoneNumber, password, 0, extraInfo, path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Client.sendObject(new Exception("there is an account with this username"));
//        }
//    }
//
//    public static void registerManger(String username, String firstName, String lastName,
//                                      String email, String phoneNumber, String password,String path) throws Exception {
//        if (Seller.getSellerByName(username) == null && Manager.getManagerByUserName(username) == null &&
//                Customer.getCustomerByName(username) == null && Supporter.getSupporterByUserName(username) == null) {
//            new Manager(username, firstName, lastName, email, phoneNumber, password,path);
//        } else {
//            Client.sendObject(new Exception("there is an account with this username"));
//        }
//    }
//
//    public static void registerCustomer(String username, String firstName, String lastName,
//                                        String email, String phoneNumber, String password, String extraInfo, String address, String path) {
//        if (Seller.getSellerByName(username) == null && Manager.getManagerByUserName(username) == null &&
//                Customer.getCustomerByName(username) == null && Supporter.getSupporterByUserName(username) == null) {
//            try {
//                new Customer(username, firstName, lastName, email,
//                        phoneNumber, password, Double.parseDouble(extraInfo), address, path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Client.sendObject(new Exception("there is an account with this username"));
//        }
//    }
//    //TODO handle login and multi accounts login

}
