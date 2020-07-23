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
            ClientHandler.sendObject(new Exception("there isn't request with this id"));
        }
    }

    public static void editDiscountCodeInfo() throws Exception {
        Object[] receivedItems = (Object[]) ClientHandler.receiveObject();

        DiscountCode discountCode = DataBaseForServer.getDiscountCode(receivedItems[0]);
        if (discountCode == null) {
            ClientHandler.sendObject(new Exception("there isn't any manager with this username"));
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

        if (DataBaseForServer.getManager(split[0]) != null) {
            ClientHandler.sendObject(new Exception("there is an discount code with this code"));
        } else {
            if (DataBaseForServer.getCustomer(split[6]) == null) {
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


//
//    public static void addAdminAccount() throws ExceptionsLibrary.UsernameAlreadyExists {
//        String newAdminDetails = ClientHandler.receiveMessage();
//        System.out.println(newAdminDetails);
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        Admin admin1 = gson.fromJson(newAdminDetails, Admin.class);
//        if (RegisterAndLogin.checkUsername(admin1.getUsername())) {
//            RegisterAndLogin.registerAdmin(newAdminDetails);
//        } else {
//            ClientHandler.sendObject(new ExceptionsLibrary.UsernameAlreadyExists());
//            return;
//        }
//        ClientHandler.sendMessage("Success!");
//    }
//
//    public static void deleteProduct() throws ExceptionsLibrary.NoProductException, ExceptionsLibrary.NoAccountException {
//        int productId = Integer.parseInt(ClientHandler.receiveMessage());
//        Product product = GetDataFromDatabaseServerSide.getProduct(productId);
//        String path = "Resources/Products/" + product.getProductId() + ".json";
//        SetDataToDatabase.updateSellerOfProduct(product, 1);
//        File file = new File(path);
//        file.delete();
//    }
//
//    public static void showCategories() throws ExceptionsLibrary.NoCategoryException {
//        ArrayList<Category> allCategories = new ArrayList<>();
//        String path = "Resources/Category";
//        File file = new File(path);
//        FileFilter fileFilter = new FileFilter() {
//            @Override
//            public boolean accept(File file1) {
//                if (file1.getName().endsWith(".json")) {
//                    return true;
//                }
//                return false;
//            }
//        };
//        for (File i : file.listFiles(fileFilter)) {
//            String fileName = i.getName();
//            String categoryName = fileName.replace(".json", "");
//            Category category = GetDataFromDatabaseServerSide.getCategory(categoryName);
//            allCategories.add(category);
//        }
//        ClientHandler.sendObject(allCategories);
//    }
//
//    public static void deleteCategory() throws ExceptionsLibrary.NoCategoryException, ExceptionsLibrary.NoProductException {
//        String categoryName = ClientHandler.receiveMessage();
//        try {
//            Category category = GetDataFromDatabaseServerSide.getCategory(categoryName);
//            FileFilter fileFilter = new FileFilter() {
//                @Override
//                public boolean accept(File file1) {
//                    if (file1.getName().endsWith(".json")) {
//                        return true;
//                    }
//                    return false;
//                }
//            };
//            File productsFolder = new File("Resources/Products");
//            for (File i : productsFolder.listFiles(fileFilter)) {
//                Gson gson = new GsonBuilder().serializeNulls().create();
//                try {
//                    String fileData = "";
//                    fileData = new String(Files.readAllBytes(Paths.get(i.getPath())));
//                    Product product = gson.fromJson(fileData, Product.class);
//                    if (product.getCategory().getName().equals(categoryName)) {
//                        SetDataToDatabase.updateSellerOfProduct(product, 1);
//                        i.delete();
//                    }
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (ExceptionsLibrary.NoAccountException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            String path = "Resources/Category/" + category.getName() + ".json";
//            File file = new File(path);
//            file.delete();
//        } catch (ExceptionsLibrary.NoCategoryException e) {
//            ClientHandler.sendObject(new ExceptionsLibrary.NoCategoryException());
//        }
//
//    }
//
//    public static void editCategory() throws ExceptionsLibrary.CategoryExistsWithThisName, ExceptionsLibrary.NoCategoryException, ExceptionsLibrary.NoFeatureWithThisName, ExceptionsLibrary.NoAccountException, ExceptionsLibrary.NoProductException {
//        Object[] receivedData = (Object[]) ClientHandler.receiveObject();
//        String categoryName = (String) receivedData[0];
//        HashMap<String, String> dataToEdit = (HashMap<String, String>) receivedData[0];
//        Category category = GetDataFromDatabaseServerSide.getCategory(categoryName);
//        String oldName = category.getName();
//        for (String i : dataToEdit.keySet()) {
//            try {
//                Field field = Category.class.getDeclaredField(i);
//                if (i.equals("features")) {
//                    field.setAccessible(true);
//                    String[] splitFeatures = dataToEdit.get(i).split("\\s*,\\s*");
//                    ArrayList<Feature> newFeatures = new ArrayList<>();
//                    for (String j : splitFeatures) {
//                        newFeatures.add(new Feature(j, null));
//                    }
//                    field.set(category, newFeatures);
//                } else {
//                    field.setAccessible(true);
//                    field.set(category, dataToEdit.get(i));
//                }
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                ClientHandler.sendObject(new ExceptionsLibrary.NoFeatureWithThisName());
//            }
//        }
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        String newName = category.getName();
//        String editedDetails = gson.toJson(category);
//        if (newName.equals(oldName)) {
//            try {
//                String path = "Resources/Category/" + category.getName() + ".json";
//                FileWriter fileWriter = new FileWriter(path);
//                fileWriter.write(editedDetails);
//                fileWriter.close();
//                File file = new File(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                String newPath = "Resources/Category/" + newName + ".json";
//                String oldPath = "Resources/Category/" + oldName + ".json";
//                File file = new File(newPath);
//                if (file.exists()) {
//                    ClientHandler.sendObject(new ExceptionsLibrary.CategoryExistsWithThisName());
//                }
//                file.createNewFile();
//                FileWriter fileWriter = new FileWriter(newPath);
//                fileWriter.write(editedDetails);
//                fileWriter.close();
//                File file1 = new File(oldPath);
//                file1.delete();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        File folder = new File("Resources/Products");
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
//            Gson gson1 = new GsonBuilder().serializeNulls().create();
//            try {
//                String fileData = "";
//                fileData = new String(Files.readAllBytes(Paths.get(i.getPath())));
//                Product product = gson1.fromJson(fileData, Product.class);
//                product.setCategory(category);
//                SetDataToDatabase.setProduct(product);
//                SetDataToDatabase.updateSellerOfProduct(product, 0);
//            } catch (ExceptionsLibrary.NoAccountException | IOException e) {
//                ClientHandler.sendObject(e);
//            }
//        }
//    }
//
//    public static void addCategory() throws ExceptionsLibrary.CategoryExistsWithThisName {
//        String categoryDetails = ClientHandler.receiveMessage();
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        Category category = gson.fromJson(categoryDetails, Category.class);
//        if (!checkCategoryName(category.getName())) {
//            String newSaleDetails = gson.toJson(category);
//            try {
//                String path = "Resources/Category/" + category.getName() + ".json";
//                File file = new File(path);
//                file.createNewFile();
//                FileWriter fileWriter = new FileWriter(path);
//                fileWriter.write(newSaleDetails);
//                fileWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            ClientHandler.sendObject(new ExceptionsLibrary.CategoryExistsWithThisName());
//        }
//    }
//
//    public static void viewSaleCodeDetails() throws ExceptionsLibrary.NoSaleException {
//        String saleCode = ClientHandler.receiveMessage();
//        Sale sale = GetDataFromDatabaseServerSide.getSale(saleCode);
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        ClientHandler.sendMessage(gson.toJson(sale));
//    }
//
//    public static void removeSaleCode() throws ExceptionsLibrary.NoSaleException {
//        String saleCode = ClientHandler.receiveMessage();
//        try {
//            Sale sale = GetDataFromDatabaseServerSide.getSale(saleCode);
//            FileFilter fileFilter = new FileFilter() {
//                @Override
//                public boolean accept(File file1) {
//                    if (file1.getName().endsWith(".json")) {
//                        return true;
//                    }
//                    return false;
//                }
//            };
//            File customerFolder = new File("Resources/Accounts/Customer");
//            File sellerFolder = new File("Resources/Accounts/Seller");
//            File adminFolder = new File("Resources/Accounts/Admin");
//
//            for (File i : customerFolder.listFiles(fileFilter)) {
//                Gson gson = new GsonBuilder().serializeNulls().create();
//                try {
//                    String fileData = "";
//                    fileData = new String(Files.readAllBytes(Paths.get(i.getPath())));
//                    Customer customer = gson.fromJson(fileData, Customer.class);
//                    Iterator<Sale> iterator = customer.getSaleCodes().iterator();
//                    while (iterator.hasNext()) {
//                        Sale tempSale = iterator.next();
//                        if (tempSale.getSaleCode().equalsIgnoreCase(sale.getSaleCode())) {
//                            iterator.remove();
//                        }
//                    }
//                    SetDataToDatabase.setAccount(customer);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            for (File i : sellerFolder.listFiles(fileFilter)) {
//                Gson gson = new GsonBuilder().serializeNulls().create();
//                try {
//                    String fileData = "";
//                    fileData = new String(Files.readAllBytes(Paths.get(i.getPath())));
//                    Seller seller = gson.fromJson(fileData, Seller.class);
//                    Iterator<Sale> iterator = seller.getSaleCodes().iterator();
//                    while (iterator.hasNext()) {
//                        Sale tempSale = iterator.next();
//                        if (tempSale.getSaleCode().equalsIgnoreCase(sale.getSaleCode())) {
//                            iterator.remove();
//                        }
//                    }
//                    SetDataToDatabase.setAccount(seller);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            for (File i : adminFolder.listFiles(fileFilter)) {
//                Gson gson = new GsonBuilder().serializeNulls().create();
//                try {
//                    String fileData = "";
//                    fileData = new String(Files.readAllBytes(Paths.get(i.getPath())));
//                    Admin admin = gson.fromJson(fileData, Admin.class);
//                    Iterator<Sale> iterator = admin.getSaleCodes().iterator();
//                    while (iterator.hasNext()) {
//                        Sale tempSale = iterator.next();
//                        if (tempSale.getSaleCode().equalsIgnoreCase(sale.getSaleCode())) {
//                            iterator.remove();
//                        }
//                    }
//                    SetDataToDatabase.setAccount(admin);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            String path = "Resources/Sales/" + sale.getSaleCode() + ".json";
//            File file = new File(path);
//            file.delete();
//        } catch (ExceptionsLibrary.NoSaleException e) {
//            ClientHandler.sendObject(new ExceptionsLibrary.NoSaleException());
//        }
//
//    }
//
//    public static void checkCategoryName() {
//        String categoryName = ClientHandler.receiveMessage();
//        String path = "Resources/Category";
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
//            String fileCategoryName = fileName.replace(".json", "");
//            if (categoryName.equals(fileCategoryName)) {
//                ClientHandler.sendMessage("true");
//            }
//        }
//        ClientHandler.sendMessage("false");
//    }
//
//    public static boolean checkCategoryName(String categoryName) {
//        String path = "Resources/Category";
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
//            String fileCategoryName = fileName.replace(".json", "");
//            if (categoryName.equals(fileCategoryName)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static boolean checkSaleCode(String saleCode) {
//        String path = "Resources/Sales";
//        File folder = new File(path);
//        FileFilter fileFilter = new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                if (file.getName().endsWith(".json")) {
//                    return true;
//                }
//                return false;
//            }
//        };
//        for (File i : folder.listFiles(fileFilter)) {
//            String fileName = i.getName();
//            String fileSaleCode = fileName.replace(".json", "");
//            if (saleCode.equals(fileSaleCode)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static void checkSaleCode() {
//        String saleCode = ClientHandler.receiveMessage();
//        String path = "Resources/Sales";
//        File folder = new File(path);
//        FileFilter fileFilter = new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                if (file.getName().endsWith(".json")) {
//                    return true;
//                }
//                return false;
//            }
//        };
//        for (File i : folder.listFiles(fileFilter)) {
//            String fileName = i.getName();
//            String fileSaleCode = fileName.replace(".json", "");
//            if (saleCode.equals(fileSaleCode)) {
//                ClientHandler.sendObject(true);
//
//            }
//        }
//        ClientHandler.sendObject(false);
//
//    }
//
//
//    public static boolean checkIfProductExist(int productId) {
//        String path = "Resources/Products/" + productId + ".json";
//        File file = new File(path);
//        if (!file.exists()) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    public static void checkIfProductExist() {
//        int productId = Integer.parseInt(ClientHandler.receiveMessage());
//        String path = "Resources/Products/" + productId + ".json";
//        File file = new File(path);
//        if (!file.exists()) {
//            ClientHandler.sendObject(false);
//        } else {
//            ClientHandler.sendObject(true);
//        }
//    }
//
//    public static void checkIfRequestExist() {
//        int requestId = Integer.parseInt(ClientHandler.receiveMessage());
//        String path = "Resources/Requests/" + requestId + ".json";
//        File file = new File(path);
//        if (!file.exists()) {
//            ClientHandler.sendObject(false);
//        } else {
//            ClientHandler.sendObject(true);
//        }
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
//    //    //public static String showManagerInfo() {
////        return (RegisterManagerMenu.getCurrentManager().toString());
////    }
////    public static void showAdminInfo() {
////        Gson gson = new GsonBuilder().serializeNulls().create();
////        if (getManager() == null) {
////            ClientHandler.sendObject(new ExceptionsLibrary.NoAccountException());
////        }
////        String username = ClientHandler.receiveMessage();
////        Manager manager = (Manager) GetDataFromDatabaseServerSide.getAccount(username);
////        setManager(manager);
////        String data = gson.toJson(manager);
////        ClientHandler.sendMessage(data);
////    }
////
////
////    public static void editManagerInfo(Manager manager, String field, String newContentForThisField) throws Exception {
//////        Manager manager = RegisterManagerMenu.getCurrentManager();
////        if (field.equalsIgnoreCase("first name")) {
////            manager.changeFirstName(manager, newContentForThisField);
////        } else if (field.equalsIgnoreCase("last name")) {
////            manager.changeLastName(manager, newContentForThisField);
////        } else if (field.equalsIgnoreCase("email")) {
////            manager.changeEmail(manager, newContentForThisField);
////        } else if (field.equalsIgnoreCase("phone number")) {
////            manager.changePhoneNumber(manager, newContentForThisField);
////        } else if (field.equalsIgnoreCase("password")) {
////            manager.changePassword(manager, newContentForThisField);
////        }
////    }
////
////    public static void createManager(String username, String firstName, String lastName, String email, String phoneNumber, String password, String path) throws IOException {
////        new Manager(username, firstName, lastName, email, phoneNumber, password, path);
////    }
////
////    public static void showManagerRequests() {
////        ArrayList<Request> allRequests = new ArrayList<>();
////        allRequests.addAll(Request.showRequestsNeedToBeAccepted());
////        Client.sendObject(allRequests);
////    }
////
////    public static String showRequest(String id) {
////        Request request = Request.getRequestById(id);
////        String string = "";
////        if (request != null) {
////            string = request.toString();
////        } else {
////            Client.sendObject(new Exception("there isn't any request with this id"));
////        }
////        return string;
////    }
////
////    public static void processRequest(String requestId, String requestState) throws Exception {
////        Object[] toSend = new Object[2];
////        toSend[0] = requestId;
////        toSend[1] = requestState;
////        Client.sendObject(toSend);
////        Object response = Client.receiveObject();
////        if (response instanceof Exception) {
////            throw new Exception("there isn't request with this ID");
////        }
////    }
////
////    public static void showDiscountCode() {
////        ArrayList<DiscountCode> allDiscountCode = new ArrayList<>();
////        allDiscountCode.addAll(DiscountCode.getAllDiscountCodes());
////        Client.sendObject(allDiscountCode);
////    }
////
////    public static void editDiscountCodeInfo(DiscountCode discountCode, String field, String newContentForThisField) {
////        if (field.equals("starting date")) {
////            discountCode.setStartDate(LocalDate.parse(newContentForThisField));
////        } else if (field.equals("ending date")) {
////            discountCode.setEndDate(LocalDate.parse(newContentForThisField));
////        } else if (field.equals("discount percent")) {
////            discountCode.setDiscountCode(newContentForThisField);
////        } else if (field.equals("maximum discount amount")) {
////            discountCode.setMaxDiscountAmount(Double.parseDouble(newContentForThisField));
////        } else if (field.equals("count discount code")) {
////            discountCode.setCountDiscountCode(Integer.parseInt(newContentForThisField));
////        }
////    }
////
////    public static void addDiscountCode(String code, String beginningDate, String endingDate, String discountPercent, String max, int repeat, String customers) {
////        if (DiscountCode.getDiscountCodeWithCode(code) == null) {
////            DiscountCode discountCode = new DiscountCode(code, LocalDate.parse(beginningDate), LocalDate.parse(endingDate),
////                    Double.parseDouble(discountPercent), Double.parseDouble(max), repeat, Customer.getCustomerByName(customers));
////            Customer.getCustomerByName(customers).addDiscountCode(discountCode);
////        } else {
////            Client.sendObject(new Exception("there is a discount code with this code"));
////        }
////    }
////
////    public static String showDiscountCodeInfo(String code) {
////        String string = "";
////        if (DiscountCode.getDiscountCodeWithCode(code) != null) {
////            string = (DiscountCode.getDiscountCodeWithCode(code).toString());
////        } else {
////            Client.sendObject(new Exception("there isn't any account with this username"));
////        }
////        return string;
////    }
////
////    public static String showLogInfo(String id) {
////        String string = "";
////        if (BuyLog.getBuyLogWithId(id) != null) {
////            string = (BuyLog.getBuyLogWithId(id).getName());
////        } else {
////            Client.sendObject(new Exception("there isn't any account with this username"));
////        }
////        return string;
////    }
////
////    public static String showUserStatusInfo(String username) {
////        String string;
////        if (RegisterManagerMenu.getOnlineManagers().contains(Manager.getManagerByUserName(username)) ||
////                RegisterCustomerMenu.getOnlineCustomers().contains(Customer.getCustomerByName(username)) ||
////                RegisterSupporterMenu.getOnlineSupporter().contains(Supporter.getSupporterByUserName(username)) ||
////                RegisterSellerMenu.getOnlineSellers().contains(Seller.getSellerByName(username))) {
////            string = "online";
////        } else {
////            string = "offline";
////        }
////        return string;
////    }
////
////    public static void showAllAccounts() {
////        ArrayList<Account> list = new ArrayList<>();
////        for (Customer customer : Customer.getAllCustomers()) {
////            list.add(customer);
////        }
////        for (Seller seller : Seller.getAllSellers()) {
////            list.add(seller);
////        }
////        for (Manager manager : Manager.getAllManagers()) {
////            list.add(manager);
////        }
////        Client.sendObject(list);
////    }
////
////    public static String showAccountInfo(String username) {
////        Account account;
////        String message = "";
////        if (Customer.getCustomerByName(username) != null) {
////            account = Customer.getCustomerByName(username);
////            message = (account.toString());
////        } else if (Manager.getManagerByUserName(username) != null) {
////            account = Manager.getManagerByUserName(username);
////            message = (account.toString());
////        } else if (Seller.getSellerByName(username) != null) {
////            account = Seller.getSellerByName(username);
////            message = (account.toString());
////        } else if (Supporter.getSupporterByUserName(username) != null) {
////            account = Supporter.getSupporterByUserName(username);
////            message = (account.toString());
////        } else {
////            Client.sendObject(new Exception("there isn't any account with this username"));
////        }
////        return message;
////    }
////
////    public static void deleteUser(String username) {
////        if (Customer.getCustomerByName(username) != null) {
////            Customer.deleteCustomer(username);
////        } else if (Manager.getManagerByUserName(username) != null) {
////            Manager.deleteManager(username);
////        } else if (Seller.getSellerByName(username) != null) {
////            Seller.deleteSeller(username);
////        } else {
////            Client.sendObject(new Exception("there isn't any account with this username"));
////        }
////    }
////
////    public static void addManager(String username, String firstName, String lastName,
////                                  String email, String phoneNumber, String password, String path) throws Exception {
////        if (Manager.getManagerByUserName(username) == null && Customer.getCustomerByName(username) == null && Seller.getSellerByName(username) == null) {
////            new Manager(username, firstName, lastName, email, phoneNumber, password, path);
////        } else {
////            Client.sendObject(new Exception("there is an account with this username"));
////        }
////    }
////
////    public static void addSupporter(String username, String firstName, String lastName,
////                                    String email, String phoneNumber, String password, String path) throws Exception {
////        if (Manager.getManagerByUserName(username) == null && Customer.getCustomerByName(username) == null && Seller.getSellerByName(username) == null && Supporter.getSupporterByUserName(username) == null) {
////            new Supporter(username, firstName, lastName, email, phoneNumber, password, 0, path);
////        } else {
////            Client.sendObject(new Exception("there is an account with this username"));
////        }
////    }
////
////    public static void deleteProduct(String productId) {
////        Product product = Product.getProductByName(productId);
////        if (product != null) {
////            Product.removeProduct(product);
////        } else {
////            Client.sendObject(new Exception("there isn't any product with this name"));
////        }
////    }
////
////    public static void showCategories() {
////        ArrayList<Category> allCategories = new ArrayList<>();
////        allCategories.addAll(Category.getAllCategories());
////        Client.sendObject(allCategories);
////    }
////
////    public static void deleteCategory(String categoryName) {
////        Category category = Category.getCategoryByName(categoryName);
////        if (category != null) {
////            Category.deleteCategory(category);
////        } else {
////            Client.sendObject(new Exception("there isn't any category with this name"));
////        }
////    }
////
////    public static void addCategory(String name, String feature) {
////        if (Category.getCategoryByName(name) == null) {
////            new Category(name, feature);
////        } else {
////            Client.sendObject(new Exception("there is a category with this name"));
////        }
////    }
////
////    public static void deleteDiscountCode(String code) {
////        DiscountCode discountCode = DiscountCode.getDiscountCodeWithCode(code);
////        if (discountCode != null) {
////            DiscountCode.removeDiscountCode(discountCode);
////        } else {
////            Client.sendObject(new Exception("there isn't any discount code with this code"));
////        }
////    }
////
////    public static void editCategory(Category category, String field, String newContentForThisField) {
////        if (field.equals("name")) {
////            category.changeCategoryName(category, newContentForThisField);
////        } else if (field.equals("feature")) {
////            category.changeSpecialFeatures(category, newContentForThisField);
////        }
////    }
////
////    public static void defineLeastAmountForSellerAndCustomerWallet(double amount) {
////        Wallet.setLeastAmount(amount);
////    }
////
////    public static void showAllProducts() {
////        ArrayList<Product> allProduct = new ArrayList<>();
////        allProduct.addAll(Product.getAllProducts());
////        Client.sendObject(allProduct);
////    }
////
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