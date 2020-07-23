package Client.ClientController.AccountsController;

import Client.Client;
import Models.Accounts.*;
import Models.Category;
import Models.Discount;
import Models.DiscountCode;
import Models.Product;
import Models.Request.Request;
import Server.ServerController.DataBaseForServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

public class CManagerController {

    private static Manager manager;

    public CManagerController(Manager manager) {
        this.manager = manager;
    }

    public static Manager getManager() {
        return manager;
    }

    public static void setManager(Manager manager) {
        CManagerController.manager = manager;
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
        String func = "Edit Admin Info";
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
            //TODO check with kian's commit
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public static ObservableList<String> showDiscountCodes() {
        ArrayList<DiscountCode> allDiscountCodes;
        ArrayList<String> info = new ArrayList<>();
        String func = "Show Discount Codes";
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
        String func = "Show Discount Code";
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
            DataBaseForServer.deleletDiscountCode(discountCode);
            //TODO check with kian's commit
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
//
//    public static void deleteProduct(int productId) throws ExceptionsLibrary.NoProductException, ExceptionsLibrary.NoAccountException {
//
//        String func = "Delete Product";
//        Client.sendMessage(func);
//
//        Client.sendMessage(String.valueOf(productId));
//        Object response = Client.receiveObject();
//
//        if (response instanceof ExceptionsLibrary.NoAccountException)
//            throw new ExceptionsLibrary.NoAccountException();
//        else if (response instanceof ExceptionsLibrary.NoProductException)
//            throw new ExceptionsLibrary.NoProductException();
//        else
//            return;
////        Product product = GetDataFromDatabase.getProduct(productId);
////        String path = "Resources/Products/" + product.getProductId() + ".json";
////        SetDataToDatabase.updateSellerOfProduct(product,1);
////        File file = new File(path);
////        file.delete();
//    }
//
//    public static ArrayList<Category> showCategories() throws ExceptionsLibrary.NoCategoryException {
//        ArrayList<Category> allCategories = new ArrayList<>();
//
//        String func = "Show Categories";
//        Client.sendMessage(func);
//
//        Object response = Client.receiveObject();
//
//        if (response instanceof ExceptionsLibrary.NoCategoryException)
//            throw new ExceptionsLibrary.NoCategoryException();
//        else
//            allCategories = (ArrayList<Category>) response;
//
////        String path = "Resources/Category";
////        File file = new File(path);
////        FileFilter fileFilter = new FileFilter() {
////            @Override
////            public boolean accept(File file1) {
////                if (file1.getName().endsWith(".json")) {
////                    return true;
////                }
////                return false;
////            }
////        };
////        for (File i : file.listFiles(fileFilter)) {
////            String fileName = i.getName();
////            String categoryName = fileName.replace(".json", "");
////            Category category = GetDataFromDatabase.getCategory(categoryName);
////            allCategories.add(category);
////        }
//        return allCategories;
//    }
//
//    public static void deleteCategory(String categoryName) throws ExceptionsLibrary.NoCategoryException, ExceptionsLibrary.NoProductException {
//
//        String func = "Delete Category";
//        Client.sendMessage(func);
//
//        Client.sendMessage(categoryName);
//        Object response = Client.receiveObject();
//
//        if (response instanceof ExceptionsLibrary.NoCategoryException)
//            throw new ExceptionsLibrary.NoCategoryException();
//        else if (response instanceof ExceptionsLibrary.NoProductException)
//            throw new ExceptionsLibrary.NoProductException();
//        else
//            return;
////                try {
////            Category category = GetDataFromDatabase.getCategory(categoryName);
////            FileFilter fileFilter = new FileFilter() {
////                @Override
////                public boolean accept(File file1) {
////                    if (file1.getName().endsWith(".json")) {
////                        return true;
////                    }
////                    return false;
////                }
////            };
////            File productsFolder = new File("Resources/Products");
////            for (File i : productsFolder.listFiles(fileFilter)) {
////                Gson gson = new GsonBuilder().serializeNulls().create();
////                try {
////                    String fileData = "";
////                    fileData = new String(Files.readAllBytes(Paths.get(i.getPath())));
////                    Product product = gson.fromJson(fileData, Product.class);
////                    if (product.getCategory().getName().equals(categoryName)) {
////                        SetDataToDatabase.updateSellerOfProduct(product,1);
////                        i.delete();
////                    }
////                } catch (FileNotFoundException e) {
////                    e.printStackTrace();
////                } catch (ExceptionsLibrary.NoAccountException e) {
////                    e.printStackTrace();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////            String path = "Resources/Category/" + category.getName() + ".json";
////            File file = new File(path);
////            file.delete();
////        } catch (ExceptionsLibrary.NoCategoryException e) {
////            throw new ExceptionsLibrary.NoCategoryException();
////        }
//    }
//
//    public static void editCategory(String categoryName, HashMap<String, String> dataToEdit) throws ExceptionsLibrary.CategoryExistsWithThisName, ExceptionsLibrary.NoCategoryException, ExceptionsLibrary.NoFeatureWithThisName, ExceptionsLibrary.NoAccountException, ExceptionsLibrary.NoProductException {
//
//        String func = "Edit Category";
//        Client.sendMessage(func);
//
//        Object[] toSend = new Object[2];
//        toSend[0] = categoryName;
//        toSend[1] = dataToEdit;
//        Client.sendObject(toSend);
//
//        Object response = Client.receiveObject();
//
//        if (response instanceof ExceptionsLibrary.CategoryExistsWithThisName)
//            throw new ExceptionsLibrary.CategoryExistsWithThisName();
//
//        else if (response instanceof ExceptionsLibrary.NoCategoryException)
//            throw new ExceptionsLibrary.NoCategoryException();
//
//        else if (response instanceof ExceptionsLibrary.NoFeatureWithThisName)
//            throw new ExceptionsLibrary.NoFeatureWithThisName();
//
//        else if (response instanceof ExceptionsLibrary.NoAccountException)
//            throw new ExceptionsLibrary.NoAccountException();
//
//        else if (response instanceof ExceptionsLibrary.NoProductException)
//            throw new ExceptionsLibrary.NoProductException();
////        Category category = GetDataFromDatabase.getCategory(categoryName);
////        String oldName = category.getName();
////        for (String i : dataToEdit.keySet()) {
////            try {
////                Field field = Category.class.getDeclaredField(i);
////                if (i.equals("features")) {
////                    field.setAccessible(true);
////                    String[] splitFeatures = dataToEdit.get(i).split("\\s*,\\s*");
////                    ArrayList<Feature> newFeatures = new ArrayList<>();
////                    for (String j : splitFeatures) {
////                        newFeatures.add(new Feature(j, null));
////                    }
////                    field.set(category, newFeatures);
////                } else {
////                    field.setAccessible(true);
////                    field.set(category, dataToEdit.get(i));
////                }
////            } catch (NoSuchFieldException | IllegalAccessException e) {
////                throw new ExceptionsLibrary.NoFeatureWithThisName();
////            }
////        }
////        Gson gson = new GsonBuilder().serializeNulls().create();
////        String newName = category.getName();
////        String editedDetails = gson.toJson(category);
////        if (newName.equals(oldName)) {
////            try {
////                String path = "Resources/Category/" + category.getName() + ".json";
////                FileWriter fileWriter = new FileWriter(path);
////                fileWriter.write(editedDetails);
////                fileWriter.close();
////                File file = new File(path);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        } else {
////            try {
////                String newPath = "Resources/Category/" + newName + ".json";
////                String oldPath = "Resources/Category/" + oldName + ".json";
////                File file = new File(newPath);
////                if (file.exists()) {
////                    throw new ExceptionsLibrary.CategoryExistsWithThisName();
////                }
////                file.createNewFile();
////                FileWriter fileWriter = new FileWriter(newPath);
////                fileWriter.write(editedDetails);
////                fileWriter.close();
////                File file1 = new File(oldPath);
////                file1.delete();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////        File folder = new File("Resources/Products");
////        FileFilter fileFilter = new FileFilter() {
////            @Override
////            public boolean accept(File file1) {
////                if (file1.getName().endsWith(".json")) {
////                    return true;
////                }
////                return false;
////            }
////        };
////        for (File i : folder.listFiles(fileFilter)) {
////            Gson gson1 = new GsonBuilder().serializeNulls().create();
////            try {
////                String fileData = "";
////                fileData = new String(Files.readAllBytes(Paths.get(i.getPath())));
////                Product product = gson1.fromJson(fileData, Product.class);
////                product.setCategory(category);
////                SetDataToDatabase.setProduct(product);
////                SetDataToDatabase.updateSellerOfProduct(product,0);
////            } catch (ExceptionsLibrary.NoAccountException | IOException e) {
////                throw new ExceptionsLibrary.NoAccountException();
////            }
////        }
//    }
//
//    public static void addCategory(String categoryDetails) throws ExceptionsLibrary.CategoryExistsWithThisName {
//
//        String func = "Add Category";
//        Client.sendMessage(func);
//
//        Client.sendMessage(categoryDetails);
//        Object response = Client.receiveObject();
//
//        if (response instanceof ExceptionsLibrary.CategoryExistsWithThisName)
//            throw new ExceptionsLibrary.CategoryExistsWithThisName();
//
//        else
//            return;
////        Gson gson = new GsonBuilder().serializeNulls().create();
////        Category category = gson.fromJson(categoryDetails, Category.class);
////        if (!checkCategoryName(category.getName())) {
////            String newSaleDetails = gson.toJson(category);
////            try {
////                String path = "Resources/Category/" + category.getName() + ".json";
////                File file = new File(path);
////                file.createNewFile();
////                FileWriter fileWriter = new FileWriter(path);
////                fileWriter.write(newSaleDetails);
////                fileWriter.close();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        } else {
////            throw new ExceptionsLibrary.CategoryExistsWithThisName();
////        }
//    }
//

//    public static ArrayList<Product> getAllProducts() throws ExceptionsLibrary.NoProductException {
//        ArrayList<Product> allProducts = new ArrayList<>();
//
//        String func = "Get All Products Admin";
//        Client.sendMessage(func);
//
//        Object response = Client.receiveObject();
//
//        if (response instanceof ExceptionsLibrary.NoProductException)
//            throw new ExceptionsLibrary.NoProductException();
//
//        else
//            allProducts = (ArrayList<Product>) response;
////        String path = "Resources/Products";
////        File folder = new File(path);
////        FileFilter fileFilter = new FileFilter() {
////            @Override
////            public boolean accept(File file1) {
////                if (file1.getName().endsWith(".json")) {
////                    return true;
////                }
////                return false;
////            }
////        };
////        for (File i : folder.listFiles(fileFilter)) {
////            String fileName = i.getName();
////            int productId = Integer.parseInt(fileName.replace(".json", ""));
////            allProducts.add(GetDataFromDatabase.getProduct(productId));
////        }
//        return allProducts;
//    }
//
//


}