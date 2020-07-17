package Controller;

import Models.*;
import Models.Accounts.Customer;
import Models.Accounts.Manager;
import Models.Accounts.Seller;
import Models.Logs.Log;
import Models.Request.Request;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class WriteIntoFile {
    public static void writeCustomersIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\customers.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\customers.txt");
        for (Customer customer : Customer.getAllCustomers()) {
            fileWriter.append(gson.toJson(customer) + "\n");
        }
        fileWriter.close();
    }

    public static void writeManagersIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\managers.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\managers.txt");
        for (Manager manager : Manager.getAllManagers()) {
            fileWriter.append(gson.toJson(manager) + "\n");
        }
        fileWriter.close();
    }

    public static void writeSellersIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\sellers.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\sellers.txt");
        for (Seller seller : Seller.getAllSellers()) {
            fileWriter.append(gson.toJson(seller) + "\n");
        }
        fileWriter.close();
    }

    public static void writeRequestsIntoFile() throws IOException {
        Gson gson = new Gson();
        // FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\requests.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\requests.txt");
        for (Request request : Request.getAllRequests()) {
            fileWriter.append(gson.toJson(request) + "\n");
        }
        fileWriter.close();
    }

    public static void writeProductsIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\products.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\products.txt");
        for (Product product : Product.getAllProducts()) {
            fileWriter.append(gson.toJson(product) + "\n");
        }
        fileWriter.close();
    }

    public static void writeLogsIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\logs.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\logs.txt");
        for (Log log : Log.getAllLogs()) {
            fileWriter.append(gson.toJson(log) + "\n");
        }
        fileWriter.close();
    }

    public static void writeDiscountProductsIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\discountProducts.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\discountProducts.txt");
        for (Product discountProduct : Discount.getDiscountProducts()) {
            fileWriter.append(gson.toJson(discountProduct) + "\n");
        }
        fileWriter.close();
    }

    public static void writeDiscountCodesIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\discountCodes.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\discountCodes.txt");
        for (DiscountCode discountCode : DiscountCode.getAllDiscountCodes()) {
            fileWriter.append(gson.toJson(discountCode) + "\n");
        }
        fileWriter.close();
    }

    public static void writeCategoriesIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\categories.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\categories.txt");
        for (Category category : Category.getAllCategories()) {
            fileWriter.append(gson.toJson(category) + "\n");
        }
        fileWriter.close();
    }

    public static void writeCartsIntoFile() throws IOException {
        Gson gson = new Gson();
        //FileWriter fileWriter = new FileWriter("C:\\Users\\kian\\IdeaProjects\\Project_team-17\\project_AP\\Project_team-17\\Project_team-17\\Project_team-17\\Project_team-17\\Test\\src\\main\\java\\Resources\\carts.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\carts.txt");
        for (Cart cart : Cart.getAllCarts()) {
            fileWriter.append(gson.toJson(cart) + "\n");
        }
        fileWriter.close();
    }

    public static void writePointOfViewsIntoFile() throws IOException {
        Gson gson = new Gson();
        // FileWriter fileWriter = new FileWriter("");
        FileWriter fileWriter = new FileWriter("C:\\Users\\UX434FL\\IdeaProjects\\project\\AP17\\src\\main\\java\\Resources\\pointOfViews.txt");
        for (PointOfView pointOfView : PointOfView.getAllPointOfViews()) {
            fileWriter.append(gson.toJson(pointOfView) + "\n");
        }
        fileWriter.close();
    }

    public static void writeIntoFile() throws IOException {
        writeCustomersIntoFile();
        writeCartsIntoFile();
        writeSellersIntoFile();
        writeCategoriesIntoFile();
        writeDiscountCodesIntoFile();
        writeDiscountProductsIntoFile();
        writeManagersIntoFile();
        writePointOfViewsIntoFile();
        writeProductsIntoFile();
        writeRequestsIntoFile();
        writeLogsIntoFile();
    }

}
